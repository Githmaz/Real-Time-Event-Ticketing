package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.service.TaxService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TaxServiceImpl implements TaxService {

    @Value("${app.tax.percentage}")
    private double taxRate;

    @Override
    public double getTaxRate() {
        return taxRate;
    }

    @Override
    public double setTaxRate(double newTaxRate) {
        this.taxRate = newTaxRate;
        return this.taxRate;
    }
}
