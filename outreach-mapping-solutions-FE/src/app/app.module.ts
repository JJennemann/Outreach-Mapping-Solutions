import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { NavigationComponent } from './navigation/navigation.component';
import { ClientPortalComponent } from './client-portal/client-portal.component';
import { ClientSearchComponent } from './client-portal/client-search/client-search.component';
import { ClientSearchResultsListComponent } from './client-portal/client-search-results-list/client-search-results-list.component';

import { UserInfoComponent } from './header/user-info/user-info.component';
import { SearchComponent } from './header/search/search.component';
import { NavItemComponent } from './navigation/nav-item/nav-item.component';
import { SetDateComponent } from './header/set-date/set-date.component';
import { ClientProfileComponent } from './client-portal/client-profile/client-profile.component';
import { CaseNotesComponent } from './client-portal/client-profile/case-notes/case-notes.component';
import { AssessmentsComponent } from './client-portal/client-profile/assessments/assessments.component';
import { MapComponent } from './client-portal/client-profile/map/map.component';
import { DemographicsComponent } from './client-portal/client-profile/overview/demographics/demographics.component';
import { ContactInformationComponent } from './client-portal/client-profile/overview/contact-information/contact-information.component';
import { PhotoComponent } from './client-portal/client-profile/overview/photo/photo.component';
import { OverviewComponent } from './client-portal/client-profile/overview/overview.component';
import { ClientProfileNavBarComponent } from './client-portal/client-profile/client-profile-nav-bar/client-profile-nav-bar.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavigationComponent,
    ClientPortalComponent,
    ClientSearchComponent,
    ClientSearchResultsListComponent,
    UserInfoComponent,
    SearchComponent,
    NavItemComponent,
    SetDateComponent,
    ClientProfileComponent,
    CaseNotesComponent,
    AssessmentsComponent,
    MapComponent,
    DemographicsComponent,
    ContactInformationComponent,
    PhotoComponent,
    OverviewComponent,
    ClientProfileNavBarComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
