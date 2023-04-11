import { TestBed } from '@angular/core/testing';

import { MycandeactGuard } from './mycandeact.guard';

describe('MycandeactGuard', () => {
  let guard: MycandeactGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(MycandeactGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
