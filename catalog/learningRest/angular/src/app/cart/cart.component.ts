import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { Cart } from '../Cart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart?: Cart;

  constructor(private cartService: CartService) { }
  ngOnInit(): void {
    this.getCart();
  }

  getCart(): void {
    this.cartService.getCart().subscribe(cart => this.cart = cart);
  }

  removeFromCart(id: number, quantity: number = 1): void {
    this.cartService.removeFromCart(id, quantity).subscribe(res => this.cart = res)
  }

  checkoutFromCart(id: number): void {
    this.cartService.checkoutFromCart(id).subscribe(res => this.cart = res);
  }
  checkoutAll(): void {
    this.cartService.checkoutAll().subscribe(res => this.cart = res);
  }
}
