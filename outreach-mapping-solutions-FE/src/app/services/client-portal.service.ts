import {  EventEmitter, Injectable, Output } from '@angular/core';
import { Client } from '../models/client.model';
import { ClientDemographics } from '../models/client-demographics.model';
import { ClientContactInfo } from '../models/client-contact-info.model';
import { Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ClientPortalService {

    


    dataQuality= ["Complete", "Partial", "Client Did Not Know", "Client Refused", "Data Not Collected"]


    private allClients: Client[] =[
        new Client(1, 'Jean-Luc', ' ', 'Picard', 'July', 13, 2305, 111, 11, 1111),
        new Client(2, 'Jane', 'D', 'Doe', 'February', 5, 2000, 222, 22, 2222),
        new Client(3, 'Luke', 'Vader', 'Skywalker', 'December', 9, 1984, 333, 33, 3333),
        new Client(4, 'Leia', 'Padme', 'Doe', 'July', 22, 1948, 444, 44, 4444),
        new Client(5, 'Cee', 'Three', 'PeeOh', 'November', 1, 1988, 555, 55, 5555)
    ]

    private allClientDemographics: ClientDemographics[] =[
     new ClientDemographics(1, 1, "Male", "White/Caucasian", "Not Applicable", "Non-Hispanic", "Veteran"),
     new ClientDemographics(2, 2, "Female", "Asian/Pacific Islander", "Black/African American", "Non-Hispanic", "Not a Veteran"),
     new ClientDemographics(3, 3, "Male", "White/Caucasian", "Not Applicable", "Non-Hispanic", "Veteran"),
     new ClientDemographics(4, 4, "Female", "White/Caucasian", "Not Applicable", "Non-Hispanic", "Not a Veteran"),
     new ClientDemographics(5, 5, "Male", "White/Caucasian", "Not Applicable", "Non-Hispanic", "Not a Veteran"),
    ]

    private allClientContactInfo: ClientContactInfo[] = [
        new ClientContactInfo(1, 1, "111-111-1111", "111-111-1112", "jean-luc@example.com", "Beverley Crusher", "Friend", "111-111-1113", " ", "bCrusher@example.com"),
        new ClientContactInfo(2, 2, "222-222-2221", "222-222-2222", "jane.doe@example.com", "Ellen Fitzgerald", "Mother", "222-222-2223", "222-222-2224", "efitzgerald@example.com"),
        new ClientContactInfo(3, 3, "333-333-3331", "333-333-3332", "luke.skywalker@example.com", "Darth Vader", "Father", "333-333-3333", " ", "darthV@example.com"),
        new ClientContactInfo(4, 4, "444-444-4441", " ", "leia.doe@example.com", "Han Solo", "Ex-Husband", "444-444-4443", " ", "han@example.com"),
        new ClientContactInfo(5, 5, "555-555-5551", " ", "c3po@example.com", "Artoo Detoo", "Friend", "555-555-5554", " ", "r2d2@example.com"),
    ]  
    
    addClientToDatabase(newClient: Client){
        this.allClients.push(newClient);
        this.allClientDemographics.push(new ClientDemographics(6))
        this.allClientContactInfo.push(new ClientContactInfo(6));
        console.log(this.allClients);
    }

    clientToReturnById: Client;
    clientDemographicsToReturnById: ClientDemographics;
    clientContactInfoToReturnById: ClientContactInfo

    @Output() allClientsEmitted=new EventEmitter<Client[]>();
    // @Output() clientSelected = new EventEmitter<number>();
    clientIdSelected: number;

    setClientIdSelected(clientId: number){
        this.clientIdSelected = clientId;
    }

    getClientIdSelected(){
        return this.clientIdSelected;
    }

    updateClient(updatedClient: Client){
        const clientIndex = this.allClients.findIndex((client => client.id === updatedClient.id));
        this.allClients[clientIndex] = updatedClient;
        

    }

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
        for(let clientDemo of this.allClientDemographics){
            if(clientDemo.id === id){
                this.clientDemographicsToReturnById = clientDemo;
            }
        }
        return this.clientDemographicsToReturnById;
    }

    getClientContactInfoById(id: number){
        for(let clientContactInfo of this.allClientContactInfo){
            if(clientContactInfo.id===id){
                this.clientContactInfoToReturnById = clientContactInfo;
            }
        }
        return this.clientContactInfoToReturnById;
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
