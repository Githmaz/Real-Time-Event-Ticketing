import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotFoundBannerComponent } from './not-found-banner.component';

describe('NotFoundBannerComponent', () => {
  let component: NotFoundBannerComponent;
  let fixture: ComponentFixture<NotFoundBannerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotFoundBannerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotFoundBannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
