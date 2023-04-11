import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from 'environment';
import { MedicalRecord } from './model/MedicalRecord';

import { MrService } from './mr.service';

describe('MrService', () => {
  let service: MrService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MrService]
    });
    service = TestBed.inject(MrService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should add a new medical record', () => {
    const newRecord: MedicalRecord = {
      id: 1,
      patientid: '10',
      doctorid: '100',
      date: '2023-03-27',
      notes: 'Test note',
    };
    service.addRecord(newRecord).subscribe((record: MedicalRecord) => {
      expect(record).toEqual(newRecord);
    });

    const req = httpTestingController.expectOne(environment.addRecordAPI);
    expect(req.request.method).toEqual('POST');
    req.flush(newRecord);
  });

  it('should get a medical record by ID', () => {
    const recordId = 1;
    const mockRecord: MedicalRecord = {
      id: recordId,
      patientid: '10',
      doctorid: '100',
      date: '2023-03-27',
      notes: 'Test note'
    };
    service.getRecordById(recordId).subscribe((record: MedicalRecord) => {
      expect(record).toEqual(mockRecord);
    });

    const req = httpTestingController.expectOne(environment.getRecordAPI + `/${recordId}`);
    expect(req.request.method).toEqual('GET');
    req.flush(mockRecord);
  });

  it('should get a list of medical records by patient ID', () => {
    const patientid = '100';
    const mockRecords: MedicalRecord[] = [
      {
        id: 1,
        patientid: patientid,
        doctorid: '100',
        date: '2023-03-27',
        notes: 'Test note 1',
      },
      {
        id: 2,
        patientid: patientid,
        doctorid: '200',
        date: '2023-03-27',
        notes: 'Test note 2',
      },
    ];
    service.getRecordsByPatient(patientid).subscribe((records: MedicalRecord[]) => {
      expect(records).toEqual(mockRecords);
    });

    const req = httpTestingController.expectOne(environment.getRecordAPI + `/patient/${patientid}`);
    expect(req.request.method).toEqual('GET');
    req.flush(mockRecords);
  });

  it('should get medical records by patient ID and date', () => {
    const patientId = 'patient1';
    const date = '2022-03-27';
    const mockRecords: MedicalRecord[] = [
      {
        id: 1,
        patientid: patientId,
        doctorid: 'doctor1',
        notes: 'Test note 1',
        date: date,
      },
      {
        id: 2,
        patientid: patientId,
        doctorid: 'doctor2',
        notes: 'Test note 2',
        date: date,
      },
    ];
    service.getRecordsByPatiendidAndDate(patientId, date).subscribe((records: MedicalRecord[]) => {
      expect(records).toEqual(mockRecords);
    });

    const req = httpTestingController.expectOne(environment.getRecordAPI + `/patientdate/${patientId}/${date}`);
    expect(req.request.method).toEqual('GET');
    req.flush(mockRecords);
  });

  it('should get medical records by doctorid and patientid', () => {
    const doctorid = 'doctor1'
    const patientid = 'patient1';
    const mockRecords: MedicalRecord[] = [
      {
        id: 1,
        patientid: patientid,
        doctorid: doctorid,
        notes: 'Test note 1',
        date: '2023-03-28',
      },
      {
        id: 2,
        patientid: patientid,
        doctorid: doctorid,
        notes: 'Test note 2',
        date: '2023-03-28',
      },
    ];
    service.getRecordsByDoctoridAndPatientid(doctorid, patientid).subscribe((records: MedicalRecord[]) => {
      expect(records).toEqual(mockRecords);
    });
    const req = httpTestingController.expectOne(environment.getRecordAPI + `/doctorpatient/${doctorid}/${patientid}`);
    expect(req.request.method).toEqual('GET');
    req.flush(mockRecords);
  })

  it('should get medical records by doctor ID and date', () => {
    const doctorid = 'doctor1';
    const date = '2022-03-28';
    const mockRecords: MedicalRecord[] = [
      {
        id: 1,
        patientid: 'patient1',
        doctorid: doctorid,
        notes: 'Test note 1',
        date: date,
      },
      {
        id: 2,
        patientid: 'patient1',
        doctorid: doctorid,
        notes: 'Test note 2',
        date: date,
      },
    ];
    service.getRecordsByDoctoridAndDate(doctorid, date).subscribe((records: MedicalRecord[]) => {
      expect(records).toEqual(mockRecords);
    });

    const req = httpTestingController.expectOne(environment.getRecordAPI + `/doctordate/${doctorid}/${date}`);
    expect(req.request.method).toEqual('GET');
    req.flush(mockRecords);
  });


});
