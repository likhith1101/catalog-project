import { CartItem } from "./cart-item";
import { Cycle } from "./cycle";

export interface Cart  {
    id: number,
    totalQuantity: number,
    totalPrice: number,
    cartItems: CartItem[]
}