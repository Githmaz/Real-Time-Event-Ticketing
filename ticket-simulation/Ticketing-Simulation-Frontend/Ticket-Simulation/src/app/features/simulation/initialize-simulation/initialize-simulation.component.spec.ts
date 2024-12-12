import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InitializeSimulationComponent } from './initialize-simulation.component';

describe('InitializeSimulationComponent', () => {
  let component: InitializeSimulationComponent;
  let fixture: ComponentFixture<InitializeSimulationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InitializeSimulationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InitializeSimulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
