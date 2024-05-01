package com.outreachmappingsolutions.dtos;

public class CreateNewClientBaseDTO {

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

    public CreateNewClientBaseDTO(String firstName, String middleName, String lastName, String nameDataQuality, String dobMonth, Integer dobDay, Integer dobYear, String dobDataQuality, Integer firstThreeSsn, Integer middleTwoSsn, Integer lastFourSsn, String ssnDataQuality) {
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
}
