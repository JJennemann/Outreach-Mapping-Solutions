package com.outreachmappingsolutions.models;

import jakarta.persistence.*;

@Entity
@Table(name="client_base_data")
public class ClientBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String nameDataQuality;

    private String dobMonth;
    private String dobDay;
    private String dobYear;
    private String dobDataQuality;

    private Integer firstThreeSsn;
    private Integer middleTwoSsn;
    private Integer lastFourSsn;
    private String ssnDataQuality;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ClientDemographics clientDemographics;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ClientContactInfo clientContactInfo;

    public ClientBase() {
    }

    public ClientBase(String firstName, String middleName, String lastName, String nameDataQuality, String dobMonth,
                      String dobDay, String dobYear, String dobDataQuality, Integer firstThreeSsn,
                      Integer middleTwoSsn, Integer lastFourSsn, String ssnDataQuality) {
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
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ClientDemographics getClientDemographics() {
        return clientDemographics;
    }

    public void setClientDemographics(ClientDemographics clientDemographics) {
        this.clientDemographics = clientDemographics;
    }

    public ClientContactInfo getClientContactInfo() {
        return clientContactInfo;
    }

    public void setClientContactInfo(ClientContactInfo clientContactInfo) {
        this.clientContactInfo = clientContactInfo;
    }
}
