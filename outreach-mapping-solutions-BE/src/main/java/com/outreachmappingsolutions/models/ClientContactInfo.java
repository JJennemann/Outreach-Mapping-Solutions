package com.outreachmappingsolutions.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="client_contact_info")
public class ClientContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(nullable=false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column (nullable=false)
    private LocalDateTime lastModified;


    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "client_id")
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

    public ClientContactInfo(ClientBase client, String phonePrimary, String phoneSecondary, String email,
                             String iceName, String iceRelationship, String icePhonePrimary,
                             String icePhoneSecondary, String iceEmail) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
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

    @Override
    public String toString() {
        return "ClientContactInfo{" +
                "id=" + id +
                ", client=" + client +
                ", phonePrimary='" + phonePrimary + '\'' +
                ", phoneSecondary='" + phoneSecondary + '\'' +
                ", email='" + email + '\'' +
                ", iceName='" + iceName + '\'' +
                ", iceRelationship='" + iceRelationship + '\'' +
                ", icePhonePrimary='" + icePhonePrimary + '\'' +
                ", icePhoneSecondary='" + icePhoneSecondary + '\'' +
                ", iceEmail='" + iceEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientContactInfo that = (ClientContactInfo) o;
        return Objects.equals(id, that.id) && Objects.equals(client, that.client) && Objects.equals(phonePrimary, that.phonePrimary) && Objects.equals(phoneSecondary, that.phoneSecondary) && Objects.equals(email, that.email) && Objects.equals(iceName, that.iceName) && Objects.equals(iceRelationship, that.iceRelationship) && Objects.equals(icePhonePrimary, that.icePhonePrimary) && Objects.equals(icePhoneSecondary, that.icePhoneSecondary) && Objects.equals(iceEmail, that.iceEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, phonePrimary, phoneSecondary, email, iceName, iceRelationship, icePhonePrimary, icePhoneSecondary, iceEmail);
    }
}
