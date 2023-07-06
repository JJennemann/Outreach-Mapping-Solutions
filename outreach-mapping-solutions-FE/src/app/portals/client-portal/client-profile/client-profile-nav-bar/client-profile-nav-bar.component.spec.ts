import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileNavBarComponent } from './client-profile-nav-bar.component';

describe('ClientProfileNavBarComponent', () => {
  let component: ClientProfileNavBarComponent;
  let fixture: ComponentFixture<ClientProfileNavBarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientProfileNavBarComponent]
    });
    fixture = TestBed.createComponent(ClientProfileNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
