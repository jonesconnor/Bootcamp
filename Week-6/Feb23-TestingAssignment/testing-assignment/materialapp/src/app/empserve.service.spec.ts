import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { EmpserveService } from './empserve.service';

describe('EmpserveService', () => {
  let service: EmpserveService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EmpserveService]
    });
    service = TestBed.inject(EmpserveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
