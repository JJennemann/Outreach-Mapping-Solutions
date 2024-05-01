import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ClientContactInfo } from 'src/app/models/client-contact-info.model';
import { ClientBase } from 'src/app/models/clientBase.model';
import { ClientPortalService } from 'src/app/services/client-portal.service';

@Component({
  selector: 'app-client-profile-overview-contact-info',
  templateUrl: './client-profile-overview-contact-info.component.html',
  styleUrls: ['./client-profile-overview-contact-info.component.css']
})
export class ClientProfileOverviewContactInfoComponent implements OnInit{

  activeClient: ClientBase;

constructor(private clientPortalService: ClientPortalService){
  this.activeClient = this.clientPortalService.currentClient;
  
}

ngOnInit(): void {
    
}

  // clientContactInfo: ClientContactInfo;
  // clientReturnedId: number;

  // constructor(private clientPortalService: ClientPortalService, private route: ActivatedRoute){
  // this.clientContactInfo = this.clientPortalService.getClientContactInfoById(this.clientReturnedId);
  
  // }

  // ngOnInit(): void {
  //   this.route.params.subscribe((params: Params) => {
  //     this.clientReturnedId = +params['id'];
  //   })
  //   this.clientContactInfo = this.clientPortalService.getClientContactInfoById(this.clientReturnedId);
  //   console.log(this.clientReturnedId);
  // }

  // loadClientContactInfoAndOpenModal(){
  //   this.clientContactInfo = this.clientPortalService.getClientContactInfoById(this.clientReturnedId);
  //   console.log(this.clientContactInfo);
  // }
}
