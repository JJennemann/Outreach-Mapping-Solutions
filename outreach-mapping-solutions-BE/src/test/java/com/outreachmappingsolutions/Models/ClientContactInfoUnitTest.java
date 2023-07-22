package com.outreachmappingsolutions.Models;

import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class ClientContactInfoUnitTest {

    @Test
    public void testClientContactInfoProperties(){
        ClientBase testClient = new ClientBase();
        String phonePrimary = "111-111-1111";
        String phoneSecondary = "222-222-2222";
        String email = "email@test.com";
        String iceName = "John Doe";
        String iceRelationship = "Brother";
        String icePhonePrimary = "333-333-3333";
        String icePhoneSecondary = "444-444-4444";
        String iceEmail = "test@email.com";

        ClientContactInfo testClientContactInfo = new ClientContactInfo();

        testClientContactInfo.setId(1);
        testClientContactInfo.setClient(testClient);
        testClientContactInfo.setPhonePrimary(phonePrimary);
        testClientContactInfo.setPhoneSecondary(phoneSecondary);
        testClientContactInfo.setEmail(email);
        testClientContactInfo.setIceName(iceName);
        testClientContactInfo.setIceRelationship(iceRelationship);
        testClientContactInfo.setIcePhonePrimary(icePhonePrimary);
        testClientContactInfo.setIcePhoneSecondary(icePhoneSecondary);
        testClientContactInfo.setIceEmail(iceEmail);

        assertThat(testClientContactInfo.getId(), is(1));
        assertThat(testClientContactInfo.getClient(), is(testClient));
        assertThat(testClientContactInfo.getPhonePrimary(), is(phonePrimary));
        assertThat(testClientContactInfo.getPhoneSecondary(), is(phoneSecondary));
        assertThat(testClientContactInfo.getEmail(), is(email));
        assertThat(testClientContactInfo.getIceName(), is(iceName));
        assertThat(testClientContactInfo.getIceRelationship(), is(iceRelationship));
        assertThat(testClientContactInfo.getIcePhonePrimary(), is(icePhonePrimary));
        assertThat(testClientContactInfo.getIcePhoneSecondary(), is(icePhoneSecondary));
        assertThat(testClientContactInfo.getIceEmail(), is(iceEmail));
    }

    @Test
    public void testDefaultClientContactInfoContstructor(){
        ClientContactInfo testClientContactInfo = new ClientContactInfo();

        assertThat(testClientContactInfo.getId(), nullValue());
        assertThat(testClientContactInfo.getClient(), nullValue());
        assertThat(testClientContactInfo.getPhonePrimary(), nullValue());
        assertThat(testClientContactInfo.getPhoneSecondary(), nullValue());
        assertThat(testClientContactInfo.getEmail(), nullValue());
        assertThat(testClientContactInfo.getIceName(), nullValue());
        assertThat(testClientContactInfo.getIceRelationship(), nullValue());
        assertThat(testClientContactInfo.getIcePhonePrimary(), nullValue());
        assertThat(testClientContactInfo.getIcePhoneSecondary(), nullValue());
        assertThat(testClientContactInfo.getIceEmail(), nullValue());
    }

}
