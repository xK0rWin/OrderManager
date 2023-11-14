import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardMealComponent } from './dashboard-meal.component';

describe('DashboardMealComponent', () => {
  let component: DashboardMealComponent;
  let fixture: ComponentFixture<DashboardMealComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DashboardMealComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DashboardMealComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
