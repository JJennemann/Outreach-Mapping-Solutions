package com.outreachmappingsolutions.dtos;

import com.outreachmappingsolutions.models.ClientBase;

import java.time.LocalDateTime;

public class CompleteClientEntityDTO {


    private Integer id;

    private LocalDateTime createdAt;
    private LocalDateTime lastModified;

    private String firstName;
    private String middleName;
    private String lastName;
    private String nameDataQuality;
    private String displayName;

    private String dobMonth;
    private String dobDay;
    private String dobYear;
    private String dobDataQuality;
    private String displayDob;

    private String firstThreeSsn;
    private String middleTwoSsn;
    private String lastFourSsn;
    private String ssnDataQuality;
    private String displaySsn;

    private ClientDemographicsDTO clientDemographicsDTO;
    private ClientContactInfoDTO clientContactInfoDTO;

    public CompleteClientEntityDTO() {
    }

    public CompleteClientEntityDTO(Integer id, LocalDateTime createdAt, LocalDateTime lastModified, String firstName,
                                   String middleName, String lastName, String nameDataQuality, String dobMonth,
                                   String dobDay, String dobYear, String dobDataQuality, String firstThreeSsn,
                                   String middleTwoSsn, String lastFourSsn, String ssnDataQuality,
                                   ClientDemographicsDTO clientDemographicsDTO,
                                   ClientContactInfoDTO clientContactInfoDTO) {
        this.id = id;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nameDataQuality = nameDataQuality;
        this.dobMonth = dobMonth;
        this.dobDay = dobDay;
        this.dobYear = dobYear;
        this.dobDataQuality = dobDataQuality;
        this.firstThreeSsn = firstThreeSsn;
        this.middleTwoSsn = middleTwoSsn;
        this.lastFourSsn = lastFourSsn;
        this.ssnDataQuality = ssnDataQuality;
        this.clientDemographicsDTO = clientDemographicsDTO;
        this.clientContactInfoDTO = clientContactInfoDTO;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNameDataQuality() {
        return nameDataQuality;
    }

    public void setNameDataQuality(String nameDataQuality) {
        this.nameDataQuality = nameDataQuality;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDobMonth() {
        return dobMonth;
    }

    public void setDobMonth(String dobMonth) {
        this.dobMonth = dobMonth;
    }

    public String getDobDay() {
        return dobDay;
    }

    public void setDobDay(String dobDay) {
        this.dobDay = dobDay;
    }

    public String getDobYear() {
        return dobYear;
    }

    public void setDobYear(String dobYear) {
        this.dobYear = dobYear;
    }

    public String getDobDataQuality() {
        return dobDataQuality;
    }

    public void setDobDataQuality(String dobDataQuality) {
        this.dobDataQuality = dobDataQuality;
    }

    public String getDisplayDob() {
        return displayDob;
    }

    public void setDisplayDob(String displayDob) {
        this.displayDob = displayDob;
    }


    public String getFirstThreeSsn() {
        return firstThreeSsn;
    }

    public void setFirstThreeSsn(String firstThreeSsn) {
        this.firstThreeSsn = firstThreeSsn;
    }

    public String getMiddleTwoSsn() {
        return middleTwoSsn;
    }

    public void setMiddleTwoSsn(String middleTwoSsn) {
        this.middleTwoSsn = middleTwoSsn;
    }

    public String getLastFourSsn() {
        return lastFourSsn;
    }

    public void setLastFourSsn(String lastFourSsn) {
        this.lastFourSsn = lastFourSsn;
    }

    public String getSsnDataQuality() {
        return ssnDataQuality;
    }

    public void setSsnDataQuality(String ssnDataQuality) {
        this.ssnDataQuality = ssnDataQuality;
    }

    public ClientDemographicsDTO getClientDemographicsDTO() {
        return clientDemographicsDTO;
    }

    public void setClientDemographicsDTO(ClientDemographicsDTO clientDemographicsDTO) {
        this.clientDemographicsDTO = clientDemographicsDTO;
    }

    public ClientContactInfoDTO getClientContactInfoDTO() {
        return clientContactInfoDTO;
    }

    public void setClientContactInfoDTO(ClientContactInfoDTO clientContactInfoDTO) {
        this.clientContactInfoDTO = clientContactInfoDTO;
    }

    public String getDisplaySsn() {
        return displaySsn;
    }

    public void setDisplaySsn(String displaySsn) {
        this.displaySsn = displaySsn;
    }
}
