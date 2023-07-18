package com.outreachmappingsolutions.outreachmappingsolutions.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="client_contact_info")
public class ClientContactInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    private Integer id;

    @OneToOne
    private ClientBase client;

    private String phonePrimary;
    private String phoneSecondary;
    private String email;

    private String iceName;
    private String iceRelationship;
    private String icePhonePrimary;
    private String icePhoneSecondary;
    private String iceEmail;

    public ClientContactInfo() {
    }

    public ClientContactInfo(ClientBase client, String phonePrimary, String phoneSecondary, String email, String iceName,
                             String iceRelationship, String icePhonePrimary, String icePhoneSecondary, String iceEmail) {
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

    public Integer getId() {
        return id;
    }

    public ClientBase getClient() {
        return client;
    }

    public void setClient(ClientBase client) {
        this.client = client;
    }

    public String getPhonePrimary() {
        return phonePrimary;
    }

    public void setPhonePrimary(String phonePrimary) {
        this.phonePrimary = phonePrimary;
    }

    public String getPhoneSecondary() {
        return phoneSecondary;
    }

    public void setPhoneSecondary(String phoneSecondary) {
        this.phoneSecondary = phoneSecondary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIceName() {
        return iceName;
    }

    public void setIceName(String iceName) {
        this.iceName = iceName;
    }

    public String getIceRelationship() {
        return iceRelationship;
    }

    public void setIceRelationship(String iceRelationship) {
        this.iceRelationship = iceRelationship;
    }

    public String getIcePhonePrimary() {
        return icePhonePrimary;
    }

    public void setIcePhonePrimary(String icePhonePrimary) {
        this.icePhonePrimary = icePhonePrimary;
    }

    public String getIcePhoneSecondary() {
        return icePhoneSecondary;
    }

    public void setIcePhoneSecondary(String icePhoneSecondary) {
        this.icePhoneSecondary = icePhoneSecondary;
    }

    public String getIceEmail() {
        return iceEmail;
    }

    public void setIceEmail(String iceEmail) {
        this.iceEmail = iceEmail;
    }

}
