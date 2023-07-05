export class ClientContactInfo{
    public phonePrimary: string;
    public phoneSecondary: string;
    public email: string;
    public iceName: string;
    public iceRelationship: string;
    public icePhonePrimary: string;
    public icePhoneSecondary: string;
    public iceEmail: string;

    constructor(phonePrimary: string, phoneSecondary: string, email: string, 
                iceName: string, iceRelationship: string, icePhonePrimary: string, icePhoneSecondary: string, iceEmail: string){
        this.phonePrimary = phonePrimary;
        this.phoneSecondary = phoneSecondary;
        this.email = email;
        this.iceName = iceName;
        this.iceRelationship = iceRelationship;
        this.icePhonePrimary = icePhonePrimary;
        this.icePhoneSecondary = icePhoneSecondary;
        this.iceEmail = iceEmail;
    }
}