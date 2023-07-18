export class ClientContactInfo{
    public id: number;
    public client: number;
    public phonePrimary: string;
    public phoneSecondary: string;
    public email: string;
    public iceName: string;
    public iceRelationship: string;
    public icePhonePrimary: string;
    public icePhoneSecondary: string;
    public iceEmail: string;

    constructor(id: number, client?: number, phonePrimary?: string, phoneSecondary?: string, email?: string, 
                iceName?: string, iceRelationship?: string, icePhonePrimary?: string, icePhoneSecondary?: string, iceEmail?: string){
        this.id = id;
        this.client = client;
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