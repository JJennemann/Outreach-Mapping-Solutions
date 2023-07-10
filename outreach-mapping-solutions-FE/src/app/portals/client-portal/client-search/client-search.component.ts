import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Client } from 'src/app/models/client.model';
import { ClientPortalService } from 'src/app/services/client-portal.service';

@Component({
  selector: 'app-client-search',
  templateUrl: './client-search.component.html',
  styleUrls: ['./client-search.component.css']
})
export class ClientSearchComponent {
  dataQuality: string[];
  monthsDays: {month: string, days: number}[];
  days: number[];
  @Output() returnedClients = new EventEmitter<Client[]>();

constructor(private clientPortalService: ClientPortalService){
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
}
