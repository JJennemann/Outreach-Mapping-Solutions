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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
//
    ClientBase testClient;
//    ClientBase testClient2;
//    ClientBase testClient3;
    ClientDemographics testClientDemographics1;
    ClientDemographics testClientDemographics2;
    ClientDemographics testClientDemographics3;
    ClientDemographics testUpdatedClientDemographics;

    @BeforeEach
    public void setup(){

        testClient = new ClientBase("John", null, "Doe", null, null, null, null, null, null, null, null, null);
        clientBaseRepository.save(testClient);
//        clientBaseService.createNewClient(testClient);
//
//
//        testClient2 = new ClientBase("Jane", null, null, null, null, null, null, null, null, null, null, null);
//        clientBaseService.createNewClient(testClient2);
//
//        testClient3 = new ClientBase("Steve", null, "Richardson", null, null, null, null, null, null, null, null, null);
//        clientBaseService.createNewClient(testClient3);

        testClientDemographics1 = new ClientDemographics();
        testClientDemographics1.setGender("Male");
        testClientDemographics1.setRacePrimary("White/Caucasian");
        testClientDemographics1.setRaceSecondary("Black/African-American");
        testClientDemographics1.setEthnicity("Hispanic");
        testClientDemographics1.setVeteranStatus("Not A Veteran");
        testClientDemographics1.setClient(testClient);


        testClientDemographics2 = new ClientDemographics();
        testClientDemographics2.setGender("Female");
        testClientDemographics2.setRacePrimary("Black/African-American");
        testClientDemographics2.setRaceSecondary("Asian/Pacific Islander");
        testClientDemographics2.setEthnicity("Non-Hispanic");
        testClientDemographics2.setVeteranStatus("Is A Veteran");

        testClientDemographics3 = new ClientDemographics();
        testClientDemographics3.setGender("Male");
        testClientDemographics3.setRacePrimary("Asian/Pacific Islander");
        testClientDemographics3.setRaceSecondary("Not/Applicable");
        testClientDemographics3.setEthnicity("Non-Hispanic");
        testClientDemographics3.setVeteranStatus("Not A Veteran");
    }

    @Test
    public void testReturnAllClientDemographicsSuccess() throws Exception {
        clientDemographicsRepository.save(testClientDemographics1);
        clientDemographicsRepository.save(testClientDemographics2);
        clientDemographicsRepository.save(testClientDemographics3);
        List<ClientDemographics> allTestClientDemographics =
                (List<ClientDemographics>) clientDemographicsService.returnAllClientDemographics().getBody();

        assert allTestClientDemographics !=null;
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
        String allTestClientDemographics = (String) clientDemographicsService.returnAllClientDemographics().getBody();

        assert allTestClientDemographics.equals("No client demographics matching your criteria were found");
        mockMvc.perform(get("/clientDemographics/returnAll"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testReturnClientDemographicsByClientIdSuccess() throws Exception {
        clientDemographicsRepository.save(testClientDemographics1);
        clientDemographicsRepository.save(testClientDemographics2);
        clientDemographicsRepository.save(testClientDemographics3);

        ClientDemographics returnedTestClientDemographics = (ClientDemographics) clientDemographicsService.returnClientDemographicsByClientId(testClient.getId()).getBody();

        assert returnedTestClientDemographics != null;
        mockMvc.perform(get("/clientDemographics/return/{clientId}", testClient.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.gender", is("Male")))
                .andExpect(jsonPath("$.racePrimary", is("White/Caucasian")))
                .andExpect(jsonPath("$.raceSecondary", is("Black/African-American")))
                .andExpect(jsonPath("$.ethnicity", is("Hispanic")))
                .andExpect(jsonPath("$.veteranStatus", is("Not A Veteran")));
    }

    @Test
    public void testReturnClientDemographicsByClientIdNotFound() throws Exception {
        String allTestClientDemographics = (String) clientDemographicsService.returnClientDemographicsByClientId(testClient.getId()).getBody();

        assert allTestClientDemographics.equals("No client demographics matching your criteria were found");
        mockMvc.perform(get("/clientDemographics/return/{clientId}", testClient.getId()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateClientDemographicsSuccess() throws Exception {
        clientDemographicsRepository.save(testClientDemographics1);
        clientDemographicsRepository.save(testClientDemographics2);
        clientDemographicsRepository.save(testClientDemographics3);
        testUpdatedClientDemographics = new ClientDemographics();
        testUpdatedClientDemographics.setGender("Female");
        testUpdatedClientDemographics.setRacePrimary("Asian/Pacific Islander");
        testUpdatedClientDemographics.setRaceSecondary("Not Applicable");
        testUpdatedClientDemographics.setEthnicity("Non-Hispanic");
        testUpdatedClientDemographics.setVeteranStatus("Is A Veteran");
        ClientDemographics returnedTestClientDemographics = (ClientDemographics) clientDemographicsService.updateClientDemographics(testClient.getId(), testUpdatedClientDemographics).getBody();

        assert returnedTestClientDemographics != null;
        mockMvc.perform(put("/clientDemographics/update/{clientId}", testClient.getId())
                        .content(objectMapper.writeValueAsString(testUpdatedClientDemographics))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.gender", is("Female")))
                .andExpect(jsonPath("$.racePrimary", is("Asian/Pacific Islander")))
                .andExpect(jsonPath("$.raceSecondary", is("Not Applicable")))
                .andExpect(jsonPath("$.ethnicity", is("Non-Hispanic")))
                .andExpect(jsonPath("$.veteranStatus", is("Is A Veteran")));

        assertThat(returnedTestClientDemographics.getClient().getId(), is(testClient.getId()));
    }

    @Test
    public void testUpdateClientDemographicsNotFound() throws Exception{
        testUpdatedClientDemographics = new ClientDemographics();
        String returnedTestClientDemographics = (String) clientDemographicsService.updateClientDemographics(testClient.getId(), testUpdatedClientDemographics).getBody();

        assert returnedTestClientDemographics.equals("No client demographics matching your criteria were found");
        mockMvc.perform(put("/clientDemographics/update/{clientId}", testClient.getId())
                        .content(objectMapper.writeValueAsString(testUpdatedClientDemographics))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
