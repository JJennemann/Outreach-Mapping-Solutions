import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileOverviewPictureComponent } from './client-profile-overview-picture.component';

describe('ClientProfileOverviewPictureComponent', () => {
  let component: ClientProfileOverviewPictureComponent;
  let fixture: ComponentFixture<ClientProfileOverviewPictureComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientProfileOverviewPictureComponent]
    });
    fixture = TestBed.createComponent(ClientProfileOverviewPictureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
