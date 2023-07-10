import {  EventEmitter, Injectable, Output } from '@angular/core';
import { Client } from '../models/client.model';
import { ClientDemographics } from '../models/client-demographics.model';
import { ClientContactInfo } from '../models/client-contact-info.model';


@Injectable({
  providedIn: 'root'
})
export class ClientPortalService {
    dataQuality= ["Complete", "Partial", "Client Did Not Know", "Client Refused", "Data Not Collected"]


    private allClients: Client[] =[
        new Client(1, 'John', 'Amos', 'Doe', 'January', 1, 1999, 123, 45, 6789),
        new Client(2, 'Jane', 'D', 'Doe', 'February', 5, 2000, 698, 13, 5313),
        new Client(3, 'Luke', 'Vader', 'Skywalker', 'December', 9, 1984, 543, 65, 1344),
        new Client(4, 'Leia', 'Padme', 'Doe', 'July', 22, 1948, 655, 77, 3453),
        new Client(5, 'Cee', 'Three', 'PeeOh', 'November', 1, 1988, 456, 23, 1131)
    ]

    private allClientDemographics: ClientDemographics[] =[
     new ClientDemographics(1, 1, "Male", "Black/African American", "Not Applicable", "Non-Hispanic", "Is a veteran"),
     new ClientDemographics(2, 2, "Female", "Asian/Pacific Islander", "Black/African American", "Non-Hispanic", "Not a Veteran"),
     new ClientDemographics(3, 3, "Male", "White/Caucasian", "Not Applicable", "Non-Hispanic", "Is a Veteran"),
     new ClientDemographics(4, 4, "Female", "White/Caucasian", "Not Applicable", "Non-Hispanic", "Not a Veteran"),
     new ClientDemographics(5, 5, "Male", "White/Caucasian", "Not Applicable", "Non-Hispanic", "Not a Veteran")
    ]


    private clientIdFourContactInfo: ClientContactInfo = new ClientContactInfo("123-456-7890", "098-765-4321", "leia.doe@example.com", "Han Solo", "Ex-Husband", "113-535-7524", "Not Applicable", "han@example.com"  )

    clientToReturnById: Client;
    clientDemographicsToReturnById: ClientDemographics;

    @Output() allClientsEmitted=new EventEmitter<Client[]>();

    getAllClients(){
        return this.allClients.slice();
    }

    getClientReturnedById(id: number,){
        for(let client of this.allClients){
            if(client.id === id){
                this.clientToReturnById = client;
            }
        }
        return this.clientToReturnById;
    }

  

    getAllClientDemographics(){
       return this.allClientDemographics.slice();
    }

    getClientDemographicsById(id: number){
        for(let client of this.allClientDemographics){
            if(client.id === id){
                this.clientDemographicsToReturnById = client;
            }
        }
        return this.allClientDemographics[id];
    }



    getClientIdFourContactInfo(){
        return this.clientIdFourContactInfo;
    }



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
 
    // formData={
    //     firstName: '',
    //     middleName: '',
    //     lastName: '',

    // }
    
    @Output() days = new EventEmitter<number[]>();
    days29=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29];
    days30=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30];
    days31=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31];
    
    
    months31Days=["January", "March", "May", "July", "August", "October", "December"];
    months30Days=["April", "June", "September", "November"];


    selectedMonth(event: Event){
        const monthSelected = (event.target as HTMLSelectElement).value
        if(this.months31Days.includes(monthSelected)){
            this.days.emit(this.days31);
        }
        if(this.months30Days.includes(monthSelected)){
            this.days.emit(this.days30);

        }
        if(monthSelected==='February'){
            this.days.emit(this.days29);
        }
      }

    clientDobMonth(month:string){
        if(this.months31Days.includes(month)){
            this.days.emit(this.days31);
        }
        if(this.months30Days.includes(month)){
            this.days.emit(this.days30);
        }
        if(month==='February'){
            this.days.emit(this.days29);
        }
    }
}
