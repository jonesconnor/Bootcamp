import { TestBed } from '@angular/core/testing';

import { UserhandlerService } from './userhandler.service';

describe('UserhandlerService', () => {
  let service: UserhandlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserhandlerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
