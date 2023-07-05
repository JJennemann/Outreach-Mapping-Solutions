import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemographicsComponent } from './demographics.component';

describe('DemographicsComponent', () => {
  let component: DemographicsComponent;
  let fixture: ComponentFixture<DemographicsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DemographicsComponent]
    });
    fixture = TestBed.createComponent(DemographicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
