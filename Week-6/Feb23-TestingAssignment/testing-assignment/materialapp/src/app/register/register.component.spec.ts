import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterTestingModule } from '@angular/router/testing';

import { RegisterComponent } from './register.component';
import { MatIconModule } from '@angular/material/icon';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let snackBarSpy: jasmine.SpyObj<MatSnackBar>;

  beforeEach(async () => {
    const snackSpy = jasmine.createSpyObj('MatSnackBar', ['open']);

    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, MatFormFieldModule, MatIconModule, MatInputModule, BrowserAnimationsModule, NoopAnimationsModule],
      declarations: [RegisterComponent],
      providers: [{ provide: MatSnackBar, useValue: snackSpy }]
    }).compileComponents();

    snackBarSpy = TestBed.inject(MatSnackBar) as jasmine.SpyObj<MatSnackBar>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call the MatSnackBar when register is clicked', () => {
    const registerButton = fixture.nativeElement.querySelector('button.mat-fab');
    registerButton.click();

    expect(snackBarSpy.open).toHaveBeenCalledOnceWith('Registered', 'User', { duration: 2000 });
  });

  it('should call the routeserve.openLogin() when login is clicked', () => {
    const loginButton = fixture.nativeElement.querySelector('button');
    spyOn(component.routeserve, 'openLogin');

    loginButton.click();

    expect(component.routeserve.openLogin).toHaveBeenCalled();
  });
});
