import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef, MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';


import { FulldialogComponent } from './fulldialog.component';

describe('FulldialogComponent', () => {
  let component: FulldialogComponent;
  let fixture: ComponentFixture<FulldialogComponent>;
  

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FulldialogComponent ],
      imports: [ MatDialogModule ],
      providers: [ {
        provide:MatDialogRef,
        useValue:{}
        },
         {
          provide:MAT_DIALOG_DATA,
         useValue:{}
      }
        ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FulldialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
