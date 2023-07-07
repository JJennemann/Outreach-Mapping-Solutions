import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-client-profile-nav-bar',
  templateUrl: './client-profile-nav-bar.component.html',
  styleUrls: ['./client-profile-nav-bar.component.css']
})
export class ClientProfileNavBarComponent {


navItems = [
  {displayName: "Overview",
   pathName: "overview"},
   {displayName: "Map",
   pathName: "map"},
   {displayName: "Assessments",
   pathName: "assessments"},
   {displayName: "Case Notes",
   pathName: "case-notes"}
];

constructor() {
  
}

}
