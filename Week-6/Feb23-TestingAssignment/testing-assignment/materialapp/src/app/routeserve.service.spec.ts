import { TestBed } from '@angular/core/testing';

import { RouteserveService } from './routeserve.service';

describe('RouteserveService', () => {
  let service: RouteserveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RouteserveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
