package edu.iit.ticketingservice.service;

import org.springframework.stereotype.Service;

@Service
public interface TaxService {
    double getTaxRate();
    double setTaxRate(double taxRate);
}
