import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { NavigationComponent } from './navigation/navigation.component';
import { ClientPortalComponent } from './portals/client-portal/client-portal.component';


import { UserInfoComponent } from './header/user-info/user-info.component';
import { SearchComponent } from './header/search/search.component';
import { SetDateComponent } from './header/set-date/set-date.component';
import { ClientProfileComponent } from './portals/client-portal/client-profile/client-profile.component';
import { ClientProfileNavBarComponent } from './portals/client-portal/client-profile/client-profile-nav-bar/client-profile-nav-bar.component';
import { ClientSearchComponent } from './portals/client-portal/client-search/client-search.component';
import { ClientSearchResultsListComponent } from './portals/client-portal/client-search/client-search-results-list/client-search-results-list.component';
import { ClientProfileOverviewComponent } from './portals/client-portal/client-profile/client-profile-overview/client-profile-overview.component';
import { ClientProfileAssessmentsComponent } from './portals/client-portal/client-profile/client-profile-assessments/client-profile-assessments.component';
import { ClientProfileCaseNotesComponent } from './portals/client-portal/client-profile/client-profile-case-notes/client-profile-case-notes.component';
import { ClientProfileMapComponent } from './portals/client-portal/client-profile/client-profile-map/client-profile-map.component';
import { ClientProfileOverviewPictureComponent } from './portals/client-portal/client-profile/client-profile-overview/client-profile-overview-picture/client-profile-overview-picture.component';
import { ClientProfileOverviewDemographicsComponent } from './portals/client-portal/client-profile/client-profile-overview/client-profile-overview-demographics/client-profile-overview-demographics.component';
import { ClientProfileOverviewContactInfoComponent } from './portals/client-portal/client-profile/client-profile-overview/client-profile-overview-contact-info/client-profile-overview-contact-info.component';
import { ClientProfileOverviewCaseManagersComponent } from './portals/client-portal/client-profile/client-profile-overview/client-profile-overview-case-managers/client-profile-overview-case-managers.component';
import { MapsPortalComponent } from './portals/maps-portal/maps-portal.component';
import { HomePageComponent } from './home-page/home-page.component';
import { PortalsComponent } from './portals/portals.component';
import { ReportsPortalComponent } from './portals/reports-portal/reports-portal.component';
import { DemographicsEditComponent } from './portals/client-portal/client-profile/client-profile-overview/client-profile-overview-demographics/demographics-edit/demographics-edit.component';
import { ContactInfoEditComponent } from './portals/client-portal/client-profile/client-profile-overview/client-profile-overview-contact-info/contact-info-edit/contact-info-edit.component';
import { ClientMapComponent } from './portals/client-portal/client-profile/client-profile-map/client-map/client-map.component';
import { InteractionListComponent } from './portals/client-portal/client-profile/client-profile-map/interaction-list/interaction-list.component';
import { InteractionFormComponent } from './portals/client-portal/client-profile/client-profile-map/interaction-form/interaction-form.component';




@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavigationComponent,
    ClientPortalComponent,
    ClientSearchComponent,
    UserInfoComponent,
    SearchComponent,
    SetDateComponent,
    ClientProfileComponent,
    ClientProfileNavBarComponent,
    ClientProfileOverviewDemographicsComponent,
    ClientSearchResultsListComponent,
    ClientProfileOverviewComponent,
    ClientProfileAssessmentsComponent,
    ClientProfileCaseNotesComponent,
    ClientProfileMapComponent,
    ClientProfileOverviewPictureComponent,
    ClientProfileOverviewContactInfoComponent,
    ClientProfileOverviewCaseManagersComponent,
    ReportsPortalComponent,
    MapsPortalComponent,
    HomePageComponent,
    PortalsComponent,
    DemographicsEditComponent,
    ContactInfoEditComponent,
    ClientMapComponent,
    InteractionListComponent,
    InteractionFormComponent,

    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
