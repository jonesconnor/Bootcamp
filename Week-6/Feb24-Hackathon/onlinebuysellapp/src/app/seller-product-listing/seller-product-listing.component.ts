import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../auth.service';
import { Product } from '../models/Product';
import { ProducthandlerService } from '../producthandler.service';
import { WishlistService } from '../wishlist.service';

@Component({
  selector: 'app-seller-product-listing',
  templateUrl: './seller-product-listing.component.html',
  styleUrls: ['./seller-product-listing.component.css']
})
export class SellerProductListingComponent implements OnInit{
  dataSource: any;
  products: Array<Product> = [];

  constructor(private producthandler: ProducthandlerService, private auth: AuthService, private wishlistService: WishlistService, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.loadProducts();
    console.log(this.auth.getCurrentUserValue().id)
  }

  loadProducts() {
    this.producthandler.getProducts().subscribe(
      (res: any) => {
        this.products = res.filter((p: Product) => p.sellerId === this.auth.getCurrentUserValue().id);
        this.dataSource = this.products;
        console.log(res);
      },
      (err: any) => {
        console.log(err);
      }
    );
    this.producthandler.productList.subscribe((products: Product[]) => {
      this.products = products.filter((p: Product) => p.sellerId === this.auth.getCurrentUserValue().id);
      this.dataSource = this.products;
    });
  }

  deleteProduct(product: Product) {
    this.producthandler.deleteProduct(product.id as number).subscribe();
  }
}
