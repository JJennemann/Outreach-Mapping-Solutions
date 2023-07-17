import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactInfoEditComponent } from './contact-info-edit.component';

describe('ContactInfoEditComponent', () => {
  let component: ContactInfoEditComponent;
  let fixture: ComponentFixture<ContactInfoEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContactInfoEditComponent]
    });
    fixture = TestBed.createComponent(ContactInfoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
