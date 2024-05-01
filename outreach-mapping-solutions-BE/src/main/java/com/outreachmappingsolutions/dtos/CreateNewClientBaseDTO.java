package com.outreachmappingsolutions.dtos;

import com.outreachmappingsolutions.models.ClientContactInfo;

import java.time.LocalDateTime;

public class CreateNewClientBaseDTO {

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


    public CreateNewClientBaseDTO() {
    }

    public CreateNewClientBaseDTO(Integer id, LocalDateTime createdAt, LocalDateTime lastModified, String firstName,
                                  String middleName, String lastName, String nameDataQuality, String dobMonth,
                                  Integer dobDay, Integer dobYear, String dobDataQuality, Integer firstThreeSsn,
                                  Integer middleTwoSsn, Integer lastFourSsn, String ssnDataQuality) {
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

}
