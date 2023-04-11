import { TestBed } from '@angular/core/testing';

import { DboardcanactiveGuard } from './dboardcanactive.guard';

describe('DboardcanactiveGuard', () => {
  let guard: DboardcanactiveGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(DboardcanactiveGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
