import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientSearchResultsListComponent } from './client-search-results-list.component';

describe('ClientSearchResultsListComponent', () => {
  let component: ClientSearchResultsListComponent;
  let fixture: ComponentFixture<ClientSearchResultsListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientSearchResultsListComponent]
    });
    fixture = TestBed.createComponent(ClientSearchResultsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
