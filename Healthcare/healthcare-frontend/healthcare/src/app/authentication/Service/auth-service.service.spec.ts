import { async, inject, TestBed, waitForAsync } from '@angular/core/testing';

import { AuthServiceService } from './auth-service.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing'
import { User } from '../Model/User';
import { AuthenticationModule } from '../authentication.module';
import { AppModule } from 'src/app/app.module';

describe('AuthServiceService', () => {
  let service: AuthServiceService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientTestingModule,
        AuthenticationModule,
        AppModule
      ],
      providers:[AuthServiceService]
    });
    service = TestBed.inject(AuthServiceService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return Observable<User> Object on Register',waitForAsync(inject([HttpTestingController,AuthServiceService], (httpMock:HttpTestingController, service: AuthServiceService)=>{
    const user:User=new User();
    user.mailid = "mitchel@gmail.com";
    user.password = "qwerty123";
      user.role="Patient";
    service.register(user).subscribe(res=>{
      expect(res.mailid).toBe("mitchel@gmail.com");
      expect(res.password).toBe("qwerty123");
      expect(res.role).toBe("Patient");
    })
    let req = httpMock.expectOne("http://localhost:1337/auth/register");
    expect(req.request.method).toBe("POST");
    httpMock.verify();
  })));

  it('should return token on login', waitForAsync(inject([HttpTestingController,AuthServiceService], (httpMock:HttpTestingController, service: AuthServiceService)=>{
    const user:User ={
       "mailid":"mitchel@gmail.com",
       "password":"qwerty123"
     }
  service.login(user).subscribe(res=>{
        expect(res.size).toBe(1);
        expect(res.has('token')).toBe(true);
     });
    let req = httpMock.expectOne("http://localhost:1337/auth/login");
     expect(req.request.method).toBe("POST");
     httpMock.verify();
    })));
});
