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

const routes: Routes = [
  { path: '', redirectTo: '/portal', pathMatch:'full'},

  { path: 'client-portal', component: ClientPortalComponent, children:[
    {path: 'search', component: ClientSearchComponent },
   
  ] },
  {path: 'client/:id', component: ClientProfileComponent, children:[
    {path: 'overview', component: ClientProfileOverviewComponent}
  ]},
  { path: 'reports-portal', component: ReportsPortalComponent },
  { path: 'maps-portal', component: MapsPortalComponent },
  { path: 'logout', component: HomePageComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
