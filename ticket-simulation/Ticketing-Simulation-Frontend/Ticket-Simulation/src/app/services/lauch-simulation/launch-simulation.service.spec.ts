import { TestBed } from '@angular/core/testing';

import { LaunchSimulationService } from './launch-simulation.service';

describe('LaunchSimulationService', () => {
  let service: LaunchSimulationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LaunchSimulationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
