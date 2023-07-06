import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileOverviewDemographicsComponent } from './client-profile-overview-demographics.component';

describe('ClientProfileOverviewDemographicsComponent', () => {
  let component: ClientProfileOverviewDemographicsComponent;
  let fixture: ComponentFixture<ClientProfileOverviewDemographicsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientProfileOverviewDemographicsComponent]
    });
    fixture = TestBed.createComponent(ClientProfileOverviewDemographicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
