import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardDrinkComponent } from './dashboard-drink.component';

describe('DashboardDrinkComponent', () => {
  let component: DashboardDrinkComponent;
  let fixture: ComponentFixture<DashboardDrinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DashboardDrinkComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DashboardDrinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
