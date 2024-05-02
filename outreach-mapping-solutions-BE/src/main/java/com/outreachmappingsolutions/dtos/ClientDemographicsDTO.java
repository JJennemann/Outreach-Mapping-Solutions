package com.outreachmappingsolutions.dtos;

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

    public ClientDemographicsDTO(Integer id, Integer clientId, LocalDateTime createdAt, LocalDateTime lastModified,
                                 String gender, String racePrimary, String raceSecondary, String ethnicity,
                                 String veteranStatus) {
        this.id = id;
        this.clientId = clientId;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.gender = gender;
        this.racePrimary = racePrimary;
        this.raceSecondary = raceSecondary;
        this.ethnicity = ethnicity;
        this.veteranStatus = veteranStatus;
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
