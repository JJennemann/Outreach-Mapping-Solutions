import { Component, Input} from '@angular/core';
import { Client } from 'src/app/models/client.model';



@Component({
  selector: 'app-client-search-results-list',
  templateUrl: './client-search-results-list.component.html',
  styleUrls: ['./client-search-results-list.component.css']
})
export class ClientSearchResultsListComponent{
  @Input() clientsReturned: Client[];


}
