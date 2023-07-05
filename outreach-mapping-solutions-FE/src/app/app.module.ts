import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { NavigationComponent } from './navigation/navigation.component';
import { ClientPortalComponent } from './client-portal/client-portal.component';
import { ClientSearchFormComponent } from './client-portal/client-search/client-search-form/client-search-form.component';


import { UserInfoComponent } from './header/user-info/user-info.component';
import { SearchComponent } from './header/search/search.component';
import { NavItemComponent } from './navigation/nav-item/nav-item.component';
import { SetDateComponent } from './header/set-date/set-date.component';
import { ClientProfileComponent } from './client-portal/client-profile/client-profile.component';
import { ClientProfileNavBarComponent } from './client-portal/client-profile/client-profile-nav-bar/client-profile-nav-bar.component';
import { ClientSearchComponent } from './client-portal/client-search/client-search.component';
import { ClientSearchResultsListComponent } from './client-portal/client-search/client-search-results-list/client-search-results-list.component';
import { ClientProfileOverviewComponent } from './client-portal/client-profile/client-profile-overview/client-profile-overview.component';
import { ClientProfileAssessmentsComponent } from './client-portal/client-profile/client-profile-assessments/client-profile-assessments.component';
import { ClientProfileCaseNotesComponent } from './client-portal/client-profile/client-profile-case-notes/client-profile-case-notes.component';
import { ClientProfileMapComponent } from './client-portal/client-profile/client-profile-map/client-profile-map.component';
import { ClientProfileOverviewPictureComponent } from './client-portal/client-profile/client-profile-overview/client-profile-overview-picture/client-profile-overview-picture.component';
import { ClientProfileOverviewDemographicsComponent } from './client-portal/client-profile/client-profile-overview/client-profile-overview-demographics/client-profile-overview-demographics.component';
import { ClientProfileOverviewContactInfoComponent } from './client-portal/client-profile/client-profile-overview/client-profile-overview-contact-info/client-profile-overview-contact-info.component';
import { ClientProfileOverviewCaseManagersComponent } from './client-portal/client-profile/client-profile-overview/client-profile-overview-case-managers/client-profile-overview-case-managers.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavigationComponent,
    ClientPortalComponent,
    ClientSearchComponent,
    UserInfoComponent,
    SearchComponent,
    NavItemComponent,
    SetDateComponent,
    ClientProfileComponent,
    ClientProfileNavBarComponent,
    ClientSearchFormComponent,
    ClientProfileOverviewDemographicsComponent,
    ClientSearchResultsListComponent,
    ClientProfileOverviewComponent,
    ClientProfileAssessmentsComponent,
    ClientProfileCaseNotesComponent,
    ClientProfileMapComponent,
    ClientProfileOverviewPictureComponent,
    ClientProfileOverviewContactInfoComponent,
    ClientProfileOverviewCaseManagersComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
