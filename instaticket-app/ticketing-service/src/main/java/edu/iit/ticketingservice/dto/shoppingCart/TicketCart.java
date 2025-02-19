package edu.iit.ticketingservice.dto.shoppingCart;

import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dao.TicketPackageEntity;
import edu.iit.ticketingservice.dto.event.Event;
import edu.iit.ticketingservice.dto.ticketPackage.TicketPackage;

public class TicketCart {
   private EventEntity event;
   private TicketPackageEntity ticketPackage;

   public TicketCart(EventEntity event, TicketPackageEntity ticketPackage) {
      this.event = event;
      this.ticketPackage = ticketPackage;
   }

   public EventEntity getEvent() {
      return event;
   }

   public TicketPackageEntity getTicketPackage() {
      return ticketPackage;
   }

   public void setTicketPackage(TicketPackageEntity ticketPackage) {
      this.ticketPackage = ticketPackage;
   }

   public void setEvent(EventEntity event) {
      this.event = event;
   }
}
