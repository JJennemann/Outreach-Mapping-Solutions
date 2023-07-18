import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemographicsEditComponent } from './demographics-edit.component';

describe('DemographicsEditComponent', () => {
  let component: DemographicsEditComponent;
  let fixture: ComponentFixture<DemographicsEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DemographicsEditComponent]
    });
    fixture = TestBed.createComponent(DemographicsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
