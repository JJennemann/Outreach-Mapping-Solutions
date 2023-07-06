import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileOverviewCaseManagersComponent } from './client-profile-overview-case-managers.component';

describe('ClientProfileOverviewCaseManagersComponent', () => {
  let component: ClientProfileOverviewCaseManagersComponent;
  let fixture: ComponentFixture<ClientProfileOverviewCaseManagersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientProfileOverviewCaseManagersComponent]
    });
    fixture = TestBed.createComponent(ClientProfileOverviewCaseManagersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
