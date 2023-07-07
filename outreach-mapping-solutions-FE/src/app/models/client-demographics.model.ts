export class ClientDemographics{

    public gender: string;
    public racePrimary: string;
    public raceSecondary: string;
    public ethnicity: string;
    public veteran: string;

    constructor(gender: string, racePrimary: string, raceSecondary: string, ethnicity: string, veteran: string){
        this.gender = gender;
        this.racePrimary = racePrimary;
        this.raceSecondary = raceSecondary;
        this.ethnicity = ethnicity;
        this.veteran = veteran;
    }
}