import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Cycle } from './cycle';

@Injectable({
	providedIn: 'root'
})
export class ApiService {
	private baseUrl = 'http://localhost:8080/api/cycles';

	constructor(private http: HttpClient) { }

	getData(): Observable<any[]> {
		return this.http.get<any[]>(`${this.baseUrl}/list`);
	}

	borrowCycle(cycleId: number): Observable<any[]> {
		const borrowUrl = `${this.baseUrl}/${cycleId}/borrow`;
		return this.http.post<any[]>(borrowUrl, {});
	}

	returnCycle(cycleId: number): Observable<any[]> {
		const returnUrl = `${this.baseUrl}/${cycleId}/return`;
		return this.http.post<any[]>(returnUrl, null);
	}

	restockCycle(cycleId: number, quantity: number): Observable<any[]> {
		const restockUrl = `${this.baseUrl}/${cycleId}/restock`;
		return this.http.post<any[]>(restockUrl,{}, {params: {quantity: quantity.toString()}});
	}

	// public save(cycle: Cycle): Observable<Cycle> {
	// 	return this.http.post<Cycle>(`${this.baseUrl}/addCycle`, cycle);
	// }

	// Add a method to fetch data after every action
	// fetchDataAfterAction(action: () => Observable<any[]>): Observable<any[]> {
	// 	return action().pipe(
	// 		switchMap(() => this.getData())
	// 	);
	// }

	getAllOrders(): Observable<any[]> {
		return this.http.get<any[]>(`${this.baseUrl}/orders`);
	}
}
