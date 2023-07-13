import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapsPortalComponent } from './maps-portal.component';

describe('MapsPortalComponent', () => {
  let component: MapsPortalComponent;
  let fixture: ComponentFixture<MapsPortalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MapsPortalComponent]
    });
    fixture = TestBed.createComponent(MapsPortalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
