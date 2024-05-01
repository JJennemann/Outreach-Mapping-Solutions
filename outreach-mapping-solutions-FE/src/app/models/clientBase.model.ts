import { ClientContactInfo } from './client-contact-info.model';
import { ClientDemographics } from './client-demographics.model';

export class ClientBase {
  public id: number;

  public firstName: string;
  public middleName: string;
  public lastName: string;
  public nameDataQuality: string;

  public dobMonth: string;
  public dobDay: string;
  public dobYear: string;
  public dobDataQuality: string;

  public firstThreeSsn: string;
  public middleTwoSsn: string;
  public lastFourSsn: string;
  public ssnDataQuality: string;

  public clientDemographics: ClientDemographics;
  public clientContactInfo: ClientContactInfo;

  constructor(
    firstName?: string,
    middleName?: string,
    lastName?: string,
    nameDataQuality?: string,
    dobMonth?: string,
    dobDay?: string,
    dobYear?: string,
    dobDataQuality?: string,
    firstThreeSsn?: string,
    middleTwoSsn?: string,
    lastFourSsn?: string,
    ssnDataQuality?: string,
    clientDemographics?: ClientDemographics,
    clientContactInfo?: ClientContactInfo
  ) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.nameDataQuality = nameDataQuality;

    this.dobMonth = dobMonth;
    this.dobDay = dobDay;
    this.dobYear = dobYear;
    this.dobDataQuality = dobDataQuality;

    this.firstThreeSsn = firstThreeSsn;
    this.middleTwoSsn = middleTwoSsn;
    this.lastFourSsn = lastFourSsn;
    this.ssnDataQuality = ssnDataQuality;

    this.clientDemographics = clientDemographics;
    this.clientContactInfo = clientContactInfo;
  }

  // Logic I was using for display versions, don't think necessary? And if I do come back to it, probably should be handled on back end?

  // formatClientName(firstName: string, middleName: string, lastName: string){
  //     if(firstName===null || firstName===undefined){
  //       firstName='';
  //     }
  //     if(middleName===null || middleName===undefined){
  //       middleName='';
  //     }
  //     if(lastName===null || lastName===undefined){
  //       lastName='';
  //     }
  //     return lastName + ", " + firstName + " " + middleName;
  //   }

  //   formatClientDob(month: string, day: string, year: string){
  //     if(month===null || month===undefined){
  //         month = '';
  //     }
  //     if(day===null || day===undefined){
  //         day = '';
  //     }
  //     if(year===null || year===undefined){
  //         year = '';
  //     }
  //     return month + " " + day + ", " + year;
  //   }

  //   formatClientSsn(firstThree: string, middleTwo: string, lastFour: string){
  //   if(firstThree===null || firstThree===undefined){
  //     firstThree = '';
  //   }

  //   if(middleTwo===null || middleTwo===undefined){
  //     middleTwo = '  ';
  //   }

  //   if(lastFour===null || lastFour===undefined){
  //     lastFour = '';
  //   }
  //   return firstThree + "-" + middleTwo + "-" + lastFour;
  //   }
}
