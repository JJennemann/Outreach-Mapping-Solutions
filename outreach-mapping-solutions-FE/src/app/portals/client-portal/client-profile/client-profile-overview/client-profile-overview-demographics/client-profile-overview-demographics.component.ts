import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
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
  // clientIdFourDemos: ClientDemographics;
  dataQuality: string[];
  monthsDays: {month: string, days: number}[];
  days: number[];
  clientDemographics: ClientDemographics;
  clientReturnedId: number;

constructor(private clientPortalService: ClientPortalService, private route: ActivatedRoute){
  this.dataQuality = this.clientPortalService.dataQuality;
  this.monthsDays = this.clientPortalService.monthsDays;

this.clientPortalService.days.subscribe((days) => {
  this.days = days
  });

}

ngOnInit(): void {
  this.route.params.subscribe((params: Params) => {
    this.clientReturnedId = +params['id'];
  })
  

  this.clientReturned = this.clientPortalService.getClientReturnedById(this.clientReturnedId);
  this.clientDemographics = this.clientPortalService.getClientDemographicsById(this.clientReturnedId);


  }

monthSelected(event: Event){
  this.clientPortalService.selectedMonth(event);
}

clientDobMonth(month: string){
  this.clientPortalService.clientDobMonth(month);
}

confirmation(){
  console.log(window.location.href)

  
  let response = confirm("Are you sure you want to exit without saving?");

  if(response){
    console.log('okay');
  } else{
    
    
    console.log('cancel');

  }


  // if(result){
  //   return true;
  // } else{
  //   return false;
  // }
}
}
