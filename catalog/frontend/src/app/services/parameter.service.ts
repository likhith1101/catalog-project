import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Parameter } from '../Parameter';

@Injectable({
  providedIn: 'root'
})
export class ParameterService {

  private baseUrl = 'http://localhost:8080/api/parameters';

	constructor(private http: HttpClient) { }

	getData(): Observable<any[]> {
		return this.http.get<any[]>(`${this.baseUrl}/list`);
	}

	addParameter(newParameter: Parameter): Observable<String> {
		// Use the actual URL and payload for your specific API.
		return this.http.post<string>(`${this.baseUrl}/add`, newParameter);
	  }

	  editParameter(parameterId: number, updatedParameter: Parameter): Observable<Parameter> {
		const url = `${this.baseUrl}/${parameterId}`; // Use the actual URL for updating a product by productId
	
		return this.http.put<Parameter>(url, updatedParameter);
	  }

	  deleteParameter(parameterId: number): Observable<Parameter> {
		const url = `${this.baseUrl}/${parameterId}`; // Use the actual URL for updating a product by productId
	
		return this.http.delete<Parameter>(url);
	  }
}
