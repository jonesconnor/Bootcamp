import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppModule } from 'src/app/app.module';
import { AuthenticationModule } from '../../authentication.module';

import { RegisterServiceProviderComponent } from './register-service-provider.component';

describe('RegisterServiceProviderComponent', () => {
  let component: RegisterServiceProviderComponent;
  let fixture: ComponentFixture<RegisterServiceProviderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterServiceProviderComponent ],
      imports:[AuthenticationModule,AppModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterServiceProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
