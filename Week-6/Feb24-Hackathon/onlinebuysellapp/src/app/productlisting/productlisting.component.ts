import { MatSnackBar } from '@angular/material/snack-bar';
import { WishlistService } from './../wishlist.service';
import { AuthService } from './../auth.service';
import { ProducthandlerService } from './../producthandler.service';
import { Component, OnInit } from '@angular/core';
import { Product } from '../models/Product';

@Component({
  selector: 'app-productlisting',
  templateUrl: './productlisting.component.html',
  styleUrls: ['./productlisting.component.css']
})
export class ProductlistingComponent implements OnInit{

  dataSource: any;
  products: Array<Product> = [];
  sellerIds: number[] = [];
  selectedSellerId: number | null = null;

  constructor(private producthandler: ProducthandlerService, private auth: AuthService, private wishlistService: WishlistService, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.producthandler.getProducts().subscribe(
      (res:any) => {
        this.products = res;
        this.dataSource = this.products;
        this.sellerIds = Array.from(new Set(res.map((product: Product) => product.sellerId)));
      },
      (err:any) => {
        console.log(err);
      }
    );
    this.producthandler.productList.subscribe((products: Product[]) => {
      this.products = products;
      this.dataSource = this.products;
    })
  }

  onSellerFilterChange(): void {
    if (this.selectedSellerId === null) {
      this.loadProducts();
    } else {
      this.producthandler.getProductsBySeller(this.selectedSellerId).subscribe(
        (products: Product[]) => {
          this.products = products;
          this.dataSource = this.products;
        },
        (err: any) => {
          console.error(err);
        }
      );
    }
  }

  clearSellerFilter(): void {
    this.selectedSellerId = null;
    this.loadProducts();
  }

  addToWishlist(product: Product) {
    const productId = product.id
    if (productId && this.wishlistService.itemExistsInWishlist(productId)) {
      this.snackBar.open('Product already exists in wishlist', 'Close', {
        duration: 5000
      });
      return;
    }
    console.log(product);
    this.wishlistService.addToWishlist(product);
    this.snackBar.open('Product added to wishlist', 'Close', {
      duration: 5000
    });
  }
}
