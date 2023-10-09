import { Component, EventEmitter, Output } from '@angular/core';
import { ClientPortalService } from 'src/app/services/client-portal.service';
import { HttpClient } from '@angular/common/http';
import { ClientBase } from 'src/app/models/clientBase.model';
import { Router } from '@angular/router';

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
  
  // this.clientPortalService.allClientsEmitted.emit(this.allClients) 
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
