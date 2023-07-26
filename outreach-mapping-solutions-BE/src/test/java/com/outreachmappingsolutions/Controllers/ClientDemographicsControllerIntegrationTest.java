package com.outreachmappingsolutions.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outreachmappingsolutions.Config.DatabaseTestConfiguration;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.repositories.ClientBaseRepository;
import com.outreachmappingsolutions.repositories.ClientDemographicsRepository;
import com.outreachmappingsolutions.services.ClientBaseService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.config.name=application-test"})
@AutoConfigureMockMvc
@Import(DatabaseTestConfiguration.class)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientDemographicsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientDemographicsRepository clientDemographicsRepository;

    @Autowired
    private ClientDemographicsService clientDemographicsService;

    @Autowired
    private ClientBaseRepository clientBaseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    ClientBase testClient;
    ClientDemographics testClientDemographics1;
    ClientDemographics testClientDemographics2;
    ClientDemographics testClientDemographics3;
    ClientDemographics testUpdatedClientDemographics;

    @BeforeEach
    public void setup(){

        testClient = new ClientBase("John", null, "Doe", null, null, null, null, null, null, null, null, null);
        clientBaseRepository.save(testClient);

        testClientDemographics1 = createTestClientDemographics("Male", "White/Caucasian", "Black/African-American",
                "Hispanic", "Not a Veteran");
        testClientDemographics2 = createTestClientDemographics("Female", "Black/African-American", "Asian/Pacific Islander",
                "Non-Hispanic", "Is a Veteran");
        testClientDemographics3 = createTestClientDemographics("Male", "Asian/Pacific Islander", "Not/Applicable",
                "Non-Hispanic", "Not a Veteran");

        testClientDemographics1.setClient(testClient);
    }

    private ClientDemographics createTestClientDemographics(String gender, String racePrimary, String raceSecondary,
                                                            String ethnicity, String veteranStatus){
        ClientDemographics clientDemographics = new ClientDemographics();
        clientDemographics.setGender(gender);
        clientDemographics.setRacePrimary(racePrimary);
        clientDemographics.setRaceSecondary(raceSecondary);
        clientDemographics.setEthnicity(ethnicity);
        clientDemographics.setVeteranStatus(veteranStatus);
        return clientDemographics;
    }

    @Test
    public void testReturnAllClientDemographicsSuccess() throws Exception {
        clientDemographicsRepository.save(testClientDemographics1);
        clientDemographicsRepository.save(testClientDemographics2);
        clientDemographicsRepository.save(testClientDemographics3);
        List<ClientDemographics> allTestClientDemographics =
                (List<ClientDemographics>) clientDemographicsService.returnAllClientDemographics().getBody();

        assertThat(allTestClientDemographics, notNullValue());
        mockMvc.perform(get("/clientDemographics/returnAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        for(int i=0; i<allTestClientDemographics.size(); i++){
            mockMvc.perform(get("/clientDemographics/returnAll"))
                    .andExpect(jsonPath("$[" + i + "].id", is (allTestClientDemographics.get(i).getId())))
                    .andExpect(jsonPath("$[" + i + "].gender", is (allTestClientDemographics.get(i).getGender())))
                    .andExpect(jsonPath("$[" + i + "].racePrimary", is (allTestClientDemographics.get(i).getRacePrimary())))
                    .andExpect(jsonPath("$[" + i + "].raceSecondary", is (allTestClientDemographics.get(i).getRaceSecondary())))
                    .andExpect(jsonPath("$[" + i + "].ethnicity", is (allTestClientDemographics.get(i).getEthnicity())))
                    .andExpect(jsonPath("$[" + i + "].veteranStatus", is (allTestClientDemographics.get(i).getVeteranStatus())));
        }
    }

    @Test
    public void testReturnAllClientDemographicsNotFound() throws Exception {
        mockMvc.perform(get("/clientDemographics/returnAll"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("No client demographics matching your criteria were found")));
    }

    @Test
    public void testReturnClientDemographicsByClientIdSuccess() throws Exception {
        clientDemographicsRepository.save(testClientDemographics1);
        clientDemographicsRepository.save(testClientDemographics2);
        clientDemographicsRepository.save(testClientDemographics3);

        mockMvc.perform(get("/clientDemographics/return/{clientId}", testClient.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testClientDemographics1.getId())))
                .andExpect(jsonPath("$.gender", is(testClientDemographics1.getGender())))
                .andExpect(jsonPath("$.racePrimary", is(testClientDemographics1.getRacePrimary())))
                .andExpect(jsonPath("$.raceSecondary", is(testClientDemographics1.getRaceSecondary())))
                .andExpect(jsonPath("$.ethnicity", is(testClientDemographics1.getEthnicity())))
                .andExpect(jsonPath("$.veteranStatus", is(testClientDemographics1.getVeteranStatus())));
        assertThat(testClientDemographics1.getClient().getId(), is(testClient.getId()));
    }

    @Test
    public void testReturnClientDemographicsByClientIdNotFound() throws Exception {
        mockMvc.perform(get("/clientDemographics/return/{clientId}", testClient.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("No client demographics matching your criteria were found")));
    }

    @Test
    public void testUpdateClientDemographicsSuccess() throws Exception {
        clientDemographicsRepository.save(testClientDemographics1);
        clientDemographicsRepository.save(testClientDemographics2);
        clientDemographicsRepository.save(testClientDemographics3);
        testUpdatedClientDemographics = createTestClientDemographics("Female", "Asian/Pacific Islander", "Not Applicable",
                "Non-Hispanic", "Is a Veteran");

        ClientDemographics returnedTestClientDemographics =
                (ClientDemographics) clientDemographicsService.updateClientDemographics(testClient.getId(),
                        testUpdatedClientDemographics).getBody();

        mockMvc.perform(put("/clientDemographics/update/{clientId}", testClient.getId())
                        .content(objectMapper.writeValueAsString(testUpdatedClientDemographics))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testClientDemographics1.getId())))
                .andExpect(jsonPath("$.gender", is(testUpdatedClientDemographics.getGender())))
                .andExpect(jsonPath("$.racePrimary", is(testUpdatedClientDemographics.getRacePrimary())))
                .andExpect(jsonPath("$.raceSecondary", is(testUpdatedClientDemographics.getRaceSecondary())))
                .andExpect(jsonPath("$.ethnicity", is(testUpdatedClientDemographics.getEthnicity())))
                .andExpect(jsonPath("$.veteranStatus", is(testUpdatedClientDemographics.getVeteranStatus())));
        assertThat(returnedTestClientDemographics.getClient().getId(), is(testClient.getId()));
    }

    @Test
    public void testUpdateClientDemographicsNotFound() throws Exception{
        testUpdatedClientDemographics = new ClientDemographics();

        mockMvc.perform(put("/clientDemographics/update/{clientId}", testClient.getId())
                        .content(objectMapper.writeValueAsString(testUpdatedClientDemographics))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is("No client demographics matching your criteria were found")));
    }
}
