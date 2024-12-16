import { SimulationConfig } from "../simulation-config/simulation-config.model";

export interface SimulationStatus {
  customerCount: number;
  vendorCount: number;
  vipCustomerCount: number;
  simulationConfig: SimulationConfig;
}
