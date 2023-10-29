import { Parameter } from "./Parameter";

export interface Feature  {
    featureId: number,
    name: String,
    internalName: String,
    details: String;
    parameters: Parameter[]; 
}