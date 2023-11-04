export class ClientDemographics{

    // public id: number;
    // public client: number;
    public gender: string;
    public racePrimary: string;
    public raceSecondary: string;
    public ethnicity: string;
    public veteran: string;


    constructor( gender?: string, racePrimary?: string, raceSecondary?: string, ethnicity?: string, veteran?: string){
        // this.id = id;
        // this.client = client;
        this.gender = gender;
        this.racePrimary = racePrimary;
        this.raceSecondary = raceSecondary;
        this.ethnicity = ethnicity;
        this.veteran = veteran;
    }
}