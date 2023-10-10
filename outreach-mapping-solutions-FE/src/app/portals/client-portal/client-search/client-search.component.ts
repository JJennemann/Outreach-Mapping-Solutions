import { Component, EventEmitter, Output } from '@angular/core';
import { ClientPortalService } from 'src/app/services/client-portal.service';
import { HttpClient } from '@angular/common/http';
import { ClientBase } from 'src/app/models/clientBase.model';
import { Router } from '@angular/router';
import { forEachChild } from 'typescript';

@Component({
  selector: 'app-client-search',
  templateUrl: './client-search.component.html',
  styleUrls: ['./client-search.component.css']
})
export class ClientSearchComponent {
  resultsReturned: Boolean = false;
  clientSearchResults: ClientBase[];

  clientToAdd: ClientBase;

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

  newClientId: string;


  dataQuality: string[];
  monthsDays: {month: string, days: string}[];
  days: string[];
  // @Output() returnedClients = new EventEmitter<Client[]>();

constructor(private clientPortalService: ClientPortalService, private http: HttpClient, private router: Router){
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
this.resultsReturned=true;

this.http.get('http://localhost:8080/clientBase/returnAll').subscribe(
  (response)=>{
    const jsonResponse = JSON.parse(JSON.stringify(response));
    this.clientSearchResults=jsonResponse;
    // this.formatSearchResults(this.clientSearchResults);
  }
)
  // this.clientPortalService.allClientsEmitted.emit(this.allClients) 
}

// formatSearchResults(searchResults: ClientBase[]){

//   for(let client of searchResults){

   
//     // client.setDisplayFields(tempDisplayName, tempDisplayDob, tempDisplaySsn);
   
//   }
// }



// Creating the clientBase object, then sending it via POST request to backend, saving returned response, assigning clientID, 
// and navigating to client profile
// -- Need to break up into multiple methods!!
addToDatabase(){
  this.clientToAdd = new ClientBase(this.formFirstName, this.formMiddleName, this.formLastName, this.formNameDataQuality, this.formDobMonth,
                                this.formDobDay, this.formDobYear,this.formDobDataQuality, this.formSsnFirstThree, this.formSsnMiddleTwo, this.formSsnLastFour, this.formSsnDataQuality);
  // this.clientPortalService.addClientToDatabase(this.clientToAdd);
    console.log(this.clientToAdd);
    this.http.post('http://localhost:8080/clientBase/create', this.clientToAdd)
    .subscribe((responseData)=> {
      console.log('Response: ', responseData);

      const jsonResponse = JSON.parse(JSON.stringify(responseData));
    
      this.newClientId = jsonResponse.id;
      this.router.navigate(['/client-portal', 'profile', this.newClientId, 'overview'])
      
    });
  }

}
