import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileAssessmentsComponent } from './client-profile-assessments.component';

describe('ClientProfileAssessmentsComponent', () => {
  let component: ClientProfileAssessmentsComponent;
  let fixture: ComponentFixture<ClientProfileAssessmentsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientProfileAssessmentsComponent]
    });
    fixture = TestBed.createComponent(ClientProfileAssessmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
