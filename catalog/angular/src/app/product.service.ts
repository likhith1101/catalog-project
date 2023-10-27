import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Product } from './Product';
import { Feature } from './Feature';

@Injectable({
	providedIn: 'root'
})
export class ProductService {
	private baseUrl = 'http://localhost:8080/api/products';

	constructor(private http: HttpClient) { }

	getData(): Observable<any[]> {
		return this.http.get<any[]>(`${this.baseUrl}/list`);
	}
	

// 	addProduct(id:number,details:string,name:string,internalName:string,maxProductsPerLocation:number,): Observable<String> {
//     return this.http.post<String>(`${this.baseUrl}/add`, {}, {params: {
//       maxProductsPerLocation: maxProductsPerLocation.toString()
//     }});
//   }

addProduct(newProduct:Product): Observable<String> {
    // Use the actual URL and payload for your specific API.
    return this.http.post<string>(`${this.baseUrl}/add`, newProduct);
  }

  editProduct(productId: number, updatedProduct: Product): Observable<Product> {
    const url = `${this.baseUrl}/${productId}`; // Use the actual URL for updating a product by productId

    return this.http.put<Product>(url, updatedProduct);
  }

  deleteProduct(productId: number): Observable<Product> {
    const url = `${this.baseUrl}/${productId}`; // Use the actual URL for updating a product by productId

    return this.http.delete<Product>(url);
  }

  addFeatureToProduct(productId:number, newFeature:Feature): Observable<Feature> {
	// Use the actual URL and payload for your specific API.
	const url = `${this.baseUrl}/${productId}/add-feature`; // Use the actual URL for updating a product by productId

    return this.http.post<Feature>(url, newFeature);
  }

  getFeaturesByProductId(productId:number): Observable<any[]> {
	return this.http.get<any[]>(`${this.baseUrl}/${productId}/features`);
}





//   editProduct(productId: number, updatedProduct: Product): Observable<Product> {
//     // Use the actual URL for updating a product by productId
//     const url = `${this.baseUrl}/${productId}`;

//     return this.http.put<Product>(url, updatedProduct);
//   }

  // saveEditedProduct(product: Product): Observable<Product> {
  //   const editUrl = `${this.baseUrl}/${product.id}`;
  //   return this.http.put<Product>(editUrl, product);
  // }

  

	// borrowCycle(cycleId: number): Observable<any[]> {
	// 	const borrowUrl = `${this.baseUrl}/${cycleId}/borrow`;
	// 	return this.http.post<any[]>(borrowUrl, {});
	// }

	// returnCycle(cycleId: number): Observable<any[]> {
	// 	const returnUrl = `${this.baseUrl}/${cycleId}/return`;
	// 	return this.http.post<any[]>(returnUrl, null);
	// }

	// restockCycle(cycleId: number, quantity: number): Observable<any[]> {
	// 	const restockUrl = `${this.baseUrl}/${cycleId}/restock`;
	// 	return this.http.post<any[]>(restockUrl,{}, {params: {quantity: quantity.toString()}});
	// }

	// public save(cycle: Cycle): Observable<Cycle> {
	// 	return this.http.post<Cycle>(`${this.baseUrl}/addCycle`, cycle);
	// }

	// Add a method to fetch data after every action
	// fetchDataAfterAction(action: () => Observable<any[]>): Observable<any[]> {
	// 	return action().pipe(
	// 		switchMap(() => this.getData())
	// 	);
	// }

	// getAllOrders(): Observable<any[]> {
	// 	return this.http.get<any[]>(`${this.baseUrl}/orders`);
	// }
}
