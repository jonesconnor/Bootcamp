import { TestBed } from '@angular/core/testing';

import { MycanchildGuard } from './mycanchild.guard';

describe('MycanchildGuard', () => {
  let guard: MycanchildGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(MycanchildGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
