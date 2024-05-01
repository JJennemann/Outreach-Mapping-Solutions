package com.outreachmappingsolutions.dtos;

import com.outreachmappingsolutions.models.ClientBase;

import java.time.LocalDateTime;

public class ClientBaseDTO {


    private Integer id;

    private LocalDateTime createdAt;
    private LocalDateTime lastModified;

    private String firstName;
    private String middleName;
    private String lastName;
    private String nameDataQuality;

    private String dobMonth;
    private Integer dobDay;
    private Integer dobYear;
    private String dobDataQuality;

    private Integer firstThreeSsn;
    private Integer middleTwoSsn;
    private Integer lastFourSsn;
    private String ssnDataQuality;

    private ClientDemographicsDTO clientDemographicsDTO;
    private ClientContactInfoDTO clientContactInfoDTO;

    public ClientBaseDTO() {
    }

    public ClientBaseDTO(ClientBase clientBase) {
        this.id = clientBase.getId();
        this.createdAt = clientBase.getCreatedAt();
        this.lastModified = clientBase.getLastModified();
        this.firstName = clientBase.getFirstName();
        this.middleName = clientBase.getMiddleName();
        this.lastName = clientBase.getLastName();
        this.nameDataQuality = clientBase.getNameDataQuality();
        this.dobMonth = clientBase.getDobMonth();
        this.dobDay = clientBase.getDobDay();
        this.dobYear = clientBase.getDobYear();
        this.dobDataQuality = clientBase.getDobDataQuality();
        this.firstThreeSsn = clientBase.getFirstThreeSsn();
        this.middleTwoSsn = clientBase.getMiddleTwoSsn();
        this.lastFourSsn = clientBase.getLastFourSsn();
        this.ssnDataQuality = clientBase.getSsnDataQuality();
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

    public String getDobMonth() {
        return dobMonth;
    }

    public void setDobMonth(String dobMonth) {
        this.dobMonth = dobMonth;
    }

    public Integer getDobDay() {
        return dobDay;
    }

    public void setDobDay(Integer dobDay) {
        this.dobDay = dobDay;
    }

    public Integer getDobYear() {
        return dobYear;
    }

    public void setDobYear(Integer dobYear) {
        this.dobYear = dobYear;
    }

    public String getDobDataQuality() {
        return dobDataQuality;
    }

    public void setDobDataQuality(String dobDataQuality) {
        this.dobDataQuality = dobDataQuality;
    }

    public Integer getFirstThreeSsn() {
        return firstThreeSsn;
    }

    public void setFirstThreeSsn(Integer firstThreeSsn) {
        this.firstThreeSsn = firstThreeSsn;
    }

    public Integer getMiddleTwoSsn() {
        return middleTwoSsn;
    }

    public void setMiddleTwoSsn(Integer middleTwoSsn) {
        this.middleTwoSsn = middleTwoSsn;
    }

    public Integer getLastFourSsn() {
        return lastFourSsn;
    }

    public void setLastFourSsn(Integer lastFourSsn) {
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
}
