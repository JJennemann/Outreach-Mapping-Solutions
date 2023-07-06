import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgForm,FormsModule } from '@angular/forms';
import { Client } from 'src/app/models/client.model';
import { ClientPortalService } from 'src/app/services/client-portal.service';
import { PortalsComponent } from '../../portals.component';

@Component({
  selector: 'app-client-search',
  templateUrl: './client-search.component.html',
  styleUrls: ['./client-search.component.css']
})
export class ClientSearchComponent implements OnInit {
  returnedClients: Client[];



  constructor(private clientPortalService: ClientPortalService){
    this.clientPortalService.allClientsEmitted.subscribe(
      (clientsReturned: Client[]) => this.returnedClients = clientsReturned
    );
  }
  
  ngOnInit(): void {
   this.returnedClients = this.clientPortalService.getAllClients();
  }

}
