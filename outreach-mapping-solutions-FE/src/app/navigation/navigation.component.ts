import { Component } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {
navItems = [
  {displayName: "Client Portal",
   pathName: "client-portal"},
   {displayName: "Reports Portal",
   pathName: "reports-portal"},
   {displayName: "Maps Portal",
   pathName: "maps-portal"},
   {displayName: "Log Out",
   pathName: "logout"}
];

  
}
