
import { Component, ElementRef, OnInit, ViewChild, EventEmitter, Output, Input, Renderer2} from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ClientDemographics } from 'src/app/models/client-demographics.model';

import { Client } from 'src/app/models/client.model';
import { ClientPortalService } from 'src/app/services/client-portal.service';

import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-demographics-edit',
  templateUrl: './demographics-edit.component.html',
  styleUrls: ['./demographics-edit.component.css']
})
export class DemographicsEditComponent implements OnInit{
  formClientReturned: Client;
  @Input() updatedFormClient: Client;
  formClientDemographics: ClientDemographics;
  @Input() updatedFormClientDemographics: ClientDemographics;

  // clientIdFourDemos: ClientDemographics;
  dataQuality: string[];
  monthsDays: {month: string, days: number}[];
  days: number[];
  @Output() updatedClient: EventEmitter<Client> = new EventEmitter();
  @Output() updatedClientDemographics: EventEmitter<ClientDemographics> = new EventEmitter();

  @ViewChild('editClientBasicInformationModal') editClientBasicInformationModal:ElementRef;
  @ViewChild('exitWithoutSavingBtn') exitWithoutSavingBtn: ElementRef;

  raceSelections: string[] = ["Black/African-American", "White/Caucasian", "Asian/Pacific Islander", "Client Doesn't Know", "Client Refused", "Data Not Collected", "Not Applicable"];
  ethnicitySelections: string[] = ["Hispanic", "Non-Hispanic", "Client Doesn't Know", "Client Refused", "Data Not Collected"];
  genderSelections: string[] = ["Male", "Female", "Trans Male-to-Female", "Trans Female-to-Male", "Non-Binary", "Client Doesn't Know", "Client Refused", "Data Not Collected"];
  veteranSelections: string[] = ["Veteran", "Not a Veteran", "Client Doesn't Know", "Client Refused", "Data Not Collected", "Not Applicable"];

constructor(private clientPortalService: ClientPortalService, private route: ActivatedRoute, private router: Router, private renderer: Renderer2){
  this.dataQuality = this.clientPortalService.dataQuality;
  this.monthsDays = this.clientPortalService.monthsDays;
  this.formClientReturned = this.updatedFormClient;
  this.formClientDemographics = this.updatedFormClientDemographics;


this.clientPortalService.days.subscribe((days) => {
  this.days = days
  });

}

ngOnInit(): void {
  this.formClientReturned = {...this.updatedFormClient};
  this.formClientDemographics = {...this.updatedFormClientDemographics};
  }

  monthSelected(event: Event){
    this.clientPortalService.selectedMonth(event);
  }
  
  clientDobMonth(month: string){
    this.clientPortalService.clientDobMonth(month);
  }

  saveUpdatedFormData() {
    this.clientPortalService.updateClient(this.formClientReturned);
    this.updatedClient.emit(this.formClientReturned);
    this.updatedClientDemographics.emit(this.formClientDemographics);
  }


// confirmation(){
//     if(confirm("Are you sure you want to exit without saving?")){
//       this.dismissModal();
//     } else{

//     }
//   }

confirmed=false;

  confirmation() {
    if (confirm("Are you sure you want to exit without saving?")) {
      this.exitWithoutSavingBtn.nativeElement.setAttribute('data-bs-dismiss', 'modal');
      this.dismissModal();
      this.exitWithoutSavingBtn.nativeElement.removeAttribute('data-bs-dismiss');
    } else {
    }
  }
  


  resetFormFields(){
    this.formClientReturned = {...this.updatedFormClient};
    this.formClientDemographics = {...this.updatedFormClientDemographics};
  }
  


  dismissModal() {
    this.resetFormFields();
    const modalElement: HTMLElement = this.editClientBasicInformationModal.nativeElement;
    modalElement.classList.remove('show');
    modalElement.style.display = 'none';
    document.body.classList.remove('modal-open');
    const modalBackdropElement: HTMLElement | null = document.querySelector('.modal-backdrop');
    if (modalBackdropElement) {
      modalBackdropElement.remove();
    }
    
    const closeButton: HTMLElement | null = modalElement.querySelector('.exitWithoutSavingBtn');
    if (closeButton) {
      this.renderer.listen(closeButton, 'click', () => {});
      closeButton.click();

  }
  }

  
  
}

  // dismissModal(){
  //   this.resetFormFields();
  //   const modalElement: HTMLElement = this.editClientBasicInformationModal.nativeElement;
  //   modalElement.classList.remove('show');
  //   modalElement.style.display = 'none';
  //   document.body.classList.remove('modal-open');
  //   const modalBackdropElement: HTMLElement | null = document.querySelector('.modal-backdrop');
  //   if(modalBackdropElement){
  //     modalBackdropElement.remove();
  //   }
    
  // }
  // dismissModal() {
  //   this.resetFormFields();
  
  //   const modalElement: HTMLElement = this.editClientBasicInformationModal.nativeElement;
  
  //   // Hide the modal using Bootstrap's JavaScript method
  //   this.renderer.addClass(modalElement, 'hide');
  //   this.renderer.removeClass(modalElement, 'show');
  //   this.renderer.setStyle(modalElement, 'display', 'none');
  //   this.renderer.removeStyle(modalElement, 'padding-right');
  //   this.renderer.removeClass(document.body, 'modal-open');
  //   const modalBackdropElement: HTMLElement | null = document.querySelector('.modal-backdrop');
  //   if (modalBackdropElement) {
  //     this.renderer.removeChild(document.body, modalBackdropElement);
  //   }
  // }



