import { Component, Input, OnInit} from '@angular/core';
import { ClientPortalService } from 'src/app/services/client-portal.service';



@Component({
  selector: 'app-client-search-results-list',
  templateUrl: './client-search-results-list.component.html',
  styleUrls: ['./client-search-results-list.component.css']
})
export class ClientSearchResultsListComponent implements OnInit{





////NO LONGER USING CLIENT-SEARCH-RESULTS-LIST Component


  
  ngOnInit(): void {
  //  this.returnedClients = this.clientPortalService.getAllClients();
  }


}
