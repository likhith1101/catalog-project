import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';
import { Cycle } from './cycle';
// import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'cycleshop';
}
