import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Feature } from './Feature';

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
	
	  
}

