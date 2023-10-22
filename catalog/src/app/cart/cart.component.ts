import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { Cart } from '../Cart';
import { Product } from '../Product';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  product?: Product;

  constructor(private cartService: CartService) { }
  ngOnInit(): void {
    this.getProduct();
  }

  getProduct(): void {
    this.cartService.getProduct().subscribe(product => this.product = product);
  }

  // removeFromCart(id: number, quantity: number = 1): void {
  //   this.cartService.removeFromCart(id, quantity).subscribe(res => this.cart = res)
  // }

  // checkoutFromCart(id: number): void {
  //   this.cartService.checkoutFromCart(id).subscribe(res => this.cart = res);
  // }
  // checkoutAll(): void {
  //   this.cartService.checkoutAll().subscribe(res => this.cart = res);
  // }
}
