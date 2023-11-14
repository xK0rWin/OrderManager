import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderComponent } from './order/order.component';
import { OrderConfirmComponent } from './order-confirm/order-confirm.component';
import { HomeComponent } from './home/home.component';
import { DashboardMealComponent } from './dashboard-meal/dashboard-meal.component';
import { DashboardDrinkComponent } from './dashboard-drink/dashboard-drink.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'order', component: OrderComponent},
  {path: 'order-confirm/:id', component: OrderConfirmComponent},
  {path: 'dashboard-meal', component: DashboardMealComponent},
  {path: 'dashboard-drink', component: DashboardDrinkComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
