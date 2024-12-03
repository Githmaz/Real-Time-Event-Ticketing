import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketPackageComponent } from './ticket-package.component';

describe('TicketPackageComponent', () => {
  let component: TicketPackageComponent;
  let fixture: ComponentFixture<TicketPackageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketPackageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TicketPackageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
