package com.outreachmappingsolutions.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outreachmappingsolutions.Config.DatabaseTestConfiguration;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.repositories.ClientBaseRepository;
import com.outreachmappingsolutions.services.ClientBaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.config.name=application-test"})
@AutoConfigureMockMvc
@Import(DatabaseTestConfiguration.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientBaseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientBaseRepository clientBaseRepository;

    @Autowired
    private ClientBaseService clientBaseService;

    @Autowired
    private ObjectMapper objectMapper;

    ClientBase testClient1;
    ClientBase testClient2;
    ClientBase testClient3;
    ClientBase testUpdatedClient;

    @BeforeEach
    public void setup(){
        testClient1 = new ClientBase("Chief", null, null, "Partially Complete", null, null, null, "Client Refused",
                null, null, null, "Client Refused");

        testClient2 = new ClientBase("Wesley", null, "Crusher", "Partially Complete", null, null, null, "Client Refused",
                null, null, null, "Client Refused");

        testClient3 = new ClientBase("Tom", null, null, "Partially Complete", "August", "19", "1952",
                "Data Quality Complete", null, null, null, "Client Refused");

        testUpdatedClient = new ClientBase("Miles", "Edward", "O'Brien", "Data Quality Complete", "June", "9", "1959",
                "Data Quality Complete", 111, 11, 1111, "Data Quality Complete");
    }

    @Test
    public void testCreateNewClientTestSuccess() throws Exception {
        mockMvc.perform(post("/clientBase/create")
                        .content(objectMapper.writeValueAsString(testClient1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is(testClient1.getFirstName())))
                .andExpect(jsonPath("$.middleName", is(testClient1.getMiddleName())))
                .andExpect(jsonPath("$.lastName", is(testClient1.getLastName())))
                .andExpect(jsonPath("$.nameDataQuality", is(testClient1.getNameDataQuality())))
                .andExpect(jsonPath("$.dobMonth", is(testClient1.getDobMonth())))
                .andExpect(jsonPath("$.dobDay", is(testClient1.getDobDay())))
                .andExpect(jsonPath("$.dobYear", is(testClient1.getDobYear())))
                .andExpect(jsonPath("$.dobDataQuality", is(testClient1.getDobDataQuality())))
                .andExpect(jsonPath("$.firstThreeSsn", is(testClient1.getFirstThreeSsn())))
                .andExpect(jsonPath("$.middleTwoSsn", is(testClient1.getMiddleTwoSsn())))
                .andExpect(jsonPath("$.lastFourSsn", is(testClient1.getLastFourSsn())))
                .andExpect(jsonPath("$.ssnDataQuality", is(testClient1.getSsnDataQuality())))
                .andExpect(jsonPath("$.clientDemographics.id", is(1)))
                .andExpect(jsonPath("$.clientContactInfo.id", is(1)));
    }

    @Test
    public void testReturnAllClientsSuccess() throws Exception{
        testClient1.setClientDemographics(new ClientDemographics());
        testClient1.setClientContactInfo(new ClientContactInfo());
        clientBaseRepository.save(testClient1);
        testClient2.setClientDemographics(new ClientDemographics());
        testClient2.setClientContactInfo(new ClientContactInfo());
        clientBaseRepository.save(testClient2);
        testClient3.setClientDemographics(new ClientDemographics());
        testClient3.setClientContactInfo(new ClientContactInfo());
        clientBaseRepository.save(testClient3);
        List<ClientBase> allTestClients = (List<ClientBase>) clientBaseService.returnAllClients().getBody();

        assertThat(allTestClients, notNullValue());
        mockMvc.perform(get("/clientBase/returnAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        for(int i=0; i<allTestClients.size(); i++){
            mockMvc.perform(get("/clientBase/returnAll"))
                    .andExpect(jsonPath("$[" + i + "].id", is (allTestClients.get(i).getId())))
                    .andExpect(jsonPath("$[" + i + "].firstName", is (allTestClients.get(i).getFirstName())))
                    .andExpect(jsonPath("$[" + i + "].middleName", is (allTestClients.get(i).getMiddleName())))
                    .andExpect(jsonPath("$[" + i + "].lastName", is (allTestClients.get(i).getLastName())))
                    .andExpect(jsonPath("$[" + i + "].nameDataQuality", is (allTestClients.get(i).getNameDataQuality())))
                    .andExpect(jsonPath("$[" + i + "].dobMonth", is (allTestClients.get(i).getDobMonth())))
                    .andExpect(jsonPath("$[" + i + "].dobDay", is (allTestClients.get(i).getDobDay())))
                    .andExpect(jsonPath("$[" + i + "].dobYear", is (allTestClients.get(i).getDobYear())))
                    .andExpect(jsonPath("$[" + i + "].dobDataQuality", is (allTestClients.get(i).getDobDataQuality())))
                    .andExpect(jsonPath("$[" + i + "].firstThreeSsn", is (allTestClients.get(i).getFirstThreeSsn())))
                    .andExpect(jsonPath("$[" + i + "].middleTwoSsn", is (allTestClients.get(i).getMiddleTwoSsn())))
                    .andExpect(jsonPath("$[" + i + "].lastFourSsn", is (allTestClients.get(i).getLastFourSsn())))
                    .andExpect(jsonPath("$[" + i + "].ssnDataQuality", is (allTestClients.get(i).getSsnDataQuality())))
                    .andExpect(jsonPath("$[" + i + "].clientDemographics.id", is (allTestClients.get(i).getClientDemographics().getId())))
                    .andExpect(jsonPath("$[" + i + "].clientContactInfo.id", is (allTestClients.get(i).getClientContactInfo().getId())));
        }
    }

    @Test
    public void testReturnAllClientsNotFound() throws Exception {
        mockMvc.perform(get("/clientBase/returnAll"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("No clients matching your criteria were found")));
    }

    @Test
    public void testReturnClientByIdSuccess() throws Exception{
        testClient1.setClientDemographics(new ClientDemographics());
        testClient1.setClientContactInfo(new ClientContactInfo());
        clientBaseRepository.save(testClient1);
        clientBaseRepository.save(testClient2);
        clientBaseRepository.save(testClient3);

        mockMvc.perform(get("/clientBase/return/{clientId}", testClient1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is(testClient1.getFirstName())))
                .andExpect(jsonPath("$.middleName", is(testClient1.getMiddleName())))
                .andExpect(jsonPath("$.lastName", is(testClient1.getLastName())))
                .andExpect(jsonPath("$.nameDataQuality", is(testClient1.getNameDataQuality())))
                .andExpect(jsonPath("$.dobMonth", is(testClient1.getDobMonth())))
                .andExpect(jsonPath("$.dobDay", is(testClient1.getDobDay())))
                .andExpect(jsonPath("$.dobYear", is(testClient1.getDobYear())))
                .andExpect(jsonPath("$.dobDataQuality", is(testClient1.getDobDataQuality())))
                .andExpect(jsonPath("$.firstThreeSsn", is(testClient1.getFirstThreeSsn())))
                .andExpect(jsonPath("$.middleTwoSsn", is(testClient1.getMiddleTwoSsn())))
                .andExpect(jsonPath("$.lastFourSsn", is(testClient1.getLastFourSsn())))
                .andExpect(jsonPath("$.ssnDataQuality", is(testClient1.getSsnDataQuality())))
                .andExpect(jsonPath("$.clientDemographics.id", is(testClient1.getClientDemographics().getId())))
                .andExpect(jsonPath("$.clientContactInfo.id", is(testClient1.getClientContactInfo().getId())));
    }

    @Test
    public void testReturnClientByIdNotFound() throws Exception {
        testClient1.setId(1);
        mockMvc.perform(get("/clientBase/return/{clientId}", testClient1.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("No clients matching your criteria were found")));
    }

    @Test
    public void testUpdateClientSuccess() throws Exception{
        testClient1.setClientDemographics(new ClientDemographics());
        testClient1.setClientContactInfo(new ClientContactInfo());
        clientBaseRepository.save(testClient1);

        mockMvc.perform(put("/clientBase/update/{clientId}", testClient1.getId())
                    .content(objectMapper.writeValueAsString(testUpdatedClient))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testClient1.getId())))
                .andExpect(jsonPath("$.firstName", is(testUpdatedClient.getFirstName())))
                .andExpect(jsonPath("$.middleName", is(testUpdatedClient.getMiddleName())))
                .andExpect(jsonPath("$.lastName", is(testUpdatedClient.getLastName())))
                .andExpect(jsonPath("$.nameDataQuality", is(testUpdatedClient.getNameDataQuality())))
                .andExpect(jsonPath("$.dobMonth", is(testUpdatedClient.getDobMonth())))
                .andExpect(jsonPath("$.dobDay", is(testUpdatedClient.getDobDay())))
                .andExpect(jsonPath("$.dobYear", is(testUpdatedClient.getDobYear())))
                .andExpect(jsonPath("$.dobDataQuality", is(testUpdatedClient.getDobDataQuality())))
                .andExpect(jsonPath("$.firstThreeSsn", is(testUpdatedClient.getFirstThreeSsn())))
                .andExpect(jsonPath("$.middleTwoSsn", is(testUpdatedClient.getMiddleTwoSsn())))
                .andExpect(jsonPath("$.lastFourSsn", is(testUpdatedClient.getLastFourSsn())))
                .andExpect(jsonPath("$.ssnDataQuality", is(testUpdatedClient.getSsnDataQuality())))
                .andExpect(jsonPath("$.clientDemographics.id", is(testClient1.getClientDemographics().getId())))
                .andExpect(jsonPath("$.clientContactInfo.id", is(testClient1.getClientContactInfo().getId())));
    }

    @Test
    public void testUpdateClientNotFound() throws Exception{
        testClient1.setId(1);

        mockMvc.perform(put("/clientBase/update/{clientId}", testClient1.getId())
                        .content(objectMapper.writeValueAsString(testUpdatedClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("No clients matching your criteria were found")));
    }

    @Test
    public void testDeleteClientSuccess() throws Exception{
        clientBaseRepository.save(testClient1);
        clientBaseRepository.save(testClient2);
        clientBaseRepository.save(testClient3);

        mockMvc.perform(delete("/clientBase/delete/{clientId}", testClient1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Client was successfully deleted")));

        List<ClientBase> allTestClients = (List<ClientBase>) clientBaseRepository.findAll();

        assertThat(allTestClients, hasSize(2));
        assertThat(allTestClients.get(0).getId(), is(testClient2.getId()));
        assertThat(allTestClients.get(1).getId(), is(testClient3.getId()));

    }

    @Test
    public void testDeleteClientNotFound() throws Exception{
        testClient1.setId(1);

        mockMvc.perform(delete("/clientBase/delete/{clientId}", testClient1.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("No clients matching your criteria were found")));
    }

}
