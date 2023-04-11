import { environment } from './../../environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Product } from './models/Product';

@Injectable({
  providedIn: 'root'
})
export class ProducthandlerService {

  private productListSubject = new BehaviorSubject<Product[]>([]);
  public productList = this.productListSubject.asObservable();

  constructor(private httpclientobj: HttpClient) { }

  getProducts(): Observable<Array<Product>> {
    return this.httpclientobj.get<Array<Product>>(environment.productsAPI)
    .pipe(
      tap((products: Product[]) => {
        this.productListSubject.next(products);
      })
    );
  }

  getProductsBySeller(sellerId: number): Observable<Array<Product>> {
    return this.httpclientobj.get<Array<Product>>(environment.productsAPI + `?sellerId=${sellerId}`);
  }

  addProduct(product: Product): Observable<Product> {
    return this.httpclientobj.post(environment.productsAPI, product, {
      headers: new HttpHeaders({"Content-Type": "application/json"})
    }).pipe(
      tap((newProduct: Product) => {
        const updatedList = [...this.productListSubject.value, newProduct];
        this.productListSubject.next(updatedList);
      })
    )
  }

  updateProduct(product: Product): Observable<Product> {
    const url = `${environment.productsAPI}/${product.id}`;
    return this.httpclientobj.put(url, product, {
      headers: new HttpHeaders({"Content-Type": "application/json"})
    }).pipe(
      tap(() => {
        const updatedList = this.productListSubject.value.map((p: Product) => {
          if (p.id === product.id) {
            return product;
          } else {
            return p;
          }
        });
        this.productListSubject.next(updatedList);
      })
    );
  }

  deleteProduct(id: number): Observable<any> {
    const url = `${environment.productsAPI}/${id}`;
    return this.httpclientobj.delete(url).pipe(
      tap(() => {
        const updatedList = this.productListSubject.value.filter((p: Product) => {
          return p.id !== id;
        });
        this.productListSubject.next(updatedList);
      })
    )
  }

}
