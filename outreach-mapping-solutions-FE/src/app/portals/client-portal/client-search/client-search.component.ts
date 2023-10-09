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
  displayName: string;
  displayDob: string;
  displaySsn: string;

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
  newClientId: number;


  dataQuality: string[];
  monthsDays: {month: string, days: number}[];
  days: number[];
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
    this.formatSearchResults(this.clientSearchResults);
  }
)

  // this.clientPortalService.allClientsEmitted.emit(this.allClients) 
}

formatSearchResults(searchResults: ClientBase[]){

  for(let client of searchResults){

    let tempDisplayName = this.formatClientName(client.firstName, client.middleName, client.lastName);
    let tempDisplayDob = this.formatClientDob(client.dobMonth, client.dobDay, client.dobYear);
    let tempDisplaySsn = this.formatClientSsn(client.firstThreeSsn, client.middleTwoSsn, client.lastFourSsn);
    // client.setDisplayFields(tempDisplayName, tempDisplayDob, tempDisplaySsn);
    client.displayName = tempDisplayName;
    client.displayDob = tempDisplayDob;
    client.displaySsn = tempDisplaySsn;
  }
}

formatClientName(firstName: string, middleName: string, lastName: string){
  if(firstName===null){
    firstName='';
  }
  if(middleName===null){
    middleName='';
  }
  if(lastName===null){
    lastName='';
  }
  return lastName + ", " + firstName + " " + middleName;
}

formatClientDob(month: string, day: number, year: number){
  let dayString: string = '';
  let yearString: string = '';
  let formattedDob: string = '';

  if(month===null){
    month = '';
    formattedDob += month + " ";
  }
  
  if(day===null){
    formattedDob += dayString + ", ";
  } else{
    formattedDob += day.toString() + ", "
  }

  if(year===null){
    formattedDob += yearString
  } else{
    formattedDob += year.toString()
  }
  return formattedDob;
}

formatClientSsn(firstThree: number, middleTwo: number, lastFour: number){
let firstString: string = '';
let middleString: string = '';
let lastString: string = '';
let formattedSsn: string = '';

if(firstThree===null){
  formattedSsn += firstString + "-";
} else{
  formattedSsn += firstThree.toString() + "-";
}

if(middleTwo===null){
  formattedSsn += middleString + "-";
} else{
  formattedSsn += middleTwo.toString() + "-"
}

if(lastFour===null){
  formattedSsn += lastString;
} else{
  formattedSsn += lastFour.toString();
}
return formattedSsn;
}


// Creating the clientBase object, then sending it via POST request to backend, saving returned response, assigning clientID, 
// and navigating to client profile
// -- Need to break up into multiple methods!!
addToDatabase(){
  this.clientToAdd = new ClientBase(this.formFirstName, this.formMiddleName, this.formLastName, this.formDobMonth,
                                this.formDobDay, this.formDobYear, this.formSsnFirstThree, this.formSsnMiddleTwo, this.formSsnLastFour);
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
