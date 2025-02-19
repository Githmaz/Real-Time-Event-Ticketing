package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dao.TicketPackageEntity;
import edu.iit.ticketingservice.dto.shoppingCart.TicketCart;
import edu.iit.ticketingservice.dto.ticketPackage.TicketPackage;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.TicketPackageRepository;
import edu.iit.ticketingservice.service.TaxService;
import edu.iit.ticketingservice.service.TicketCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TicketCartServiceImpl implements TicketCartService {

    @Autowired
    private TicketPackageRepository ticketPackageRepository;

    @Override
    public TicketCart getTicketCart(String ticketId) {
        TicketPackageEntity ticketPackage = ticketPackageRepository.findByPackageId(ticketId)
                .orElseThrow(() -> new BusinessException(ErrorType.PACKAGE_NOT_FOUND));
        ticketPackage.getEvent().setTicketPackages(null);
        ticketPackage.setTickets(null);
        return  new TicketCart(ticketPackage.getEvent(),ticketPackage);
    }

}
