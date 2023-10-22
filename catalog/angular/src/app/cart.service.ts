import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from './Cart';
import { Product } from './Product';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseUrl = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) { }

  // addToCart(id: number,quantity: number = 1): Observable<String> {
  //   return this.http.post<String>(`${this.baseUrl}/cart/${id}/add`, {}, {params: {
  //     quantity: quantity.toString()
  //   }});
  // }

  getProduct(): Observable<Product> {
    return this.http.get<Product>(`${this.baseUrl}/list`);
  }

  // removeFromCart(id: number, quantity: number = 1): Observable<Cart> {
  //   return this.http.post<Cart>(`${this.baseUrl}/cart/${id}/remove`, {}, {params: {quantity: quantity.toString()}});
  // }

  // checkoutFromCart(id: number): Observable<Cart> {
  //   return this.http.post<Cart>(`${this.baseUrl}/cart/checkout/${id}`, {} );
  // }

  // checkoutAll(): Observable<Cart> {
  //   return this.http.post<Cart>(`${this.baseUrl}/cart/checkout/all`, {});
  // }
}
