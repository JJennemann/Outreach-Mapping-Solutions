import { Component, Input, OnInit} from '@angular/core';
import { Client } from 'src/app/models/client.model';
import { ClientPortalService } from 'src/app/services/client-portal.service';



@Component({
  selector: 'app-client-search-results-list',
  templateUrl: './client-search-results-list.component.html',
  styleUrls: ['./client-search-results-list.component.css']
})
export class ClientSearchResultsListComponent implements OnInit{
  // @Input() returnedClients: Client[];



  // constructor(private clientPortalService: ClientPortalService){
  //   this.clientPortalService.allClientsEmitted.subscribe(
  //     (clientsReturned: Client[]) => this.returnedClients = clientsReturned
  //   );
  // }
  
  ngOnInit(): void {
  //  this.returnedClients = this.clientPortalService.getAllClients();
  }

  // onClientSelected(clientId: number){
  //   this.clientPortalService.setClientIdSelected(clientId);
  // }

}
