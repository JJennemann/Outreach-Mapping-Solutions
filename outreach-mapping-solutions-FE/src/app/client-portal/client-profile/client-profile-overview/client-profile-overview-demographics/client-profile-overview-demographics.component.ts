import { Component, OnInit } from '@angular/core';
import { ClientDemographics } from 'src/app/models/client-demographics.model';

import { Client } from 'src/app/models/client.model';
import { ClientPortalService } from 'src/app/services/client-portal.service';

@Component({
  selector: 'app-client-profile-overview-demographics',
  templateUrl: './client-profile-overview-demographics.component.html',
  styleUrls: ['./client-profile-overview-demographics.component.css']
})
export class ClientProfileOverviewDemographicsComponent implements OnInit {
  clientReturned: Client;
  clientIdFourDemos: ClientDemographics;

  constructor(private clientPortalService: ClientPortalService){}

  ngOnInit(): void {
    this.clientReturned = this.clientPortalService.getClientReturned(3);
    this.clientIdFourDemos = this.clientPortalService.getClientIdFourDemographics();
  }
}
