package com.outreachmappingsolutions.Models;

import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientDemographics;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class ClientDemographicsUnitTest {

    @Test
    public void testClientDemographicsProperties(){
        ClientBase testClient = new ClientBase();
        String gender="Male";
        String racePrimary="White/Caucasian";
        String raceSecondary="Black/African-American";
        String ethnicity="Non-Hispanic";
        String veteranStatus="Not a Veteran";

        ClientDemographics testClientDemographics = new ClientDemographics();

        testClientDemographics.setId(1);
        testClientDemographics.setClient(testClient);
        testClientDemographics.setGender(gender);
        testClientDemographics.setRacePrimary(racePrimary);
        testClientDemographics.setRaceSecondary(raceSecondary);
        testClientDemographics.setEthnicity(ethnicity);
        testClientDemographics.setVeteranStatus(veteranStatus);

        assertThat(testClientDemographics.getId(), is(1));
        assertThat(testClientDemographics.getClient(), is(testClient));
        assertThat(testClientDemographics.getGender(), is(gender));
        assertThat(testClientDemographics.getRacePrimary(), is(racePrimary));
        assertThat(testClientDemographics.getRaceSecondary(), is(raceSecondary));
        assertThat(testClientDemographics.getEthnicity(), is(ethnicity));
        assertThat(testClientDemographics.getVeteranStatus(), is(veteranStatus));
    }

    @Test
    public void testDefaultClientDemographicsConstructor(){
        ClientDemographics testClientDemographics = new ClientDemographics();

        assertThat(testClientDemographics.getId(), nullValue());
        assertThat(testClientDemographics.getClient(), nullValue());
        assertThat(testClientDemographics.getGender(), nullValue());
        assertThat(testClientDemographics.getRacePrimary(), nullValue());
        assertThat(testClientDemographics.getRaceSecondary(), nullValue());
        assertThat(testClientDemographics.getEthnicity(), nullValue());
        assertThat(testClientDemographics.getVeteranStatus(), nullValue());
    }
}
