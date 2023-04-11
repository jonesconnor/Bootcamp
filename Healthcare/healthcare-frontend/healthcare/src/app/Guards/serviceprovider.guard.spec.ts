import { TestBed } from '@angular/core/testing';

import { ServiceproviderGuard } from './serviceprovider.guard';

describe('ServiceproviderGuard', () => {
  let guard: ServiceproviderGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(ServiceproviderGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
