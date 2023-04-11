import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchproviderComponent } from './searchprovider.component';

describe('SearchproviderComponent', () => {
  let component: SearchproviderComponent;
  let fixture: ComponentFixture<SearchproviderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchproviderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchproviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
