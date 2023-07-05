export class Client {
    public id: number;

    public firstName: string;
    public middleName: string;
    public lastName: string;

    public dobMonth: number;
    public dobDay: number;
    public dobYear: number

    public firstThreeSsn: number;
    public middleTwoSsn: number;
    public lastFourSsn: number;

    constructor(id: number, firstName: string, middleName: string, lastName:string,
                dobMonth: number, dobDay: number, dobYear: number,
                firstThreeSsn: number, middleTwoSsn: number, lastFourSsn: number){
        this.id = id;
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

}