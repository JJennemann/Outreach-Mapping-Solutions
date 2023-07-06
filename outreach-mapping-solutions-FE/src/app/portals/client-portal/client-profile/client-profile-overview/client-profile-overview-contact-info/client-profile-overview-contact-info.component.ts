import { Component, OnInit } from '@angular/core';
import { ClientContactInfo } from 'src/app/models/client-contact-info.model';
import { ClientPortalService } from 'src/app/services/client-portal.service';

@Component({
  selector: 'app-client-profile-overview-contact-info',
  templateUrl: './client-profile-overview-contact-info.component.html',
  styleUrls: ['./client-profile-overview-contact-info.component.css']
})
export class ClientProfileOverviewContactInfoComponent implements OnInit{
  clientContactInfo: ClientContactInfo;

  constructor(private clientPortalService: ClientPortalService){

  }

  ngOnInit(): void {
    this.clientContactInfo = this.clientPortalService.getClientIdFourContactInfo();
  }
}
