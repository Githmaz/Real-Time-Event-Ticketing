import { TestBed } from '@angular/core/testing';

import { ShorppingCartService } from './shorpping-cart.service';

describe('ShorppingCartService', () => {
  let service: ShorppingCartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShorppingCartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
