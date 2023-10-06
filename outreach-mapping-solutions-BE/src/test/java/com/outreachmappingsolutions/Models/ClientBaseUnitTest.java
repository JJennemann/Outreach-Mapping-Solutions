package com.outreachmappingsolutions.Models;

import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class ClientBaseUnitTest {
    
    @Test
    public void testClientBaseProperties(){
        Integer id = 1;
        ClientDemographics testClientDemographics = new ClientDemographics();
        ClientContactInfo testClientContactInfo = new ClientContactInfo();
        String firstName = "John";
        String middleName = "James";
        String lastName = "Doe";
        String nameDataQuality = "Complete Data Quality";
        String dobMonth = "January";
        String dobDay = "1";
        String dobYear = "1999";
        String dobDataQuality = "Complete Data Quality";
        Integer firstThreeSsn = 123;
        Integer middleTwoSsn = 45;
        Integer lastFourSsn = 6789;
        String ssnDataQuality = "Complete Data Quality";

        ClientBase testClient = new ClientBase();

        testClient.setId(id);
        testClient.setClientDemographics(testClientDemographics);
        testClient.setClientContactInfo(testClientContactInfo);
        testClient.setFirstName(firstName);
        testClient.setMiddleName(middleName);
        testClient.setLastName(lastName);
        testClient.setNameDataQuality(nameDataQuality);
        testClient.setDobMonth(dobMonth);
        testClient.setDobDay(dobDay);
        testClient.setDobYear(dobYear);
        testClient.setDobDataQuality(dobDataQuality);
        testClient.setFirstThreeSsn(firstThreeSsn);
        testClient.setMiddleTwoSsn(middleTwoSsn);
        testClient.setLastFourSsn(lastFourSsn);
        testClient.setSsnDataQuality(ssnDataQuality);

        assertThat(testClient.getId(), is(id));
        assertThat(testClient.getClientDemographics(), is(testClientDemographics));
        assertThat(testClient.getClientContactInfo(), is(testClient.getClientContactInfo()));
        assertThat(testClient.getFirstName(), is(firstName));
        assertThat(testClient.getMiddleName(), is(middleName));
        assertThat(testClient.getLastName(), is(lastName));
        assertThat(testClient.getNameDataQuality(), is(nameDataQuality));
        assertThat(testClient.getDobMonth(), is(dobMonth));
        assertThat(testClient.getDobDay(), is(dobDay));
        assertThat(testClient.getDobYear(), is(dobYear));
        assertThat(testClient.getDobDataQuality(), is(dobDataQuality));
        assertThat(testClient.getFirstThreeSsn(), is(firstThreeSsn));
        assertThat(testClient.getMiddleTwoSsn(), is(middleTwoSsn));
        assertThat(testClient.getLastFourSsn(), is(lastFourSsn));
        assertThat(testClient.getSsnDataQuality(), is(ssnDataQuality));
    }

    @Test
    public void testClientBaseConstructorNoArgs(){
        ClientBase testClient = new ClientBase();

        assertThat(testClient.getId(), nullValue());
        assertThat(testClient.getClientDemographics(), nullValue());
        assertThat(testClient.getClientContactInfo(), nullValue());
        assertThat(testClient.getFirstName(), nullValue());
        assertThat(testClient.getMiddleName(), nullValue());
        assertThat(testClient.getLastName(), nullValue());
        assertThat(testClient.getNameDataQuality(), nullValue());
        assertThat(testClient.getDobMonth(), nullValue());
        assertThat(testClient.getDobDay(), nullValue());
        assertThat(testClient.getDobYear(), nullValue());
        assertThat(testClient.getDobDataQuality(), nullValue());
        assertThat(testClient.getFirstThreeSsn(), nullValue());
        assertThat(testClient.getMiddleTwoSsn(), nullValue());
        assertThat(testClient.getLastFourSsn(), nullValue());
        assertThat(testClient.getSsnDataQuality(), nullValue());
    }

    @Test
    public void testClientBaseConstructorParameterized(){
        String firstName = "John";
        String middleName = "James";
        String lastName = "Doe";
        String nameDataQuality = "Complete Data Quality";
        String dobMonth = "January";
        String dobDay = "1";
        String dobYear = "1999";
        String dobDataQuality = "Complete Data Quality";
        Integer firstThreeSsn = 123;
        Integer middleTwoSsn = 45;
        Integer lastFourSsn = 6789;
        String ssnDataQuality = "Complete Data Quality";

        ClientBase testClient = new ClientBase(firstName, middleName, lastName, nameDataQuality, dobMonth, dobDay,
                dobYear, dobDataQuality, firstThreeSsn, middleTwoSsn, lastFourSsn, ssnDataQuality);

        assertThat(testClient.getId(), nullValue());
        assertThat(testClient.getClientDemographics(), nullValue());
        assertThat(testClient.getClientContactInfo(), nullValue());
        assertThat(testClient.getFirstName(), is(firstName));
        assertThat(testClient.getMiddleName(), is(middleName));
        assertThat(testClient.getLastName(), is(lastName));
        assertThat(testClient.getNameDataQuality(), is(nameDataQuality));
        assertThat(testClient.getDobMonth(), is(dobMonth));
        assertThat(testClient.getDobDay(), is(dobDay));
        assertThat(testClient.getDobYear(), is(dobYear));
        assertThat(testClient.getDobDataQuality(), is(dobDataQuality));
        assertThat(testClient.getFirstThreeSsn(), is(firstThreeSsn));
        assertThat(testClient.getMiddleTwoSsn(), is(middleTwoSsn));
        assertThat(testClient.getLastFourSsn(), is(lastFourSsn));
        assertThat(testClient.getSsnDataQuality(), is(ssnDataQuality));
    }


    @Test
    public void testClientBaseEqualsMethod() {
        // Create two instances with the same attributes
        ClientBase testClient1 = new ClientBase("John", "James", "Doe", "Complete Data Quality", "January", "1", "1999", "Complete Data Quality", 123, 45, 6789, "Complete Data Quality");
        ClientBase testClient2 = new ClientBase("John", "James", "Doe", "Complete Data Quality", "January", "1", "1999", "Complete Data Quality", 123, 45, 6789, "Complete Data Quality");
        ClientBase testClient3 = new ClientBase();
        ClientBase testClient4 = new ClientBase();
        // Ensure they are equal as they have the same attributes
        assertThat(testClient1.equals(testClient2), is(true));
    }

    @Test
    public void testClientBaseHashCodeMethod() {
        // Create two instances with the same attributes
        ClientBase testClient1 = new ClientBase("John", "James", "Doe", "Complete Data Quality", "January", "1", "1999", "Complete Data Quality", 123, 45, 6789, "Complete Data Quality");
        ClientBase testClient2 = new ClientBase("John", "James", "Doe", "Complete Data Quality", "January", "1", "1999", "Complete Data Quality", 123, 45, 6789, "Complete Data Quality");

        // Ensure they have the same hash code as they have the same attributes
        assertThat(testClient1.hashCode(), is(testClient2.hashCode()));
    }
}
