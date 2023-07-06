import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileMapComponent } from './client-profile-map.component';

describe('ClientProfileMapComponent', () => {
  let component: ClientProfileMapComponent;
  let fixture: ComponentFixture<ClientProfileMapComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientProfileMapComponent]
    });
    fixture = TestBed.createComponent(ClientProfileMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
