import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Client } from 'src/app/models/client.model';
import { ClientPortalService } from 'src/app/services/client-portal.service';

@Component({
  selector: 'app-client-portal',
  templateUrl: './client-portal.component.html',
  styleUrls: ['./client-portal.component.css']
})
export class ClientPortalComponent {

  dataQuality= ["Complete", "Partial", "Client Did Not Know", "Client Refused", "Data Not Collected"]
  monthsDays=[
    {month: 'January',
     days: 30},
     {month: 'February',
     days: 29},
     {month: 'March',
     days: 31},
     {month: 'April',
     days: 30},
     {month: 'May',
     days: 31},
     {month: 'June',
     days: 30},
     {month: 'July',
     days: 31},
     {month: 'August',
     days: 31},
     {month: 'September',
     days: 30},
     {month: 'October',
     days: 31},
     {month: 'November',
     days: 30},
     {month: 'December',
     days: 31},
  ]

  days: number[]=[];
  days29=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29];
  days30=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30];
  days31=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31];


  months31Days=["January", "March", "May", "July", "August", "October", "December"];
  months30Days=["April", "June", "September", "November"];
  
  formData={
    firstName: '',
    middleName: '',
    lastName: '',

  }

@Output() returnedClients = new EventEmitter<Client[]>();

constructor(private clientPortalService: ClientPortalService){
  // this.showSearchForm = this.clientPortalService.showSearchFormOnClientPortal;
  // this.clientPortalService.showSearchFormOnClientPortal.subscribe(
  //   () => !this.showSearchForm
  // );



  
}



  monthSelected(event: Event){
    const monthSelected = (event.target as HTMLSelectElement).value
    if(this.months31Days.includes(monthSelected)){
      this.days = this.days31;
    }
    if(this.months30Days.includes(monthSelected)){
      this.days=this.days30;
    }
    if(monthSelected==='February'){
      this.days=this.days29;
    }
  }


  clientSearch(){
    this.clientPortalService.allClientsEmitted.emit(this.clientPortalService.getAllClients())

    
  }

  // clientSearch(form: NgForm){
  //   // const clientSearched = new Client()
  //   // const firstNameValue=this.formData.firstName;
  //   // const middleNameValue=this.formData.middleName;
  //   // const lastNameValue=this.formData.lastName;

  //   // this.searchResults = this.clientPortalService.getClientReturnedByFormElements();
    
  // }
}
