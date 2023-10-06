import { Component, EventEmitter, Output } from '@angular/core';
import { Client } from 'src/app/models/client.model';
import { ClientPortalService } from 'src/app/services/client-portal.service';
import { NgForm } from '@angular/forms';
import { ClientDemographics } from 'src/app/models/client-demographics.model';
import { HttpClient } from '@angular/common/http';
import { ClientBase } from 'src/app/models/clientBase.model';

@Component({
  selector: 'app-client-search',
  templateUrl: './client-search.component.html',
  styleUrls: ['./client-search.component.css']
})
export class ClientSearchComponent {
  
  clientToAdd: ClientBase;
  formFirstName: string;
  formMiddleName: string;
  formLastName: string;
  formDobMonth: string;
  formDobDay: number;
  formDobYear: number;
  formSsnFirstThree: number;
  formSsnMiddleTwo: number;
  formSsnLastFour: number;


  dataQuality: string[];
  monthsDays: {month: string, days: number}[];
  days: number[];
  @Output() returnedClients = new EventEmitter<Client[]>();

constructor(private clientPortalService: ClientPortalService, private http: HttpClient){
  this.dataQuality = this.clientPortalService.dataQuality;
  this.monthsDays = this.clientPortalService.monthsDays;

  this.clientPortalService.days.subscribe(
    (days) => this.days = days
  );
}

monthSelected(event: Event){
  this.clientPortalService.selectedMonth(event);
}

allClients = this.clientPortalService.getAllClients();

clientSearch(){
  this.clientPortalService.allClientsEmitted.emit(this.allClients) 
}




addToDatabase(){
  this.clientToAdd = new ClientBase(this.formFirstName, this.formMiddleName, this.formLastName, this.formDobMonth,
                                this.formDobDay, this.formDobYear, this.formSsnFirstThree, this.formSsnMiddleTwo, this.formSsnLastFour);
  // this.clientPortalService.addClientToDatabase(this.clientToAdd);

    this.http.post('http://localhost:8080/clientBase/create', this.clientToAdd)
    .subscribe(responseData => {
      console.log(responseData);
    });

  }


}
