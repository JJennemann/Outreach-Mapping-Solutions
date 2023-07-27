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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    ClientBase testUpdatedClientBase;

    @BeforeEach
    public void setup(){
        testClient1 = new ClientBase("Chief", null, null, "Partially Complete", null, null, null, "Client Refused",
                null, null, null, "Client Refused");

        testClient2 = new ClientBase("Wesley", null, "Crusher", "Partially Complete", null, null, null, "Client Refused",
                null, null, null, "Client Refused");

        testClient3 = new ClientBase("Tom", null, null, "Partially Complete", "August", "19", "1952",
                "Data Quality Complete", null, null, null, "Client Refused");

        testUpdatedClientBase = new ClientBase("Miles", "Edward", "O'Brien", "Data Quality Complete", "June", "9", "1959",
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

//  Dont care about this here, right? This will be checked in the service integration test
//        List<ClientBase> allTestClients = (List<ClientBase>) clientBaseRepository.findAll();
//
//        assertThat(allTestClients, hasSize(1));
//        assertThat(allTestClients.get(0).getId(), is(1));
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
        ResponseEntity<?> response = clientBaseService.returnAllClients();

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));
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
        ClientBase returnedTestClient = (ClientBase) clientBaseService.returnClientById(testClient1.getId()).getBody();

        assertThat(returnedTestClient, notNullValue());
        mockMvc.perform(get("/clientBase/return/{clientId}", testClient1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(returnedTestClient.getId())))
                .andExpect(jsonPath("$.firstName", is(returnedTestClient.getFirstName())))
                .andExpect(jsonPath("$.middleName", is(returnedTestClient.getMiddleName())))
                .andExpect(jsonPath("$.lastName", is(returnedTestClient.getLastName())))
                .andExpect(jsonPath("$.nameDataQuality", is(returnedTestClient.getNameDataQuality())))
                .andExpect(jsonPath("$.dobMonth", is(returnedTestClient.getDobMonth())))
                .andExpect(jsonPath("$.dobDay", is(returnedTestClient.getDobDay())))
                .andExpect(jsonPath("$.dobYear", is(returnedTestClient.getDobYear())))
                .andExpect(jsonPath("$.dobDataQuality", is(returnedTestClient.getDobDataQuality())))
                .andExpect(jsonPath("$.firstThreeSsn", is(returnedTestClient.getFirstThreeSsn())))
                .andExpect(jsonPath("$.middleTwoSsn", is(returnedTestClient.getMiddleTwoSsn())))
                .andExpect(jsonPath("$.lastFourSsn", is(returnedTestClient.getLastFourSsn())))
                .andExpect(jsonPath("$.ssnDataQuality", is(returnedTestClient.getSsnDataQuality())))
                .andExpect(jsonPath("$.clientDemographics.id", is(returnedTestClient.getClientDemographics().getId())))
                .andExpect(jsonPath("$.clientContactInfo.id", is(returnedTestClient.getClientContactInfo().getId())));
    }

    @Test
    public void testReturnClientByIdNotFound() throws Exception {
        ResponseEntity<?> response = clientBaseService.returnClientById(1);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));
        mockMvc.perform(get("/clientBase/return/{clientId}", 1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("No clients matching your criteria were found")));
    }

    @Test
    public void testUpdateClientSuccess() throws Exception{
        testClient1.setClientDemographics(new ClientDemographics());
        testClient1.setClientContactInfo(new ClientContactInfo());
        clientBaseRepository.save(testClient1);
        ClientBase updatedTestClient = (ClientBase) clientBaseService.updateClient(testClient1.getId(), testUpdatedClientBase).getBody();

        assertThat(updatedTestClient, notNullValue());
        mockMvc.perform(put("/clientBase/update/{clientId}", testClient1.getId())
                    .content(objectMapper.writeValueAsString(testUpdatedClientBase))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedTestClient.getId())))
                .andExpect(jsonPath("$.firstName", is(updatedTestClient.getFirstName())))
                .andExpect(jsonPath("$.middleName", is(updatedTestClient.getMiddleName())))
                .andExpect(jsonPath("$.lastName", is(updatedTestClient.getLastName())))
                .andExpect(jsonPath("$.nameDataQuality", is(updatedTestClient.getNameDataQuality())))
                .andExpect(jsonPath("$.dobMonth", is(updatedTestClient.getDobMonth())))
                .andExpect(jsonPath("$.dobDay", is(updatedTestClient.getDobDay())))
                .andExpect(jsonPath("$.dobYear", is(updatedTestClient.getDobYear())))
                .andExpect(jsonPath("$.dobDataQuality", is(updatedTestClient.getDobDataQuality())))
                .andExpect(jsonPath("$.firstThreeSsn", is(updatedTestClient.getFirstThreeSsn())))
                .andExpect(jsonPath("$.middleTwoSsn", is(updatedTestClient.getMiddleTwoSsn())))
                .andExpect(jsonPath("$.lastFourSsn", is(updatedTestClient.getLastFourSsn())))
                .andExpect(jsonPath("$.ssnDataQuality", is(updatedTestClient.getSsnDataQuality())))
                .andExpect(jsonPath("$.clientDemographics.id", is(updatedTestClient.getClientDemographics().getId())))
                .andExpect(jsonPath("$.clientContactInfo.id", is(updatedTestClient.getClientContactInfo().getId())));
    }

    @Test
    public void testUpdateClientNotFound() throws Exception{
       ResponseEntity<?> response = clientBaseService.updateClient(1, testUpdatedClientBase);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));
        mockMvc.perform(put("/clientBase/update/{clientId}", 1)
                        .content(objectMapper.writeValueAsString(testUpdatedClientBase))
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


//  Dont care about this here, right? This will be checked in the service integration test
//        List<ClientBase> allTestClients = (List<ClientBase>) clientBaseRepository.findAll();
//        assertThat(allTestClients, hasSize(2));
//        assertThat(allTestClients.get(0).getId(), is(testClient2.getId()));
//        assertThat(allTestClients.get(1).getId(), is(testClient3.getId()));

    }

    @Test
    public void testDeleteClientNotFound() throws Exception{
        ResponseEntity<?> response = clientBaseService.deleteClient(1);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));
        mockMvc.perform(delete("/clientBase/delete/{clientId}", 1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is(response.getBody())));
    }

}
