package edu.iit.TicketingSimulation.dto;

public class SimulationInitData {
    private int customerCount;
    private int vendorCount;
    private int vipCustomerCount;
    private SimulationConfig simulationConfig;

    public SimulationInitData(int customerCount, int vendorCount, int vipCustomerCount, SimulationConfig simulationConfig) {
        this.customerCount = customerCount;
        this.vendorCount = vendorCount;
        this.vipCustomerCount = vipCustomerCount;
        this.simulationConfig = simulationConfig;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

    public int getVipCustomerCount() {
        return vipCustomerCount;
    }

    public void setVipCustomerCount(int vipCustomerCount) {
        this.vipCustomerCount = vipCustomerCount;
    }

    public SimulationConfig getSimulationConfig() {
        return simulationConfig;
    }

    public void setSimulationConfig(SimulationConfig simulationConfig) {
        this.simulationConfig = simulationConfig;
    }
}
