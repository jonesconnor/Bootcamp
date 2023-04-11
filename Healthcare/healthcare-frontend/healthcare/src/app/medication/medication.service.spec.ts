import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClientModule } from '@angular/common/http';
import { MedicationModule } from './medication.module';

import { MedicationService } from './medication.service';

describe('MedicationService', () => {
  let service: MedicationService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule, HttpClientModule, MedicationModule]});
    service = TestBed.inject(MedicationService);
    httpTestingController = TestBed.get(HttpTestingController)
    service=TestBed.get(MedicationService)
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });



});
