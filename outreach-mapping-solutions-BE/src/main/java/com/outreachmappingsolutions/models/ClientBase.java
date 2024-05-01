package com.outreachmappingsolutions.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Objects;
import java.time.LocalDateTime;

@Entity
@Table(name="client_base_data")
public class ClientBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(nullable=false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column (nullable=false)
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ClientDemographics clientDemographics;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ClientContactInfo clientContactInfo;

    public ClientBase() {
    }

    public ClientBase(String firstName, String middleName, String lastName, String nameDataQuality, String dobMonth,
                      Integer dobDay, Integer dobYear, String dobDataQuality, Integer firstThreeSsn,
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
