

import { Component, ElementRef, OnInit, ViewChild, Renderer2} from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ClientDemographics } from 'src/app/models/client-demographics.model';

import { Client } from 'src/app/models/client.model';
import { ClientPortalService } from 'src/app/services/client-portal.service';

@Component({
  selector: 'app-client-profile-overview-demographics',
  templateUrl: './client-profile-overview-demographics.component.html',
  styleUrls: ['./client-profile-overview-demographics.component.css']
})
export class ClientProfileOverviewDemographicsComponent implements OnInit  {
  clientReturned: Client;
  clientDemographics: ClientDemographics;
  clientReturnedId: number;

constructor(private clientPortalService: ClientPortalService, private route: ActivatedRoute){
  this.clientReturned = this.clientPortalService.getClientReturnedById(this.clientReturnedId);
  this.clientDemographics = this.clientPortalService.getClientDemographicsById(this.clientReturnedId);
}

ngOnInit(): void {
  this.route.params.subscribe((params: Params) => {
    this.clientReturnedId = +params['id'];
    this.clientReturned = this.clientPortalService.getClientReturnedById(this.clientReturnedId);
    this.clientDemographics = this.clientPortalService.getClientDemographicsById(this.clientReturnedId);
  });
  }
  
  handleUpdatedClient(updatedClient: Client){
    this.clientReturned = {...updatedClient};
  }

  handleUpdatedDemographics(updatedClientDemographics: ClientDemographics){
    this.clientDemographics = {...updatedClientDemographics};
  }
}

