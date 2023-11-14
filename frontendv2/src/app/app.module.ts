import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrderComponent } from './order/order.component';
import { OrderConfirmComponent } from './order-confirm/order-confirm.component';
import { HomeComponent } from './home/home.component';
import { DashboardMealComponent } from './dashboard-meal/dashboard-meal.component';
import { DashboardDrinkComponent } from './dashboard-drink/dashboard-drink.component';


@NgModule({
  declarations: [
    AppComponent,
    OrderComponent,
    OrderConfirmComponent,
    HomeComponent,
    DashboardMealComponent,
    DashboardDrinkComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
