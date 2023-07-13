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

  raceSelections: string[] = ["Black/African-American", "White/Caucasian", "Asian/Pacific Islander", "Client Doesn't Know", "Client Refused", "Data Not Collected", "Not Applicable"]
  ethnicitySelections: string[] = ["Hispanic", "Non-Hispanic", "Client Doesn't Know", "Client Refused", "Data Not Collected"]
  genderSelections: string[] = ["Male", "Female", "Trans Male-to-Female", "Trans Female-to-Male", "Non-Binary", "Client Doesn't Know", "Client Refused", "Data Not Collected"]
  veteranSelections: string[] = ["Veteran", "Not a Veteran", "Client Doesn't Know", "Client Refused", "Data Not Collected", "Not Applicable"]

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
  
  loadClientDetailsAndOpenModal(){
    this.clientReturned = this.clientPortalService.getClientReturnedById(this.clientReturnedId);
    this.clientDemographics = this.clientPortalService.getClientDemographicsById(this.clientReturnedId);
    console.log(this.clientReturned)
  }

monthSelected(event: Event){
  this.clientPortalService.selectedMonth(event);
}

clientDobMonth(month: string){
  this.clientPortalService.clientDobMonth(month);
  console.log(this.clientReturned)
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
