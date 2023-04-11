import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppModule } from 'src/app/app.module';
import { AuthenticationModule } from '../../authentication.module';

import { RegisterPatientComponent } from './register-patient.component';

describe('RegisterPatientComponent', () => {
  let component: RegisterPatientComponent;
  let fixture: ComponentFixture<RegisterPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterPatientComponent ],
      imports:[AuthenticationModule,AppModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
