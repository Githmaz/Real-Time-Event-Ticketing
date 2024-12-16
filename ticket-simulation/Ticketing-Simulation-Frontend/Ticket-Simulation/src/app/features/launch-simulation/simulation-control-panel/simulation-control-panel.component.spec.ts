import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimulationControlPanelComponent } from './simulation-control-panel.component';

describe('SimulationControlPanelComponent', () => {
  let component: SimulationControlPanelComponent;
  let fixture: ComponentFixture<SimulationControlPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SimulationControlPanelComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SimulationControlPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
