package com.outreachmappingsolutions.outreachmappingsolutions.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="client_demographic_data")
public class ClientDemographics {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    @Column(name="client_id")
    private Integer id;

    @OneToOne(mappedBy="clientDemographics")
    private ClientBase client;

    private String gender;
    private String racePrimary;
    private String raceSecondary;
    private String ethnicity;
    private String veteranStatus;

    public ClientDemographics() {
    }

    public ClientDemographics(ClientBase client, String gender, String racePrimary, String raceSecondary,
                              String ethnicity, String veteranStatus) {
        this.client = client;
        this.gender = gender;
        this.racePrimary = racePrimary;
        this.raceSecondary = raceSecondary;
        this.ethnicity = ethnicity;
        this.veteranStatus = veteranStatus;
    }

    public Integer getId() {
        return id;
    }

    public ClientBase getClient() {
        return client;
    }

    public void setClient(ClientBase client) {
        this.client = client;
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
