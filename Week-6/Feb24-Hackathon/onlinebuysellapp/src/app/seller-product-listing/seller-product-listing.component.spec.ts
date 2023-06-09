import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerProductListingComponent } from './seller-product-listing.component';

describe('SellerProductListingComponent', () => {
  let component: SellerProductListingComponent;
  let fixture: ComponentFixture<SellerProductListingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellerProductListingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SellerProductListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
