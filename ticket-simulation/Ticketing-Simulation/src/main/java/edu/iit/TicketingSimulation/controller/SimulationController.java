package edu.iit.TicketingSimulation.controller;

import edu.iit.TicketingSimulation.dto.CustomerRequest;
import edu.iit.TicketingSimulation.dto.SimulationStartRequest;
import edu.iit.TicketingSimulation.dto.VendorRequest;
import edu.iit.TicketingSimulation.model.ApiResponse;
import edu.iit.TicketingSimulation.model.Customer;
import edu.iit.TicketingSimulation.model.TicketConfig;
import edu.iit.TicketingSimulation.model.Vendor;
import edu.iit.TicketingSimulation.service.SimulationService;
import edu.iit.TicketingSimulation.util.LoggingServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    /**
     * Starts a new simulation with a given number of customers, vendors, and configuration.
     */
    @PostMapping("/start")
    public ResponseEntity<ApiResponse<String>> startSimulation(@RequestBody SimulationStartRequest request) {
        try {
            simulationService.initializeSimulation(
                    request.getCustomerCount(),
                    request.getVendorCount(),
                    request.getTicketsPerCustomer(),
                    request.getVipCustomerCount(),
                    null
            );
            simulationService.startSimulation();
            return ResponseEntity.ok(new ApiResponse<>(true, "Simulation started successfully.", null));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Stops the ongoing simulation.
     */
    @PostMapping("/stop")
    public ResponseEntity<ApiResponse<String>> stopSimulation() {
        try {
            simulationService.stopSimulation();
            return ResponseEntity.ok(new ApiResponse<>(true, "Simulation stopped successfully.", null));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds a new customer to the ongoing simulation.
     */
    @PostMapping("/add/customer")
    public ResponseEntity<ApiResponse<String>> addCustomer(@RequestBody CustomerRequest customerRequest) {
        try {
            simulationService.addCustomer(customerRequest);
            return ResponseEntity.ok(new ApiResponse<>(true, "Customer added successfully.", null));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds a new vendor to the ongoing simulation.
     */
    @PostMapping("/add/vendor")
    public ResponseEntity<ApiResponse<String>> addVendor(@RequestBody VendorRequest vendorRequest) {
        try {
            simulationService.addVendor(vendorRequest);
            return ResponseEntity.ok(new ApiResponse<>(true, "Vendor added successfully.", null));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Fetches the current status of the simulation.
     */
    @GetMapping("/status")
    public ResponseEntity<ApiResponse<String>> getSimulationStatus() {
        try {
            String status = simulationService.getSimulationStatus();
            return ResponseEntity.ok(new ApiResponse<>(true, status, null));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Fetches the list of all customers in the simulation.
     */
    @GetMapping("/customers")
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        try {
            List<Customer> customers = simulationService.getAllCustomers();
            return ResponseEntity.ok(new ApiResponse<>(true, "Customers fetched successfully.", customers));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Fetches the list of all vendors in the simulation.
     */
    @GetMapping("/vendors")
    public ResponseEntity<ApiResponse<List<Vendor>>> getAllVendors() {
        try {
            List<Vendor> vendors = simulationService.getAllVendors();
            return ResponseEntity.ok(new ApiResponse<>(true, "Vendors fetched successfully.", vendors));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
