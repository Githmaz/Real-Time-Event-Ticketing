import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RealTimeLogsComponent } from './real-time-logs.component';

describe('RealTimeLogsComponent', () => {
  let component: RealTimeLogsComponent;
  let fixture: ComponentFixture<RealTimeLogsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RealTimeLogsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RealTimeLogsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
