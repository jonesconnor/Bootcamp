import { MessageSellerDialogComponent } from './../message-seller-dialog/message-seller-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { WishlistService } from './../wishlist.service';
import { Component, OnInit } from '@angular/core';
import { Product } from '../models/Product';

@Component({
  selector: 'app-viewwishlist',
  templateUrl: './viewwishlist.component.html',
  styleUrls: ['./viewwishlist.component.css']
})
export class ViewwishlistComponent implements OnInit {

  public wishlist: Product[] = [];

  constructor(private wishlistservice: WishlistService, public dialog: MatDialog) {}

  ngOnInit(): void {
    this.wishlistservice.getWishlist().subscribe(wishlist => {
      this.wishlist = wishlist;
    });
    this.wishlistservice.wishlist.subscribe(wishlist=> {
      this.wishlist = wishlist;
    })
  }

  messageSeller(product: Product) {
    const dialogRef = this.dialog.open(MessageSellerDialogComponent, {
      width: '500px',
      data: { productId: product.id, sellerId: product.sellerId}
    });
  }

}
