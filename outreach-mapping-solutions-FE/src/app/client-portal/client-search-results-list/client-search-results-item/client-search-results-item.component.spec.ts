import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientSearchResultsItemComponent } from './client-search-results-item.component';

describe('ClientSearchResultsItemComponent', () => {
  let component: ClientSearchResultsItemComponent;
  let fixture: ComponentFixture<ClientSearchResultsItemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientSearchResultsItemComponent]
    });
    fixture = TestBed.createComponent(ClientSearchResultsItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
