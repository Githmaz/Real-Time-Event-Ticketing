package edu.iit.TicketingSimulation.model;

import edu.iit.TicketingSimulation.service.LoggerService;
import edu.iit.TicketingSimulation.util.FileLoggerUtil;
import edu.iit.TicketingSimulation.util.LoggingServer;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private final int maxTicketCapacity;
    private final Lock reentrantLock = new ReentrantLock();
    private final Condition ticketPoolEmpty = reentrantLock.newCondition();
    private final Condition ticketPoolFull = reentrantLock.newCondition();
    private final Queue<Ticket> tickets;
    private PriorityQueue<Customer> priorityCustomer;
    private  LoggerService logger ;


    public TicketPool(int maxTicketCapacity,LoggerService logger) {
        this.logger = logger;
        this.maxTicketCapacity = maxTicketCapacity;
        this.tickets = new LinkedList<>();
        priorityCustomer = new PriorityQueue<>((c1 , c2)->{
            if(c1.isVip() && !c2.isVip()) return -1;
            if(!c1.isVip() && c2.isVip()) return 1;
            return 0;
        });
    }

    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.tickets = new LinkedList<>();
    }
    public  void addTicket(Ticket ticket) {
        reentrantLock.lock();
            try {
                while (this.tickets.size() >= maxTicketCapacity) {
                    LoggingServer.broadcastLog("Waiting to add ticket: " + ticket.getTicketId());
                    ticketPoolFull.await();
                }
                tickets.add(ticket);
                String logMessage = "Vendor ID - " + ticket.getVendorId() + " added Ticket ID - " + ticket.getTicketId();
                sendLogger(logMessage);
                FileLoggerUtil.logToFile(logMessage);
                LoggingServer.broadcastLog(logMessage);
                ticketPoolEmpty.signalAll();

            } catch (InterruptedException e) {
                LoggingServer.broadcastLog("Thread interrupted while waiting to add a ticket.");
                Thread.currentThread().interrupt();
            }finally {
                reentrantLock.unlock();
            }


    }

    public synchronized Ticket getTicket(Customer customer) {
        reentrantLock.lock();
            try {
                LoggingServer.broadcastLog("Waiting for ticket retrieval by customer: " + customer.getCustomerId());
                while (tickets.isEmpty()) {
                    ticketPoolEmpty.await();
                }
                Ticket ticket = tickets.poll();
                String logMessage = "Customer ID - " + customer.getCustomerId() + " retrieved Ticket ID - " + ticket.getTicketId();
                sendLogger(logMessage);
                FileLoggerUtil.logToFile(logMessage);
                LoggingServer.broadcastLog(logMessage);
                ticketPoolFull.signalAll();
                return ticket;
            } catch (InterruptedException e) {
                LoggingServer.broadcastLog("Thread interrupted while waiting to get a ticket.");
                Thread.currentThread().interrupt();
                return null;
            }
            finally {
                reentrantLock.unlock();
            }
    }

    private void sendLogger(String logMessage) {
        if(logger != null) {
            logger.sendLoggerToClient(logMessage);
        }
    }
}
