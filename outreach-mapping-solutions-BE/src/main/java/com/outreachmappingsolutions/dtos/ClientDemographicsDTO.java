package com.outreachmappingsolutions.dtos;

import com.outreachmappingsolutions.models.ClientDemographics;

import java.time.LocalDateTime;

public class ClientDemographicsDTO {

    private Integer id;
    private Integer clientId;

    private LocalDateTime createdAt;
    private LocalDateTime lastModified;

    private String gender;
    private String racePrimary;
    private String raceSecondary;
    private String ethnicity;
    private String veteranStatus;

    public ClientDemographicsDTO() {
    }

    public ClientDemographicsDTO(ClientDemographics clientDemographics){
        this.id = clientDemographics.getId();
        this.clientId = clientDemographics.getClient().getId();
        this.createdAt = clientDemographics.getCreatedAt();
        this.lastModified = clientDemographics.getLastModified();
        this.gender = clientDemographics.getGender();
        this.racePrimary = clientDemographics.getRacePrimary();
        this.raceSecondary = clientDemographics.getRaceSecondary();
        this.ethnicity = clientDemographics.getEthnicity();
        this.veteranStatus = clientDemographics.getVeteranStatus();
    }



    public Integer getId() {
        return id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRacePrimary() {
        return racePrimary;
    }

    public void setRacePrimary(String racePrimary) {
        this.racePrimary = racePrimary;
    }

    public String getRaceSecondary() {
        return raceSecondary;
    }

    public void setRaceSecondary(String raceSecondary) {
        this.raceSecondary = raceSecondary;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getVeteranStatus() {
        return veteranStatus;
    }

    public void setVeteranStatus(String veteranStatus) {
        this.veteranStatus = veteranStatus;
    }
}
