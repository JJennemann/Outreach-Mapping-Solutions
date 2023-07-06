import {  EventEmitter, Injectable, Output } from '@angular/core';
import { Client } from '../models/client.model';
import { ClientDemographics } from '../models/client-demographics.model';
import { ClientContactInfo } from '../models/client-contact-info.model';


@Injectable({
  providedIn: 'root'
})
export class ClientPortalService {
    private allClients: Client[] =[
        new Client(1, 'John', 'Amos', 'Doe', 1, 1, 1999, 123, 45, 6789),
        new Client(2, 'Jane', 'D', 'Doe', 2, 5, 2000, 698, 13, 5313),
        new Client(3, 'Luke', 'Vader', 'Skywalker', 12, 9, 1984, 543, 65, 1344),
        new Client(4, 'Leia', 'Padme', 'Doe', 7, 22, 1948, 655, 77, 3453),
        new Client(5, 'Cee', 'Three', 'PeeOh', 11, 1, 1988, 456, 23, 1131)
    ]

    private clientIdFourDemographics: ClientDemographics = new ClientDemographics("Female", "White/Caucasian", "Not Applicable", "Non-Hispanic", "Not a Veteran");
    private clientIdFourContactInfo: ClientContactInfo = new ClientContactInfo("123-456-7890", "098-765-4321", "leia.doe@example.com", "Han Solo", "Ex-Husband", "113-535-7524", "Not Applicable", "han@example.com"  )

    clientToReturnById: Client;

    allClientsEmitted=new EventEmitter<Client[]>();



    getAllClients(){
        return this.allClients.slice();
    }

    getClientReturnedById(id: number,){
        for(let client of this.allClients){
            if(client.id === id){
                this. clientToReturnById = client;
            }
        }
        return this.allClients[id];
    }
    getClientReturnedByFormElements(client: Client){
    }

    getClientReturned(index: number){
        return this.allClients[index];
    }

    getClientIdFourDemographics(){
        return this.clientIdFourDemographics;
    }

    getClientIdFourContactInfo(){
        return this.clientIdFourContactInfo;
    }

}
