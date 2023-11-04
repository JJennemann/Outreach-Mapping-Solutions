

import { Component, ElementRef, OnInit, ViewChild, Renderer2} from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ClientContactInfo } from 'src/app/models/client-contact-info.model';
import { ClientDemographics } from 'src/app/models/client-demographics.model';

import { ClientBase } from 'src/app/models/clientBase.model';
import { ClientPortalService } from 'src/app/services/client-portal.service';

@Component({
  selector: 'app-client-profile-overview-demographics',
  templateUrl: './client-profile-overview-demographics.component.html',
  styleUrls: ['./client-profile-overview-demographics.component.css']
})
export class ClientProfileOverviewDemographicsComponent implements OnInit  {

activeClient: ClientBase;
// activeClientDemographics: ClientDemographics;


constructor(private clientPortalService: ClientPortalService){
  this.activeClient = this.clientPortalService.currentClient;
  // this.activeClientDemographics = this.activeClient.clientDemographics;
}

ngOnInit(): void {
  // this.activeClient = this.clientPortalService.currentClient;
  // this.activeClientDemographics = this.activeClient.clientDemographics;
}

//   clientReturned: Client;
//   clientDemographics: ClientDemographics;
//   clientReturnedId: number;

// constructor(private clientPortalService: ClientPortalService, private route: ActivatedRoute){
//   this.clientReturned = this.clientPortalService.getClientReturnedById(this.clientReturnedId);
//   this.clientDemographics = this.clientPortalService.getClientDemographicsById(this.clientReturnedId);
// }

// ngOnInit(): void {
//   this.route.params.subscribe((params: Params) => {
//     this.clientReturnedId = +params['id'];
//     this.clientReturned = this.clientPortalService.getClientReturnedById(this.clientReturnedId);
//     this.clientDemographics = this.clientPortalService.getClientDemographicsById(this.clientReturnedId);
//   });
//   }
  
//   handleUpdatedClient(updatedClient: Client){
//     this.clientReturned = {...updatedClient};
//   }

//   handleUpdatedDemographics(updatedClientDemographics: ClientDemographics){
//     this.clientDemographics = {...updatedClientDemographics};
//   }
}

