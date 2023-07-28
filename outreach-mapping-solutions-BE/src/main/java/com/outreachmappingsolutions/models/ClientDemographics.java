package com.outreachmappingsolutions.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name="client_demographics")
public class ClientDemographics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "client_id")
    private ClientBase client;

    private String gender;
    private String racePrimary;
    private String raceSecondary;
    private String ethnicity;
    private String veteranStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ClientDemographics{" +
                "id=" + id +
                ", client=" + client +
                ", gender='" + gender + '\'' +
                ", racePrimary='" + racePrimary + '\'' +
                ", raceSecondary='" + raceSecondary + '\'' +
                ", ethnicity='" + ethnicity + '\'' +
                ", veteranStatus='" + veteranStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDemographics that = (ClientDemographics) o;
        return Objects.equals(id, that.id) && Objects.equals(client, that.client) && Objects.equals(gender, that.gender) && Objects.equals(racePrimary, that.racePrimary) && Objects.equals(raceSecondary, that.raceSecondary) && Objects.equals(ethnicity, that.ethnicity) && Objects.equals(veteranStatus, that.veteranStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, gender, racePrimary, raceSecondary, ethnicity, veteranStatus);
    }
}
