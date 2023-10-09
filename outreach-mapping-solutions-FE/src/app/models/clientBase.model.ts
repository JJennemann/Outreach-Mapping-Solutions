export class ClientBase {
    public id: number;

    public firstName: string;
    public middleName: string;
    public lastName: string;

    public dobMonth: string;
    public dobDay: number;
    public dobYear: number;

    public firstThreeSsn: number;
    public middleTwoSsn: number;
    public lastFourSsn: number;

    public displayName: string;
    public displayDob: string;
    public displaySsn: string;

    constructor(firstName?: string, middleName?: string, lastName?:string,
                dobMonth?: string, dobDay?: number, dobYear?: number,
                firstThreeSsn?: number, middleTwoSsn?: number, lastFourSsn?: number){

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dobMonth = dobMonth;
        this.dobDay = dobDay;
        this.dobYear = dobYear;
        this.firstThreeSsn = firstThreeSsn;
        this.middleTwoSsn = middleTwoSsn;
        this.lastFourSsn = lastFourSsn;
    }

setDisplayFields(displayName: string, displayDob: string, displaySsn: string){
    this.displayName = displayName;
    this.displayDob = displayDob;
    this.displaySsn = displaySsn;
}
}