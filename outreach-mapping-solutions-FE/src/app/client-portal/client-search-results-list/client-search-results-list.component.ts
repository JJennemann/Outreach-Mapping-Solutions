import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/models/client.model';
import { ClientPortalService } from '../client-portal.service';

@Component({
  selector: 'app-client-search-results-list',
  templateUrl: './client-search-results-list.component.html',
  styleUrls: ['./client-search-results-list.component.css']
})
export class ClientSearchResultsListComponent implements OnInit{
  clientsReturned: Client[];


  constructor(private clientPortalService: ClientPortalService){}

  ngOnInit(): void {
    this.clientsReturned = this.clientPortalService.getClientsReturned();
  }
}
