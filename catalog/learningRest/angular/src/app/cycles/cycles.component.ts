import { Component } from '@angular/core';
import { Cycle } from '../cycle';
import { ApiService } from '../api.service';
import { AuthService } from '../auth.service';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cycles',
  templateUrl: './cycles.component.html',
  styleUrls: ['./cycles.component.css']
})
export class CyclesComponent {
  data: Cycle[] = []; // Use the Cycle class for data

  constructor(private api: ApiService,public authService: AuthService, private cartService: CartService) { }

  ngOnInit() {
    this.loadData();
  }

  private loadData() {
    this.api.getData().subscribe(res => this.data = res);
  }

  borrowCycle(cycleId: number): void {
    this.api.borrowCycle(cycleId).subscribe(res => this.data = res);
  }

  returnCycle(cycleId: number): void {
    this.api.returnCycle(cycleId).subscribe(res => this.data = res);
  }

  restockCycle(cycleId: number, quantity: number): void {
    this.api.restockCycle(cycleId, quantity).subscribe(res => this.data = res);
  }

  addToCart(cycleId: number, quantity: number): void {
    this.cartService.addToCart(cycleId,quantity).subscribe(res => alert(`${quantity} cycles added to cart`));
  }
}
