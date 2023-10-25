import { Feature } from "./Feature";
import { Parameter } from "./Parameter";

export interface Product  {
    id: number,
    details: String,
    internalName: String,
    maxProductsPerLocation: number,
    name: String,
    features: Feature[]
}