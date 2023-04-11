import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Product } from './models/Product';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  private wishlistSubject = new BehaviorSubject<Product[]>([]);
  public wishlist = this.wishlistSubject.asObservable();

  constructor() { }

  addToWishlist(product: Product) {
    const currentWishlist = this.wishlistSubject.value;
    const updatedWishlist = [...currentWishlist, product];
    this.wishlistSubject.next(updatedWishlist);
    console.log(updatedWishlist);
  }

  removeFromWishlist(productId: number) {
    const currentWishlist = this.wishlistSubject.value;
    const updatedWishlist = currentWishlist.filter(product => product.id !== productId);
    this.wishlistSubject.next(updatedWishlist);
  }

  clearWishlist() {
    this.wishlistSubject.next([]);
  }

  getWishlist(): Observable<Product[]> {
    return this.wishlist;
  }

  itemExistsInWishlist(productId: number): boolean {
    const currentWishlist = this.wishlistSubject.value;
    return currentWishlist.some(product => product.id === productId);
  }
}
