import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderComponent } from './order/order.component';
import { OrderConfirmComponent } from './order-confirm/order-confirm.component';
import { HomeComponent } from './home/home.component';
import { DashboardMealComponent } from './dashboard-meal/dashboard-meal.component';
import { DashboardDrinkComponent } from './dashboard-drink/dashboard-drink.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from '../authguard';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { StatisticsComponent } from './statistics/statistics.component';

const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'order', component: OrderComponent, canActivate: [AuthGuard]},
  {path: 'order-confirm/:id', component: OrderConfirmComponent, canActivate: [AuthGuard]},
  {path: 'dashboard-meal', component: DashboardMealComponent, canActivate: [AuthGuard]},
  {path: 'dashboard-drink', component: DashboardDrinkComponent, canActivate: [AuthGuard]},
  {path: 'admin', component: AdminPanelComponent, canActivate: [AuthGuard]},
  {path: 'statistics', component: StatisticsComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
