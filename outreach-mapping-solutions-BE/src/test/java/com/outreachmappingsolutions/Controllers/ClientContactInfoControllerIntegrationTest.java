package com.outreachmappingsolutions.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outreachmappingsolutions.Config.DatabaseTestConfiguration;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.repositories.ClientBaseRepository;
import com.outreachmappingsolutions.repositories.ClientContactInfoRepository;
import com.outreachmappingsolutions.services.ClientContactInfoService;
import com.outreachmappingsolutions.services.ClientDemographicsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.config.name=application-test"})
@AutoConfigureMockMvc
@Import(DatabaseTestConfiguration.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientContactInfoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientContactInfoRepository clientContactInfoRepository;

    @Autowired
    private ClientContactInfoService clientContactInfoService;

    @Autowired
    private ClientBaseRepository clientBaseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    ClientBase testClient;
    ClientContactInfo testClientContactInfo1;
    ClientContactInfo testClientContactInfo2;
    ClientContactInfo testClientContactInfo3;
    ClientContactInfo testUpdatedClientContactInfo;

    @BeforeEach
    public void setup(){
        testClient = new ClientBase("Miles", null, "O'Brien", null, null, null, null, null, null, null, null, null);
        clientBaseRepository.save(testClient);

        testClientContactInfo1 = createTestClientContactInfo("111-111-1111", "111-111-1112", "miles@test.com",
                "Keiko O'Brien", "Ex-Wife", "111-111-1113", "111-111-1114", "keiko@test.com");
        testClientContactInfo1.setClient(testClient);

        testClientContactInfo2 = createTestClientContactInfo("222-222-2221", "222-222-2222", "wesley@test.com",
                "Beverly Crusher", "Mother", "222-222-2223", "222-222-2224", "dr.crusher@email.com");

        testClientContactInfo3 = createTestClientContactInfo("111-111-1111", "222-222-2222", "thomas@test.com",
                "Will Riker", "Brother", "333-333-3333", "444-444-4444", "will@email.com");
    }

    private ClientContactInfo createTestClientContactInfo(String phonePrimary, String phoneSecondary, String email,
                                                          String iceName, String iceRelationship,
                                                          String icePhonePrimary, String icePhoneSecondary, String iceEmail) {
        ClientContactInfo clientContactInfo = new ClientContactInfo();
        clientContactInfo.setPhonePrimary(phonePrimary);
        clientContactInfo.setPhoneSecondary(phoneSecondary);
        clientContactInfo.setEmail(email);
        clientContactInfo.setIceName(iceName);
        clientContactInfo.setIceRelationship(iceRelationship);
        clientContactInfo.setIcePhonePrimary(icePhonePrimary);
        clientContactInfo.setIcePhoneSecondary(icePhoneSecondary);
        clientContactInfo.setIceEmail(iceEmail);
        return clientContactInfo;
    }



    @Test
    public void testReturnAllClientContactInfoSuccess() throws Exception {
        clientContactInfoRepository.save(testClientContactInfo1);
        clientContactInfoRepository.save(testClientContactInfo2);
        clientContactInfoRepository.save(testClientContactInfo3);
        List<ClientContactInfo> allTestClientContactInfo =
                (List<ClientContactInfo>) clientContactInfoService.returnAllClientContactInfo().getBody();

        assertThat(allTestClientContactInfo, notNullValue());
        mockMvc.perform(get("/clientContactInfo/returnAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        for(int i=0; i<allTestClientContactInfo.size(); i++){
            mockMvc.perform(get("/clientContactInfo/returnAll"))
                    .andExpect(jsonPath("$[" + i + "].id", is (allTestClientContactInfo.get(i).getId())))
                    .andExpect(jsonPath("$[" + i + "].phonePrimary", is (allTestClientContactInfo.get(i).getPhonePrimary())))
                    .andExpect(jsonPath("$[" + i + "].phoneSecondary", is (allTestClientContactInfo.get(i).getPhoneSecondary())))
                    .andExpect(jsonPath("$[" + i + "].email", is (allTestClientContactInfo.get(i).getEmail())))
                    .andExpect(jsonPath("$[" + i + "].iceName", is (allTestClientContactInfo.get(i).getIceName())))
                    .andExpect(jsonPath("$[" + i + "].iceRelationship", is (allTestClientContactInfo.get(i).getIceRelationship())))
                    .andExpect(jsonPath("$[" + i + "].icePhonePrimary", is (allTestClientContactInfo.get(i).getIcePhonePrimary())))
                    .andExpect(jsonPath("$[" + i + "].icePhoneSecondary", is (allTestClientContactInfo.get(i).getIcePhoneSecondary())))
                    .andExpect(jsonPath("$[" + i + "].iceEmail", is (allTestClientContactInfo.get(i).getIceEmail())));
        }
    }

    @Test
    public void testReturnAllClientContactInfoNotFound() throws Exception{
        String allTestClientContactInfo = (String) clientContactInfoService.returnAllClientContactInfo().getBody();

        assertThat(allTestClientContactInfo, is("No client contact information matching your criteria was found"));
        mockMvc.perform(get("/clientContactInfo/returnAll"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testReturnClientContactInfoByIdSuccess() throws Exception{
        clientContactInfoRepository.save(testClientContactInfo1);
        clientContactInfoRepository.save(testClientContactInfo2);
        clientContactInfoRepository.save(testClientContactInfo3);
        ClientContactInfo returnedTestClientContactInfo =
                (ClientContactInfo) clientContactInfoService.returnClientContactInfoByClientId(testClient.getId()).getBody();

        assertThat(returnedTestClientContactInfo, notNullValue());
        mockMvc.perform(get("/clientContactInfo/return/{clientId}", testClient.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testClientContactInfo1.getId())))
                .andExpect(jsonPath("$.phonePrimary", is(testClientContactInfo1.getPhonePrimary())))
                .andExpect(jsonPath("$.phoneSecondary", is(testClientContactInfo1.getPhoneSecondary())))
                .andExpect(jsonPath("$.email", is(testClientContactInfo1.getEmail())))
                .andExpect(jsonPath("$.iceName", is(testClientContactInfo1.getIceName())))
                .andExpect(jsonPath("$.iceRelationship", is(testClientContactInfo1.getIceRelationship())))
                .andExpect(jsonPath("$.icePhonePrimary", is(testClientContactInfo1.getIcePhonePrimary())))
                .andExpect(jsonPath("$.icePhoneSecondary", is(testClientContactInfo1.getIcePhoneSecondary())))
                .andExpect(jsonPath("$.iceEmail", is(testClientContactInfo1.getIceEmail())));
        assertThat(testClientContactInfo1.getClient().getId(), is(testClient.getId()));
    }


    @Test
    public void testReturnClientContactInfoByIdNotFound() throws Exception{
    String returnedTestClientContactInfo = (String) clientContactInfoService.returnClientContactInfoByClientId(testClient.getId()).getBody();

        assertThat(returnedTestClientContactInfo, is("No client contact information matching your criteria was found"));
        mockMvc.perform(get("/clientContactInfo/return/{clientId}", testClient.getId()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateClientContactInfoSuccess() throws Exception{
        clientContactInfoRepository.save(testClientContactInfo1);
        clientContactInfoRepository.save(testClientContactInfo2);
        clientContactInfoRepository.save(testClientContactInfo3);
        testUpdatedClientContactInfo = createTestClientContactInfo("111-111-1115", "111-111-1116", "miles@test.com",
                "Julian Bashir", "Friend", "111-111-1117", "111-111-1118", "julian@test.com");

        ClientContactInfo returnedTestClientContactInfo =
                (ClientContactInfo) clientContactInfoService.updateClientContactInfo(testClient.getId(),
                        testUpdatedClientContactInfo).getBody();

        assertThat(returnedTestClientContactInfo, notNullValue());
        mockMvc.perform(put("/clientContactInfo/update/{clientId}", testClient.getId())
                    .content(objectMapper.writeValueAsString(testUpdatedClientContactInfo))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(returnedTestClientContactInfo.getId())))
                .andExpect(jsonPath("$.phonePrimary", is(testUpdatedClientContactInfo.getPhonePrimary())))
                .andExpect(jsonPath("$.phoneSecondary", is(testUpdatedClientContactInfo.getPhoneSecondary())))
                .andExpect(jsonPath("$.email", is(testUpdatedClientContactInfo.getEmail())))
                .andExpect(jsonPath("$.iceName", is(testUpdatedClientContactInfo.getIceName())))
                .andExpect(jsonPath("$.iceRelationship", is(testUpdatedClientContactInfo.getIceRelationship())))
                .andExpect(jsonPath("$.icePhonePrimary", is(testUpdatedClientContactInfo.getIcePhonePrimary())))
                .andExpect(jsonPath("$.icePhoneSecondary", is(testUpdatedClientContactInfo.getIcePhoneSecondary())))
                .andExpect(jsonPath("$.iceEmail", is(testUpdatedClientContactInfo.getIceEmail())));
        assertThat (returnedTestClientContactInfo.getClient().getId(), is(testClient.getId()));
    }

    @Test
    public void testUpdateClientContactInfoNotFound() throws Exception{
        testUpdatedClientContactInfo = new ClientContactInfo();
        String returnedTestClientContactInfo = (String) clientContactInfoService.updateClientContactInfo(testClient.getId(),
                testUpdatedClientContactInfo).getBody();

        assertThat(returnedTestClientContactInfo, is("No client contact information matching your criteria was found"));
        mockMvc.perform(put("/clientContactInfo/update/{clientId}", testClient.getId())
                        .content(objectMapper.writeValueAsString(testUpdatedClientContactInfo))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}

















