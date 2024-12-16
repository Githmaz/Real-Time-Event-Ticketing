package edu.iit.TicketingSimulation.controller;

import edu.iit.TicketingSimulation.dto.*;
import edu.iit.TicketingSimulation.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/simulation")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    /**
     * Starts a new simulation with a given number of customers, vendors, and configuration.
     */
    @PostMapping("/initialize")
    public ResponseEntity<ApiResponse<String>> startSimulation(@RequestBody SimulationStartRequest request) {
        try {
            simulationService.initializeSimulation(
                    request.getCustomerCount(),
                    request.getVendorCount(),
                    request.getTicketsPerCustomer(),
                    request.getVipCustomerCount(),
                    null
            );
            return ResponseEntity.ok(new ApiResponse<>(true, "Simulation started successfully.", null));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Initializes the simulation with provided customer and vendor data.
     *
     * @return ResponseEntity<ApiResponse<String>> indicating success or failure.
     */
    @PostMapping("/custom/initialize")
    public ResponseEntity<ApiResponse<String>> initializeSimulationWithRequests(
            @RequestBody SimulationRequest simulationRequest) {
        try {
            List<CustomerRequest> customerRequests = simulationRequest.getCustomers();
            List<VendorRequest> vendorRequests = simulationRequest.getVendors();
            simulationService.initializeSimulationWithRequests(customerRequests, vendorRequests);
            return ResponseEntity.ok(new ApiResponse<>(true, "Simulation initialized successfully with provided requests.", null));
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "An error occurred while initializing the simulation.", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Starts the simulation directly without initialization.
     */
    @PostMapping("/start")
    public ResponseEntity<ApiResponse<String>> startSimulationDirectly() {
        try {
            simulationService.startSimulation();
            return ResponseEntity.ok(new ApiResponse<>(true, "Simulation started successfully without initialization.", null));
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
            return new ResponseEntity<>(new ApiResponse<>(true, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
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
        System.out.println(vendorRequest.getTicketPrice());
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
     * Retrieves the simulation configuration for the frontend.
     */
    @GetMapping("/config")
    public ResponseEntity<ApiResponse<SimulationConfig>> getSimulationConfig() {
        try {
            SimulationConfig config = simulationService.getSimulationConfig();
            return ResponseEntity.ok(new ApiResponse<>(true, "Simulation configuration fetched successfully.", config));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Saves a new simulation configuration.
     */
    @PostMapping("/config")
    public ResponseEntity<ApiResponse<String>> saveSimulationConfig(@RequestBody SimulationConfig simulationConfig) {
        try {
            simulationService.saveSimulationConfig(simulationConfig);
            return ResponseEntity.ok(new ApiResponse<>(true, "Simulation configuration saved successfully.", null));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Checks if the simulation is ready to start.
     */
    @GetMapping("/ready")
    public ResponseEntity<ApiResponse<SimulationInitData>> isSimulationReady() {
        try {
            SimulationInitData simulationInitData =  simulationService.checkSimulationReady(); // Throws an exception if not ready
            return ResponseEntity.ok(new ApiResponse<>(true, "Simulation is ready to start.", simulationInitData));
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
