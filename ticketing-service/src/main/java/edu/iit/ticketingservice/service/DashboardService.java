package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dto.dashboardData.CustomerDashboardData;
import org.springframework.stereotype.Service;

@Service
public interface DashboardService {
    CustomerDashboardData getCustomerDashboardData();
}
