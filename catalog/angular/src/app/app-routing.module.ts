import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';

import { BaseLayoutComponent } from './base-layout/base-layout.component';
import { SiteLayoutComponent } from './site-layout/site-layout.component';

import { ProductComponent } from './product/product.component';
import { FeatureComponent } from './feature/feature.component';
import { ParameterComponent } from './parameter/parameter.component';
import { SummaryComponent } from './summary/summary.component';


const routes: Routes = [
  {
    path: '',
    component: BaseLayoutComponent,
    children: [
      { path: 'login', component: LoginComponent },
    ]
  },
  {
    path: '',
    component: SiteLayoutComponent,
    children: [
      {path: 'product', component: ProductComponent},
      {path: 'feature', component: FeatureComponent},
      {path: 'parameter', component: ParameterComponent},
      {path: 'summary', component: SummaryComponent}
    ]
  },

  {path: '', redirectTo: '/login', pathMatch: 'full'},

   
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
