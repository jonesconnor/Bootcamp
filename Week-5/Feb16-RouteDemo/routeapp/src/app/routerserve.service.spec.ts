import { TestBed } from '@angular/core/testing';

import { RouterserveService } from './routerserve.service';

describe('RouterserveService', () => {
  let service: RouterserveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RouterserveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
