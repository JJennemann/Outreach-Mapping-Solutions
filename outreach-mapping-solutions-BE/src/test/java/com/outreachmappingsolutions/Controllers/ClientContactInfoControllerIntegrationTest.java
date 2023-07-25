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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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

        testClientContactInfo1 = new ClientContactInfo();
        testClientContactInfo1.setPhonePrimary("111-111-1111");
        testClientContactInfo1.setPhoneSecondary("111-111-1112");
        testClientContactInfo1.setEmail("miles@test.com");
        testClientContactInfo1.setIceName("Keiko O'Brien");
        testClientContactInfo1.setIceRelationship("Ex-Wife");
        testClientContactInfo1. setIcePhonePrimary("111-111-1113");
        testClientContactInfo1.setIcePhoneSecondary("111-111-1114");
        testClientContactInfo1.setIceEmail("keiko@test.com");
        testClientContactInfo1.setClient(testClient);

        testClientContactInfo2 = new ClientContactInfo();
        testClientContactInfo2.setPhonePrimary("222-222-2221");
        testClientContactInfo2.setPhoneSecondary("222-222-2222");
        testClientContactInfo2.setEmail("wesley@test.com");
        testClientContactInfo2.setIceName("Beverly Crusher");
        testClientContactInfo2.setIceRelationship("Mother");
        testClientContactInfo2. setIcePhonePrimary("222-222-2223");
        testClientContactInfo2.setIcePhoneSecondary("222-222-2224");
        testClientContactInfo2.setIceEmail("dr.crusher@email.com");

        testClientContactInfo3 = new ClientContactInfo();
        testClientContactInfo3.setPhonePrimary("111-111-1111");
        testClientContactInfo3.setPhoneSecondary("222-222-2222");
        testClientContactInfo3.setEmail("thomas@test.com");
        testClientContactInfo3.setIceName("Will Riker");
        testClientContactInfo3.setIceRelationship("Brother");
        testClientContactInfo3.setIcePhonePrimary("333-333-3333");
        testClientContactInfo3.setIcePhoneSecondary("444-444-4444");
        testClientContactInfo3.setIceEmail("will@email.com");

    }

    @Test
    public void testReturnAllClientContactInfoSuccess() throws Exception {
        clientContactInfoRepository.save(testClientContactInfo1);
        clientContactInfoRepository.save(testClientContactInfo2);
        clientContactInfoRepository.save(testClientContactInfo3);
        List<ClientContactInfo> allTestClientContactInfo =
                (List<ClientContactInfo>) clientContactInfoService.returnAllClientContactInfo().getBody();

        assert allTestClientContactInfo != null;
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

        assert allTestClientContactInfo.equals("No client contact information matching your criteria was found");
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

        assert returnedTestClientContactInfo !=null;
        mockMvc.perform(get("/clientContactInfo/return/{clientId}", testClient.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.phonePrimary", is("111-111-1111")))
                .andExpect(jsonPath("$.phoneSecondary", is("111-111-1112")))
                .andExpect(jsonPath("$.email", is("miles@test.com")))
                .andExpect(jsonPath("$.iceName", is("Keiko O'Brien")))
                .andExpect(jsonPath("$.iceRelationship", is("Ex-Wife")))
                .andExpect(jsonPath("$.icePhonePrimary", is("111-111-1113")))
                .andExpect(jsonPath("$.icePhoneSecondary", is("111-111-1114")))
                .andExpect(jsonPath("$.iceEmail", is("keiko@test.com")));
    }


    @Test
    public void testReturnClientContactInfoByIdNotFound() throws Exception{
    String returnedTestClientContactInfo = (String) clientContactInfoService.returnClientContactInfoByClientId(testClient.getId()).getBody();

        assert returnedTestClientContactInfo.equals("No client contact information matching your criteria was found");
        mockMvc.perform(get("/clientContactInfo/return/{clientId}", testClient.getId()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateClientContactInfoSuccess() throws Exception{
        clientContactInfoRepository.save(testClientContactInfo1);
        clientContactInfoRepository.save(testClientContactInfo2);
        clientContactInfoRepository.save(testClientContactInfo3);
        testUpdatedClientContactInfo = new ClientContactInfo();
        testUpdatedClientContactInfo.setPhonePrimary("111-111-1115");
        testUpdatedClientContactInfo.setPhoneSecondary("111-111-1116");
        testUpdatedClientContactInfo.setEmail("miles@test.com");
        testUpdatedClientContactInfo.setIceName("Julian Bashir");
        testUpdatedClientContactInfo.setIceRelationship("Friend");
        testUpdatedClientContactInfo. setIcePhonePrimary("111-111-1117");
        testUpdatedClientContactInfo.setIcePhoneSecondary("111-111-1118");
        testUpdatedClientContactInfo.setIceEmail("julian@test.com");

        ClientContactInfo returnedTestClientContactInfo =
                (ClientContactInfo) clientContactInfoService.updateClientContactInfo(testClient.getId(),
                        testUpdatedClientContactInfo).getBody();

        assert returnedTestClientContactInfo != null;
        mockMvc.perform(put("/clientContactInfo/update/{clientId}", testClient.getId())
                    .content(objectMapper.writeValueAsString(testUpdatedClientContactInfo))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.phonePrimary", is("111-111-1115")))
                .andExpect(jsonPath("$.phoneSecondary", is("111-111-1116")))
                .andExpect(jsonPath("$.email", is("miles@test.com")))
                .andExpect(jsonPath("$.iceName", is("Julian Bashir")))
                .andExpect(jsonPath("$.iceRelationship", is("Friend")))
                .andExpect(jsonPath("$.icePhonePrimary", is("111-111-1117")))
                .andExpect(jsonPath("$.icePhoneSecondary", is("111-111-1118")))
                .andExpect(jsonPath("$.iceEmail", is("julian@test.com")));
        assertThat (returnedTestClientContactInfo.getClient().getId(), is(testClient.getId()));
    }

    @Test
    public void testUpdateClientContactInfoNotFound() throws Exception{
        testUpdatedClientContactInfo = new ClientContactInfo();
        String returnedTestClientContactInfo = (String) clientContactInfoService.updateClientContactInfo(testClient.getId(),
                testUpdatedClientContactInfo).getBody();

        assert returnedTestClientContactInfo.equals("No client contact information matching your criteria was found");
        mockMvc.perform(put("/clientContactInfo/update/{clientId}", testClient.getId())
                        .content(objectMapper.writeValueAsString(testUpdatedClientContactInfo))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}