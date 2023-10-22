import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { CyclesComponent } from './cycles/cycles.component';
import { FormsModule } from '@angular/forms';
import { RequestInterceptor } from './request.interceptor';
import { NavbarComponent } from './navbar/navbar.component';
import { BaseLayoutComponent } from './base-layout/base-layout.component';
import { SiteLayoutComponent } from './site-layout/site-layout.component';
import { AuthClickDirective } from './auth-click.directive';
import { CartComponent } from './cart/cart.component';
import { OrdersComponent } from './orders/orders.component';
import { ProductComponent } from './product/product.component';
import { FeatureComponent } from './feature/feature.component';
import { ParameterComponent } from './parameter/parameter.component';
import { ReactiveFormsModule } from '@angular/forms'; 


@NgModule({
  declarations: [AppComponent, RegisterComponent, LoginComponent, CyclesComponent, NavbarComponent, BaseLayoutComponent, SiteLayoutComponent, AuthClickDirective, CartComponent, OrdersComponent, ProductComponent, FeatureComponent, ParameterComponent],
  imports: [BrowserModule, HttpClientModule, AppRoutingModule, FormsModule, ReactiveFormsModule],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }