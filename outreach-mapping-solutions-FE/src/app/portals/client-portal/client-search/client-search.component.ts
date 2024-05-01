import { Component } from '@angular/core';
import { ClientPortalService } from 'src/app/services/client-portal.service';
import { ClientBase } from 'src/app/models/clientBase.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-search',
  templateUrl: './client-search.component.html',
  styleUrls: ['./client-search.component.css']
})
export class ClientSearchComponent {
  resultsReturned: Boolean = false;
  returnedSearchResults: ClientBase[];
  clientBaseToCreate: ClientBase;
  newClient: ClientBase;  

  formFirstName: string;
  formMiddleName: string;
  formLastName: string;
  formNameDataQuality: string;

  formDobMonth: string;
  formDobDay: string;
  formDobYear: string;
  formDobDataQuality: string;

  formSsnFirstThree: string;
  formSsnMiddleTwo: string;
  formSsnLastFour: string;
  formSsnDataQuality: string;

  dataQuality: string[];
  monthsDays: {month: string, days: string}[];
  days: string[];


constructor(private clientPortalService: ClientPortalService, private router: Router){
  this.dataQuality = this.clientPortalService.dataQuality;
  this.monthsDays = this.clientPortalService.monthsDays;


  this.clientPortalService.days.subscribe(
    (days) => this.days = days
  );
}

monthSelected(event: Event){
  const monthSelected = (event.target as HTMLSelectElement).value;

  this.clientPortalService.selectedMonth(monthSelected);
}

searchClient(){
this.resultsReturned=true;

this.clientPortalService.getAllClients().subscribe(responseData => {
  this.returnedSearchResults = <ClientBase[]> responseData;
});
}

createNewClientBase(){
  const clientBaseToCreate = new ClientBase(this.formFirstName, this.formMiddleName, this.formLastName, this.formNameDataQuality, this.formDobMonth,
                          this.formDobDay, this.formDobYear,this.formDobDataQuality, this.formSsnFirstThree, this.formSsnMiddleTwo, this.formSsnLastFour, this.formSsnDataQuality);
  
  this.addClient(clientBaseToCreate);
}

addClient(clientToAdd: ClientBase){
  this.clientPortalService.postNewClientBase(clientToAdd).subscribe(responseData => {
    this.newClient = <ClientBase> responseData;
    this.addClientRoute();
    this.setCurrentClient(this.newClient);
  });
}

addClientRoute(){
  this.router.navigate(['/client-portal', 'profile', this.newClient.id, 'overview']);
}

setCurrentClient(clientToSet: ClientBase){
  this.clientPortalService.setCurrentClient(clientToSet);
}

}
