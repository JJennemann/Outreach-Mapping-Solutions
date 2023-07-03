import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetDateComponent } from './set-date.component';

describe('SetDateComponent', () => {
  let component: SetDateComponent;
  let fixture: ComponentFixture<SetDateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SetDateComponent]
    });
    fixture = TestBed.createComponent(SetDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
