import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Empsample2Component } from './empsample2.component';

describe('Empsample2Component', () => {
  let component: Empsample2Component;
  let fixture: ComponentFixture<Empsample2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Empsample2Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Empsample2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
