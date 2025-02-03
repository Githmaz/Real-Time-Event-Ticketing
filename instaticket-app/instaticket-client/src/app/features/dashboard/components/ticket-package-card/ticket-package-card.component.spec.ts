import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketPackageCardComponent } from './ticket-package-card.component';

describe('TicketPackageCardComponent', () => {
  let component: TicketPackageCardComponent;
  let fixture: ComponentFixture<TicketPackageCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketPackageCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TicketPackageCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
