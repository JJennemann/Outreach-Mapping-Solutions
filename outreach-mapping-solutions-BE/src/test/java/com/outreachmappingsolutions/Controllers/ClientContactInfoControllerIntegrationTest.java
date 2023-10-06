package com.outreachmappingsolutions.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outreachmappingsolutions.Config.DatabaseTestConfiguration;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.repositories.ClientBaseRepository;
import com.outreachmappingsolutions.repositories.ClientContactInfoRepository;
import com.outreachmappingsolutions.services.ClientContactInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



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
        testClientContactInfo2 = createTestClientContactInfo("222-222-2221", "222-222-2222", "wesley@test.com",
                "Beverly Crusher", "Mother", "222-222-2223", "222-222-2224", "dr.crusher@email.com");
        testClientContactInfo3 = createTestClientContactInfo("111-111-1111", "222-222-2222", "thomas@test.com",
                "Will Riker", "Brother", "333-333-3333", "444-444-4444", "will@email.com");
        testUpdatedClientContactInfo = createTestClientContactInfo("111-111-1115", "111-111-1116", "miles@test.com",
                "Julian Bashir", "Friend", "111-111-1117", "111-111-1118", "julian@test.com");

        testClientContactInfo1.setClient(testClient);
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

        ResponseEntity<?> response = clientContactInfoService.returnAllClientContactInfo();
        List<ClientContactInfo> allTestClientContactInfo = (List<ClientContactInfo>)  response.getBody();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(allTestClientContactInfo, hasSize(3));
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
        ResponseEntity<?> response = clientContactInfoService.returnAllClientContactInfo();

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));
        mockMvc.perform(get("/clientContactInfo/returnAll"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("No client contact information matching your criteria was found")));
    }

    @Test
    public void testReturnClientContactInfoByIdSuccess() throws Exception{
        clientContactInfoRepository.save(testClientContactInfo1);
        clientContactInfoRepository.save(testClientContactInfo2);
        clientContactInfoRepository.save(testClientContactInfo3);

        ResponseEntity<?> response = clientContactInfoService.returnClientContactInfoByClientId(testClient.getId());
        ClientContactInfo returnedTestClientContactInfo = (ClientContactInfo) response.getBody();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(returnedTestClientContactInfo, notNullValue());
        mockMvc.perform(get("/clientContactInfo/return/{clientId}", testClient.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(returnedTestClientContactInfo.getId())))
                .andExpect(jsonPath("$.phonePrimary", is(returnedTestClientContactInfo.getPhonePrimary())))
                .andExpect(jsonPath("$.phoneSecondary", is(returnedTestClientContactInfo.getPhoneSecondary())))
                .andExpect(jsonPath("$.email", is(returnedTestClientContactInfo.getEmail())))
                .andExpect(jsonPath("$.iceName", is(returnedTestClientContactInfo.getIceName())))
                .andExpect(jsonPath("$.iceRelationship", is(returnedTestClientContactInfo.getIceRelationship())))
                .andExpect(jsonPath("$.icePhonePrimary", is(returnedTestClientContactInfo.getIcePhonePrimary())))
                .andExpect(jsonPath("$.icePhoneSecondary", is(returnedTestClientContactInfo.getIcePhoneSecondary())))
                .andExpect(jsonPath("$.iceEmail", is(returnedTestClientContactInfo.getIceEmail())));
        assertThat(returnedTestClientContactInfo.getClient().getId(), is(testClient.getId()));
    }


    @Test
    public void testReturnClientContactInfoByIdNotFound() throws Exception{
        ResponseEntity<?> response = clientContactInfoService.returnClientContactInfoByClientId(1);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));
        mockMvc.perform(get("/clientContactInfo/return/{clientId}", testClient.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("No client contact information matching your criteria was found")));
    }

    @Test
    public void testUpdateClientContactInfoSuccess() throws Exception{
        clientContactInfoRepository.save(testClientContactInfo1);

        ResponseEntity<?> response = clientContactInfoService.updateClientContactInfo(testClient.getId(), testUpdatedClientContactInfo);
        ClientContactInfo returnedTestClientContactInfo = (ClientContactInfo) response.getBody();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(returnedTestClientContactInfo, notNullValue());
        mockMvc.perform(put("/clientContactInfo/update/{clientId}", testClient.getId())
                    .content(objectMapper.writeValueAsString(testUpdatedClientContactInfo))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(returnedTestClientContactInfo.getId())))
                .andExpect(jsonPath("$.phonePrimary", is(returnedTestClientContactInfo.getPhonePrimary())))
                .andExpect(jsonPath("$.phoneSecondary", is(returnedTestClientContactInfo.getPhoneSecondary())))
                .andExpect(jsonPath("$.email", is(returnedTestClientContactInfo.getEmail())))
                .andExpect(jsonPath("$.iceName", is(returnedTestClientContactInfo.getIceName())))
                .andExpect(jsonPath("$.iceRelationship", is(returnedTestClientContactInfo.getIceRelationship())))
                .andExpect(jsonPath("$.icePhonePrimary", is(returnedTestClientContactInfo.getIcePhonePrimary())))
                .andExpect(jsonPath("$.icePhoneSecondary", is(returnedTestClientContactInfo.getIcePhoneSecondary())))
                .andExpect(jsonPath("$.iceEmail", is(returnedTestClientContactInfo.getIceEmail())));
        assertThat (returnedTestClientContactInfo.getClient().getId(), is(testClient.getId()));
    }

    @Test
    public void testUpdateClientContactInfoNotFound() throws Exception{
        ResponseEntity<?> response = clientContactInfoService.updateClientContactInfo(1, testUpdatedClientContactInfo);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));
        mockMvc.perform(put("/clientContactInfo/update/{clientId}", testClient.getId())
                        .content(objectMapper.writeValueAsString(testUpdatedClientContactInfo))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("No client contact information matching your criteria was found")));
    }
}

















