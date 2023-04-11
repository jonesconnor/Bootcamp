import { TestBed } from '@angular/core/testing';

import { CommonlibService } from './commonlib.service';

describe('CommonlibService', () => {
  let service: CommonlibService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommonlibService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
