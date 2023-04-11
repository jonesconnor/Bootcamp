import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MrdisplayComponent } from './mrdisplay.component';

describe('MrdisplayComponent', () => {
  let component: MrdisplayComponent;
  let fixture: ComponentFixture<MrdisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MrdisplayComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MrdisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
