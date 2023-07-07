import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientPortalComponent } from './portals/client-portal/client-portal.component';
import { ReportsPortalComponent } from './portals/reports-portal/reports-portal.component';
import { HomePageComponent } from './home-page/home-page.component';
import { MapsPortalComponent } from './portals/maps-portal/maps-portal.component';
import { Client } from './models/client.model';
import { ClientProfileComponent } from './portals/client-portal/client-profile/client-profile.component';
import { ClientSearchComponent } from './portals/client-portal/client-search/client-search.component';
import { PortalsComponent } from './portals/portals.component';
import { ClientProfileOverviewComponent } from './portals/client-portal/client-profile/client-profile-overview/client-profile-overview.component';
import { ClientSearchResultsListComponent } from './portals/client-portal/client-search/client-search-results-list/client-search-results-list.component';
import { ClientProfileMapComponent } from './portals/client-portal/client-profile/client-profile-map/client-profile-map.component';
import { ClientProfileAssessmentsComponent } from './portals/client-portal/client-profile/client-profile-assessments/client-profile-assessments.component';
import { ClientProfileCaseNotesComponent } from './portals/client-portal/client-profile/client-profile-case-notes/client-profile-case-notes.component';

const routes: Routes = [
  { path: '', redirectTo: '/portal', pathMatch:'full'},

  { path: 'client-portal', redirectTo: '/client-portal/search', pathMatch:'full'},

  { path: 'client-portal', component: ClientPortalComponent, children:[
    {path: 'search', component: ClientSearchComponent, children:[
      {path: 'results', component: ClientSearchResultsListComponent}
    ] },
    { path: ':id', redirectTo:'/client-portal/:id/overview', pathMatch:'full'},
    { path: ':id', component: ClientProfileComponent, children:[
      {path: 'overview', component: ClientProfileOverviewComponent},
      {path: 'map', component: ClientProfileMapComponent},
      {path: 'assessments', component: ClientProfileAssessmentsComponent},
      {path: 'case-notes', component: ClientProfileCaseNotesComponent}
      


    ] }
  ] },
  // {path: ':id', component: ClientProfileComponent, children:[
  //   {path: 'overview', component: ClientProfileOverviewComponent}
  // ]},
  { path: 'reports-portal', component: ReportsPortalComponent },
  { path: 'maps-portal', component: MapsPortalComponent },
  { path: 'logout', component: HomePageComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
