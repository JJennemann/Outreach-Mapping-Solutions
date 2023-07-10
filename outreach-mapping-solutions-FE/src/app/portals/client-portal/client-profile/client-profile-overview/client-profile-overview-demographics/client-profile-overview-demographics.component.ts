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
  dataQuality: string[];
  monthsDays: {month: string, days: number}[];
  days: number[];

constructor(private clientPortalService: ClientPortalService){
  this.dataQuality = this.clientPortalService.dataQuality;
  this.monthsDays = this.clientPortalService.monthsDays;

  this.clientPortalService.days.subscribe(
    (days) => this.days = days
  );

  console.log(window.location.href)
}

ngOnInit(): void {
  this.clientReturned = this.clientPortalService.getClientReturned(3);
  this.clientIdFourDemos = this.clientPortalService.getClientIdFourDemographics();
  }

monthSelected(event: Event){
  this.clientPortalService.selectedMonth(event);
}

confirmation(){
  console.log(window.location.href)

  
  const result = window.confirm("Are you sure you want to exit without saving?");

  // if(result){
  //   return true;
  // } else{
  //   return false;
  // }
}
}