// import { Component, ElementRef, OnInit, ViewChild EventEmitter, Output} from '@angular/core';
// import { ActivatedRoute, Params, Router } from '@angular/router';
// import { ClientDemographics } from 'src/app/models/client-demographics.model';

// import { Client } from 'src/app/models/client.model';
// import { ClientPortalService } from 'src/app/services/client-portal.service';

// @Component({
//   selector: 'app-demographics-edit',
//   templateUrl: './demographics-edit.component.html',
//   styleUrls: ['./demographics-edit.component.css']
// })
// export class DemographicsEditComponent implements OnInit{
//   clientReturned: Client;
//   formClientReturned: Client;
//   // clientIdFourDemos: ClientDemographics;
//   dataQuality: string[];
//   monthsDays: {month: string, days: number}[];
//   days: number[];
//   clientDemographics: ClientDemographics;
//   formClientDemographics: ClientDemographics;
//   clientReturnedId: number;
//   @Output() updatedClient: EventEmitter<Client> = new EventEmitter();

//   raceSelections: string[] = ["Black/African-American", "White/Caucasian", "Asian/Pacific Islander", "Client Doesn't Know", "Client Refused", "Data Not Collected", "Not Applicable"];
//   ethnicitySelections: string[] = ["Hispanic", "Non-Hispanic", "Client Doesn't Know", "Client Refused", "Data Not Collected"];
//   genderSelections: string[] = ["Male", "Female", "Trans Male-to-Female", "Trans Female-to-Male", "Non-Binary", "Client Doesn't Know", "Client Refused", "Data Not Collected"];
//   veteranSelections: string[] = ["Veteran", "Not a Veteran", "Client Doesn't Know", "Client Refused", "Data Not Collected", "Not Applicable"];

//   @ViewChild('editClientBasicInformationModal') editClientBasicInformationModal:ElementRef;


// constructor(private clientPortalService: ClientPortalService, private route: ActivatedRoute, private router: Router){
//   this.dataQuality = this.clientPortalService.dataQuality;
//   this.monthsDays = this.clientPortalService.monthsDays;
//   this.clientReturned = this.clientPortalService.getClientReturnedById(this.clientReturnedId);
//   this.clientDemographics = this.clientPortalService.getClientDemographicsById(this.clientReturnedId);
//   this.formClientReturned = this.clientPortalService.getClientReturnedById(this.clientReturnedId);
//   this.formClientDemographics = this.clientPortalService.getClientDemographicsById(this.clientReturnedId);

// this.clientPortalService.days.subscribe((days) => {
//   this.days = days
//   });

// }

// ngOnInit(): void {
//   this.route.params.subscribe((params: Params) => {
//     this.clientReturnedId = +params['id'];
//     this.clientReturned = this.clientPortalService.getClientReturnedById(this.clientReturnedId);
//     this.clientDemographics = this.clientPortalService.getClientDemographicsById(this.clientReturnedId);
//   })
//   this.formClientReturned = {...this.clientReturned};
//   this.formClientDemographics = {...this.clientDemographics};
//   }

//   monthSelected(event: Event){
//     this.clientPortalService.selectedMonth(event);
//   }
  
//   clientDobMonth(month: string){
//     this.clientPortalService.clientDobMonth(month);
//   }

//   saveUpdatedFormData() {
//     this.clientPortalService.updateClient(this.formClientReturned);
//     this.updatedClient.emit(this.formClientReturned);
//   }


// confirmation(){
//     if(confirm("Are you sure you want to exit without saving?")){
//       this.resetFormFields();
//       this.dismissModal();
//     }else {
//     }
//   }

//   resetFormFields(){
//     this.formClientReturned = {...this.clientReturned};
//     this.formClientDemographics = {...this.clientDemographics};
//   }

//   dismissModal(){
//     this.resetFormFields();
//     const modalElement: HTMLElement = this.editClientBasicInformationModal.nativeElement;
//     modalElement.classList.remove('show');
//     modalElement.style.display = 'none';
//     document.body.classList.remove('modal-open');
//     const modalBackdropElement: HTMLElement | null = document.querySelector('.modal-backdrop');
//     if(modalBackdropElement){
//       modalBackdropElement.remove();
//     }
    
//   }

//   // dismissModal() {
//   //   this.resetFormFields();
  
//   //   const modalElement: HTMLElement = this.editClientBasicInformationModal.nativeElement;
  
//   //   // Hide the modal using Bootstrap's JavaScript method
//   //   this.renderer.addClass(modalElement, 'hide');
//   //   this.renderer.removeClass(modalElement, 'show');
//   //   this.renderer.setStyle(modalElement, 'display', 'none');
//   //   this.renderer.removeStyle(modalElement, 'padding-right');
//   //   this.renderer.removeClass(document.body, 'modal-open');
//   //   const modalBackdropElement: HTMLElement | null = document.querySelector('.modal-backdrop');
//   //   if (modalBackdropElement) {
//   //     this.renderer.removeChild(document.body, modalBackdropElement);
//   //   }
//   // }
  
// }