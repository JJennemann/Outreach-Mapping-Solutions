import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileOverviewContactInfoComponent } from './client-profile-overview-contact-info.component';

describe('ClientProfileOverviewContactInfoComponent', () => {
  let component: ClientProfileOverviewContactInfoComponent;
  let fixture: ComponentFixture<ClientProfileOverviewContactInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientProfileOverviewContactInfoComponent]
    });
    fixture = TestBed.createComponent(ClientProfileOverviewContactInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
