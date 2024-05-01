package com.outreachmappingsolutions.dtos;

import java.time.LocalDateTime;

public class ClientContactInfoDTO {

    private Integer id;
    private Integer clientId;

    private LocalDateTime createdAt;
    private LocalDateTime lastModified;

    private String phonePrimary;
    private String phoneSecondary;
    private String email;

    private String iceName;
    private String iceRelationship;
    private String icePhonePrimary;
    private String icePhoneSecondary;
    private String iceEmail;

    public ClientContactInfoDTO() {
    }

    public ClientContactInfoDTO(Integer id, Integer clientId, LocalDateTime createdAt, LocalDateTime lastModified,
                                String phonePrimary, String phoneSecondary, String email, String iceName,
                                String iceRelationship, String icePhonePrimary, String icePhoneSecondary,
                                String iceEmail) {
        this.id = id;
        this.clientId = clientId;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
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
