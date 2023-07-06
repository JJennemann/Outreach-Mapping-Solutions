import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileCaseNotesComponent } from './client-profile-case-notes.component';

describe('ClientProfileCaseNotesComponent', () => {
  let component: ClientProfileCaseNotesComponent;
  let fixture: ComponentFixture<ClientProfileCaseNotesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientProfileCaseNotesComponent]
    });
    fixture = TestBed.createComponent(ClientProfileCaseNotesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
