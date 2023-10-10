export class ClientBase {
    public id: number;

    public firstName: string;
    public middleName: string;
    public lastName: string;
    public displayName: string;
    public nameDataQuality: string;

    public dobMonth: string;
    public dobDay: string;
    public dobYear: string;
    public displayDob: string;
    public dobDataQuality: string;

    public firstThreeSsn: string;
    public middleTwoSsn: string;
    public lastFourSsn: string;
    public displaySsn: string;
    public ssnDataQuality: string;


    constructor(firstName?: string, middleName?: string, lastName?:string, nameDataQuality?: string,
                dobMonth?: string, dobDay?: string, dobYear?: string, dobDataQuality?: string,
                firstThreeSsn?: string, middleTwoSsn?: string, lastFourSsn?: string, ssnDataQuality?: string){

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.displayName = this.formatClientName(firstName, middleName, lastName);
        this.nameDataQuality = nameDataQuality;

        this.dobMonth = dobMonth;
        this.dobDay = dobDay;
        this.dobYear = dobYear;
        this.displayDob = this.formatClientDob(dobMonth, dobDay, dobYear);
        this.dobDataQuality = dobDataQuality;

        this.firstThreeSsn = firstThreeSsn;
        this.middleTwoSsn = middleTwoSsn;
        this.lastFourSsn = lastFourSsn;
        this.displaySsn = this.formatClientSsn(firstThreeSsn, middleTwoSsn, lastFourSsn);
        this.ssnDataQuality = ssnDataQuality;
    }


formatClientName(firstName: string, middleName: string, lastName: string){
    if(firstName===null || firstName===undefined){
      firstName='';
    }
    if(middleName===null || middleName===undefined){
      middleName='';
    }
    if(lastName===null || lastName===undefined){
      lastName='';
    }
    return lastName + ", " + firstName + " " + middleName;
  }
  
  formatClientDob(month: string, day: string, year: string){
    if(month===null || month===undefined){
        month = '';
    }
    if(day===null || day===undefined){
        day = '';
    }
    if(year===null || year===undefined){
        year = '';
    }
    return month + " " + day + ", " + year;
  }
  
  formatClientSsn(firstThree: string, middleTwo: string, lastFour: string){
  if(firstThree===null || firstThree===undefined){
    firstThree = '';
  }
  
  if(middleTwo===null || middleTwo===undefined){
    middleTwo = '  ';
  }
  
  if(lastFour===null || lastFour===undefined){
    lastFour = '';
  }
  return firstThree + "-" + middleTwo + "-" + lastFour;
  }
  

}