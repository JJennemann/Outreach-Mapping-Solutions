import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileOverviewComponent } from './client-profile-overview.component';

describe('ClientProfileOverviewComponent', () => {
  let component: ClientProfileOverviewComponent;
  let fixture: ComponentFixture<ClientProfileOverviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientProfileOverviewComponent]
    });
    fixture = TestBed.createComponent(ClientProfileOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
