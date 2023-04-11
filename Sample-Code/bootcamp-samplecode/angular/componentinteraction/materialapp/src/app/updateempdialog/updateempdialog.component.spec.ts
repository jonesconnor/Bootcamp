import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateempdialogComponent } from './updateempdialog.component';

describe('UpdateempdialogComponent', () => {
  let component: UpdateempdialogComponent;
  let fixture: ComponentFixture<UpdateempdialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateempdialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateempdialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
