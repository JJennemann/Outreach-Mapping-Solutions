import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { NavigationComponent } from './navigation/navigation.component';
import { ClientPortalComponent } from './client-portal/client-portal.component';
import { ClientSearchComponent } from './client-portal/client-search/client-search.component';
import { ClientSearchResultsListComponent } from './client-portal/client-search-results-list/client-search-results-list.component';
import { ClientSearchResultsItemComponent } from './client-portal/client-search-results-list/client-search-results-item/client-search-results-item.component';
import { UserInfoComponent } from './header/user-info/user-info.component';
import { SearchComponent } from './header/search/search.component';
import { NavItemComponent } from './navigation/nav-item/nav-item.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavigationComponent,
    ClientPortalComponent,
    ClientSearchComponent,
    ClientSearchResultsListComponent,
    ClientSearchResultsItemComponent,
    UserInfoComponent,
    SearchComponent,
    NavItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
