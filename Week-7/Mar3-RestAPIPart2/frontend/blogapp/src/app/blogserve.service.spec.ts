import { TestBed } from '@angular/core/testing';

import { BlogserveService } from './blogserve.service';

describe('BlogserveService', () => {
  let service: BlogserveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BlogserveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
