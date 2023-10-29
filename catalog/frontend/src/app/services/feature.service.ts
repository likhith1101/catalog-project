import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Feature } from '../Feature';
import { Parameter } from '../Parameter';

@Injectable({
  providedIn: 'root'
})
export class FeatureService {

  private baseUrl = 'http://localhost:8080/api/features';

	constructor(private http: HttpClient) { }

	getData(): Observable<any[]> {
		return this.http.get<any[]>(`${this.baseUrl}/list`);
	}

	

	addFeature(newFeature:Feature): Observable<String> {
		// Use the actual URL and payload for your specific API.
		return this.http.post<string>(`${this.baseUrl}/add`, newFeature);
	  }

	  editFeature(featureId: number, updatedFeature: Feature): Observable<Feature> {
		const url = `${this.baseUrl}/${featureId}`; // Use the actual URL for updating a product by productId
	
		return this.http.put<Feature>(url, updatedFeature);
	  }
	
	  addParameterToFeature(featureId:number, newParameter:Parameter): Observable<Parameter> {
		// Use the actual URL and payload for your specific API.
		const url = `${this.baseUrl}/${featureId}/add-parameter`; // Use the actual URL for updating a product by productId
	
		return this.http.post<Parameter>(url, newParameter);
	  }

	  getFeaturesForProduct(productId: number): Observable<Feature[]> {
		const url = `${this.baseUrl}/${productId}/features`; // Adjust the URL according to your API
		return this.http.get<Feature[]>(url);
	  }

	  getParametersByFeatureId(featureId: number): Observable<Parameter[]> {
		const url = `${this.baseUrl}/${featureId}/parameters`; // Adjust the URL according to your API
		return this.http.get<Parameter[]>(url);
	  }

	  deleteFeature(featureId: number): Observable<Feature> {
		const url = `${this.baseUrl}/${featureId}`; // Use the actual URL for updating a product by productId
	
		return this.http.delete<Feature>(url);
	  }
	
	  
}

