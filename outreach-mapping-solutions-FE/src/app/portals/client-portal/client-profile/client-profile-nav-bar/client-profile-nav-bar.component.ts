import { Component } from '@angular/core';

@Component({
  selector: 'app-client-profile-nav-bar',
  templateUrl: './client-profile-nav-bar.component.html',
  styleUrls: ['./client-profile-nav-bar.component.css']
})
export class ClientProfileNavBarComponent {
navItems=["Overview", "Map", "Assessments", "Case Notes"];

}
