import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ClientPortalService } from 'src/app/services/client-portal.service';

@Component({
  selector: 'app-client-profile-nav-bar',
  templateUrl: './client-profile-nav-bar.component.html',
  styleUrls: ['./client-profile-nav-bar.component.css']
})
export class ClientProfileNavBarComponent implements OnInit {
clientIdUrl: number;

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

constructor(private clientPortalService: ClientPortalService) {
}

ngOnInit(): void {
// this.route.parent.params.subscribe((params: Params) => {
//     this.clientIdUrl = +params['id'];
//   });
this.clientIdUrl = this.clientPortalService.currentClient.id;

}




  // this.router.navigate(['../'], {relativeTo: this.route});
  // this.router.navigate(['/servers', id, 'edit'], {queryParams: {allowEdit: '1'}, fragment: 'loading'});

  




// ngOnInit(): void {

//   this.clientIdUrl = +this.route.snapshot.params['id']
//   this.route.params.subscribe((params: Params) => {
//     this.clientIdUrl = +params['id'];
//   })
// }
}
