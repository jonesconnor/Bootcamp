import {  async, inject, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BookingdbService } from './bookingdb.service';
import { Booking } from '../model/Booking';

describe('BookingdbService', () => {
  let service: BookingdbService;
  let httpMock: HttpTestingController
  let postItemArr: Array<Booking>;
  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule]});
    service = TestBed.inject(BookingdbService);
    httpMock = TestBed.inject(HttpTestingController)
    postItemArr =[
      {
        "id": "6421f48e61a9a95dcd42a88a",
        "patientId": "10",
        "serviceProviderId": "20",
        "subject": "cold",
        "paymentToken": "string",
        "timeSlot": {
          "bookingDate": "2023-04-22",
          "bookingTime": "08:30:00",
          "duration": 30
        },
        "location": "in person",
        "status": 0,
        "recurrenceType": "none",
        "recurrenceInterval": 0,
        "recurrenceEndDate": "2023-03-27",
        "isEmergency": false
      },
      {
        "id": "6421f48e61a9a95dcd42a88b",
        "patientId": "10",
        "serviceProviderId": "21",
        "subject": "renew prescription",
        "paymentToken": "string",
        "timeSlot": {
          "bookingDate": "2023-03-27",
          "bookingTime": "10:00:00",
          "duration": 30
        },
        "location": "online",
        "status": 0,
        "recurrenceType": "none",
        "recurrenceInterval": 0,
        "recurrenceEndDate": "2023-03-27",
        "isEmergency": true
      }]
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('Should get bookings as an Observable array',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
     service.viewAllBooking().subscribe(res =>{
       expect(res.length).toBe(2)
     })
     let req = httpMock.expectOne('http://localhost:9000/bookings/all');
     expect(req.request.method).toBe("GET");
     req.flush(postItemArr)
     httpMock.verify()
 })))

  it('Should get one booking as an Observable',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.viewBooking("6421f48e61a9a95dcd42a88a").subscribe(res =>{
      expect(res.subject).toBe("cold")
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/view/6421f48e61a9a95dcd42a88a');
    expect(req.request.method).toBe("GET");
    req.flush(postItemArr[0])
    httpMock.verify()
  })))

  it('Should post a booking and return an Observable',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.createBooking(postItemArr[0]).subscribe(res =>{
      expect(res.subject).toBe("cold")
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/create');
    expect(req.request.method).toBe("POST");
    req.flush(postItemArr[0])
    httpMock.verify()
  })))

  it('Should delete a booking and return an Observable',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.deleteBooking("6421f48e61a9a95dcd42a88a").subscribe(res =>{
      expect(res.subject).toBe("cold")
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/6421f48e61a9a95dcd42a88a');
    expect(req.request.method).toBe("DELETE");
    req.flush(postItemArr[0])
    httpMock.verify()
  })))

  it('Should get bookings by status as an Observable array',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.viewBookingsByStatus(0).subscribe(res =>{
      expect(res.length).toBe(2)
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/status/0');
    expect(req.request.method).toBe("GET");
    req.flush(postItemArr)
    httpMock.verify()
  })))

  it('Should get bookings by patient id as an Observable array',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.viewBookingsByPatient("10").subscribe(res =>{
      expect(res.length).toBe(2)
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/patient/10');
    expect(req.request.method).toBe("GET");
    req.flush(postItemArr)
    httpMock.verify()
  })))

    it('Should get bookings by provider id as an Observable array',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.viewBookingsByProvider("10").subscribe(res =>{
      expect(res.length).toBe(2)
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/provider/10');
    expect(req.request.method).toBe("GET");
    req.flush(postItemArr)
    httpMock.verify()
  })))

    it('Should get bookings by emergency as an Observable array',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.viewBookingsByEmergency(true).subscribe(res =>{
      expect(res.length).toBe(2)
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/emergency/true');
    expect(req.request.method).toBe("GET");
    req.flush(postItemArr)
    httpMock.verify()
  })))

    it('Should get bookings by location as an Observable array',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.viewBookingsByLocation("online").subscribe(res =>{
      expect(res.length).toBe(2)
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/location/online');
    expect(req.request.method).toBe("GET");
    req.flush(postItemArr)
    httpMock.verify()
  })))

    it('Should get bookings by payment token as an Observable array',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.viewBookingsByPaymentToken("abcd").subscribe(res =>{
      expect(res.length).toBe(2)
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/payment/abcd');
    expect(req.request.method).toBe("GET");
    req.flush(postItemArr)
    httpMock.verify()
  })))

    it('Should get bookings by date as an Observable array',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.viewBookingsByDate("2023-03-27").subscribe(res =>{
      expect(res.length).toBe(2)
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/date/2023-03-27');
    expect(req.request.method).toBe("GET");
    req.flush(postItemArr)
    httpMock.verify()
   })))

  it('Should get bookings by patient id and date as an Observable array',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.viewBookingsByPatientAndDate( "10","2023-03-27").subscribe(res =>{
      expect(res.length).toBe(2)
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/patient/10/date/2023-03-27');
    expect(req.request.method).toBe("GET");
    req.flush(postItemArr)
    httpMock.verify()
  })))
  
    it('Should get bookings by provider id and date as an Observable array',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.viewBookingsByProviderAndDate( "10",  "2023-03-27").subscribe(res =>{
      expect(res.length).toBe(2)
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/provider/10/date/2023-03-27');
    expect(req.request.method).toBe("GET");
    req.flush(postItemArr)
    httpMock.verify()
  })))

    it('Should get bookings by patient id and provider id as an Observable array',async(inject([HttpTestingController ,BookingdbService],(httpClient: HttpTestingController, service:BookingdbService)=>{
    service.viewBookingsByPatientAndProvider("10","10").subscribe(res =>{
      expect(res.length).toBe(2)
    })
    let req = httpMock.expectOne('http://localhost:9000/bookings/patient/10/provider/10');
    expect(req.request.method).toBe("GET");
    req.flush(postItemArr)
    httpMock.verify()
  })))

});
