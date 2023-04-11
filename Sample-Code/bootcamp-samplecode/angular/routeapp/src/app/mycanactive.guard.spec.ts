import { TestBed } from '@angular/core/testing';

import { MycanactiveGuard } from './mycanactive.guard';

describe('MycanactiveGuard', () => {
  let guard: MycanactiveGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(MycanactiveGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
