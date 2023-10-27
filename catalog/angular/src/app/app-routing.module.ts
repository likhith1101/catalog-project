import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component'; // Assuming you have a login component

import { BaseLayoutComponent } from './base-layout/base-layout.component';
import { SiteLayoutComponent } from './site-layout/site-layout.component';
import { ProductComponent } from './product/product.component';
import { FeatureComponent } from './feature/feature.component';
import { ParameterComponent } from './parameter/parameter.component';
import { SummaryComponent } from './summary/summary.component';
import { AdminGuard } from './admin.guard';
import { UserGuard } from './user.guard'; // Correct the import statement if needed






const routes: Routes = [
  {
    path: '',
    component: BaseLayoutComponent,
    children: [
      { path: 'login', component: LoginComponent }, // Assuming you have a LoginComponent
    ]
  },
  {
    path: '',
    component: SiteLayoutComponent,
    children: [
      { path: 'product', component: ProductComponent, canActivate: [AdminGuard] },
      { path: 'feature', component: FeatureComponent, canActivate: [AdminGuard] },
      { path: 'parameter', component: ParameterComponent, canActivate: [AdminGuard] },
      { path: 'summary', component: SummaryComponent },
    ]
  },
  // Remove the section related to 'login' module
  { path: '', redirectTo: '/login', pathMatch: 'full' },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
