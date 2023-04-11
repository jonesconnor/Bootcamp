import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpcardComponent } from './empcard.component';

describe('EmpcardComponent', () => {
  let component: EmpcardComponent;
  let fixture: ComponentFixture<EmpcardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpcardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
