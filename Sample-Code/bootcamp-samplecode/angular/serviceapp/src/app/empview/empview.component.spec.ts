import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpviewComponent } from './empview.component';

describe('EmpviewComponent', () => {
  let component: EmpviewComponent;
  let fixture: ComponentFixture<EmpviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
