import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatExpansionModule } from '@angular/material/expansion';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { AuthenticateService } from '../authenticate.service';
import { RouteserveService } from '../routeserve.service';
import { HttpClientTestingModule } from '@angular/common/http/testing'

import { LoginComponent } from './login.component';
describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthenticateService;
  let routeService: RouteserveService;
  let generateTokenSpy: jasmine.Spy;
  let openDashboardSpy: jasmine.Spy;

  const testUser = 'admin';
  const testPassword = 'password';

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      providers: [ AuthenticateService, RouteserveService ],
      imports: [ HttpClientTestingModule, RouterTestingModule, FormsModule, MatExpansionModule, MatIconModule, MatFormFieldModule, BrowserAnimationsModule, NoopAnimationsModule ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthenticateService);
    routeService = TestBed.inject(RouteserveService);

    generateTokenSpy = spyOn(authService, 'generateToken').and.returnValue(of({ token: 'testToken' }));
    openDashboardSpy = spyOn(routeService, 'openDashboard');
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call authenticate service generateToken method with correct data when SignIn button is clicked', () => {
    const signinButton = fixture.debugElement.query(By.css('button')).nativeElement;
    signinButton.click();

    expect(generateTokenSpy).toHaveBeenCalledOnceWith({ username: testUser, password: testPassword });
  });

  it('should navigate to dashboard when SignIn button is clicked and authentication is successful', () => {
    const signinButton = fixture.debugElement.query(By.css('button')).nativeElement;
    signinButton.click();

    expect(openDashboardSpy).toHaveBeenCalled();
  });
});
