import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportsPortalComponent } from './reports-portal.component';

describe('ReportsPortalComponent', () => {
  let component: ReportsPortalComponent;
  let fixture: ComponentFixture<ReportsPortalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReportsPortalComponent]
    });
    fixture = TestBed.createComponent(ReportsPortalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
