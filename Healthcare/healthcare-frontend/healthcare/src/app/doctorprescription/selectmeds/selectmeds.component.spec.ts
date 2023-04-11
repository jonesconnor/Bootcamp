import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectmedsComponent } from './selectmeds.component';

describe('SelectmedsComponent', () => {
  let component: SelectmedsComponent;
  let fixture: ComponentFixture<SelectmedsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectmedsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelectmedsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
