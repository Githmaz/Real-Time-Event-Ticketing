import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketPackageTableComponent } from './ticket-package-table.component';

describe('TicketPackageTableComponent', () => {
  let component: TicketPackageTableComponent;
  let fixture: ComponentFixture<TicketPackageTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketPackageTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TicketPackageTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
