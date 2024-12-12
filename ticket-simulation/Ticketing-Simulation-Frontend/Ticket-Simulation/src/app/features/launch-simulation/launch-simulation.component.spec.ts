import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LaunchSimulationComponent } from './launch-simulation.component';

describe('LaunchSimulationComponent', () => {
  let component: LaunchSimulationComponent;
  let fixture: ComponentFixture<LaunchSimulationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LaunchSimulationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LaunchSimulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
