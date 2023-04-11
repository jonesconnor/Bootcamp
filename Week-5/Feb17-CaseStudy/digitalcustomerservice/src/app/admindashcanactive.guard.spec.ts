import { TestBed } from '@angular/core/testing';

import { AdmindashcanactiveGuard } from './admindashcanactive.guard';

describe('AdmindashcanactiveGuard', () => {
  let guard: AdmindashcanactiveGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AdmindashcanactiveGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
