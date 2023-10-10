package com.outreachmappingsolutions.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="client_base_data")
public class ClientBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String displayName;
    private String nameDataQuality;

    private String dobMonth;
    private String dobDay;
    private String dobYear;
    private String displayDob;
    private String dobDataQuality;

    private String firstThreeSsn;
    private String middleTwoSsn;
    private String lastFourSsn;
    private String displaySsn;
    private String ssnDataQuality;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ClientDemographics clientDemographics;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ClientContactInfo clientContactInfo;

    public ClientBase() {
    }

    public ClientBase(String firstName, String middleName, String lastName, String displayName, String nameDataQuality, String dobMonth,
                      String dobDay, String dobYear, String displayDob, String dobDataQuality, String firstThreeSsn,
                      String middleTwoSsn, String lastFourSsn, String displaySsn, String ssnDataQuality) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.nameDataQuality = nameDataQuality;
        this.dobMonth = dobMonth;
        this.dobDay = dobDay;
        this.dobYear = dobYear;
        this.displayDob = displayDob;
        this.dobDataQuality = dobDataQuality;
        this.firstThreeSsn = firstThreeSsn;
        this.middleTwoSsn = middleTwoSsn;
        this.lastFourSsn = lastFourSsn;
        this.displaySsn = displaySsn;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public String getDisplayDob() {
        return displayDob;
    }

    public void setDisplayDob(String displayDob) {
        this.displayDob = displayDob;
    }

    public String getDobDataQuality() {
        return dobDataQuality;
    }

    public void setDobDataQuality(String dobDataQuality) {
        this.dobDataQuality = dobDataQuality;
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

    public String getDisplaySsn() {
        return displaySsn;
    }

    public void setDisplaySsn(String displaySsn) {
        this.displaySsn = displaySsn;
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

    @Override
    public String toString() {
        return "ClientBase{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nameDataQuality='" + nameDataQuality + '\'' +
                ", dobMonth='" + dobMonth + '\'' +
                ", dobDay='" + dobDay + '\'' +
                ", dobYear='" + dobYear + '\'' +
                ", dobDataQuality='" + dobDataQuality + '\'' +
                ", firstThreeSsn=" + firstThreeSsn +
                ", middleTwoSsn=" + middleTwoSsn +
                ", lastFourSsn=" + lastFourSsn +
                ", ssnDataQuality='" + ssnDataQuality + '\'' +
                ", clientDemographics=" + clientDemographics +
                ", clientContactInfo=" + clientContactInfo +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientBase that = (ClientBase) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(nameDataQuality, that.nameDataQuality) && Objects.equals(dobMonth, that.dobMonth) && Objects.equals(dobDay, that.dobDay) && Objects.equals(dobYear, that.dobYear) && Objects.equals(dobDataQuality, that.dobDataQuality) && Objects.equals(firstThreeSsn, that.firstThreeSsn) && Objects.equals(middleTwoSsn, that.middleTwoSsn) && Objects.equals(lastFourSsn, that.lastFourSsn) && Objects.equals(ssnDataQuality, that.ssnDataQuality) && Objects.equals(clientDemographics, that.clientDemographics) && Objects.equals(clientContactInfo, that.clientContactInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, nameDataQuality, dobMonth, dobDay, dobYear, dobDataQuality, firstThreeSsn, middleTwoSsn, lastFourSsn, ssnDataQuality, clientDemographics, clientContactInfo);
    }
}
