import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateComponent } from './create.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';

import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

describe('CreateComponent', () => {
  let component: CreateComponent;
  let fixture: ComponentFixture<CreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateComponent ], 
      imports: [HttpClientModule, MatExpansionModule, MatIconModule, MatFormFieldModule, MatInputModule, BrowserAnimationsModule, MatSnackBarModule,MaterialModule, FormsModule, ReactiveFormsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it(`Should Have Input Field for 'subject'`,()=>{
    const fixture = TestBed.createComponent(CreateComponent);
    fixture.detectChanges();
    const app = fixture.debugElement.nativeElement as HTMLElement;
    expect(app.querySelector('input[formControlName="subject"]')?.previousElementSibling?.textContent).toContain('Purpose of appointment');
  })

  it(`Should Have Input Field for 'bookingDate'`,()=>{
    const fixture = TestBed.createComponent(CreateComponent);
    fixture.detectChanges();
    const app = fixture.debugElement.nativeElement as HTMLElement;
    expect(app.querySelector('input[formControlName="bookingDate"]')?.previousElementSibling?.textContent).toContain('Date of appointment');
  })
});
