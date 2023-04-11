import { TestBed } from '@angular/core/testing';

import { UserprocessService } from './userprocess.service';

describe('UserprocessService', () => {
  let service: UserprocessService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserprocessService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
