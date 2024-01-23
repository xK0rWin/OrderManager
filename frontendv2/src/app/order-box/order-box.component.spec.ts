import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderBoxComponent } from './order-box.component';

describe('OrderBoxComponent', () => {
  let component: OrderBoxComponent;
  let fixture: ComponentFixture<OrderBoxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OrderBoxComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OrderBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
