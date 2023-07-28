package com.outreachmappingsolutions.Services;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.repositories.ClientBaseRepository;
import com.outreachmappingsolutions.repositories.ClientDemographicsRepository;
import com.outreachmappingsolutions.services.ClientDemographicsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.config.name=application-test"})
@Transactional
public class ClientDemographicsServiceIntegrationTest {

    @Autowired
    private ClientDemographicsRepository clientDemographicsRepository;

    @Autowired
    private ClientDemographicsService clientDemographicsService;

    @Autowired
    private ClientBaseRepository clientBaseRepository;
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
        testUpdatedClientDemographics = createTestClientDemographics("Female", "Asian/Pacific Islander", "Not Applicable",
                "Non-Hispanic", "Is a Veteran");

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
    public void testReturnAllClientDemographicsSuccess(){
        clientDemographicsRepository.save(testClientDemographics1);
        clientDemographicsRepository.save(testClientDemographics2);
        clientDemographicsRepository.save(testClientDemographics3);


        ResponseEntity<?> response = clientDemographicsService.returnAllClientDemographics();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        List<ClientDemographics> responseBody = (List<ClientDemographics>) response.getBody();
        assertThat(responseBody, hasSize(3));

        List<ClientDemographics> allClientDemographics = (List<ClientDemographics>) clientDemographicsRepository.findAll();
        assertThat(allClientDemographics, hasSize(3));

        assertThat(allClientDemographics.get(0), is(responseBody.get(0)));
        assertThat(allClientDemographics.get(1), is(responseBody.get(1)));
        assertThat(allClientDemographics.get(2), is(responseBody.get(2)));
    }

    @Test
    public void testReturnAllClientDemographicsNotFound(){
        ResponseEntity<?> response = clientDemographicsService.returnAllClientDemographics();
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client demographics matching your criteria were found"));

        List<ClientDemographics> allTestClientDemographics = (List<ClientDemographics>) clientDemographicsRepository.findAll();
        assertThat(allTestClientDemographics, is(empty()));
    }

    @Test
    public void testReturnClientDemographicsByClientIdSuccess(){
        clientDemographicsRepository.save(testClientDemographics1);

        ResponseEntity<?> response = clientDemographicsService.returnClientDemographicsByClientId(testClient.getId());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ClientDemographics responseBody = (ClientDemographics) response.getBody();
        ClientDemographics returnedTestClientDemographics = clientDemographicsRepository.findByClientId(testClient.getId()).get();

        assertThat(responseBody, notNullValue());
        assertThat(returnedTestClientDemographics, is(responseBody));
    }

    @Test
    public void testReturnClientDemographicsByClientIdNotFound(){
        ResponseEntity<?> response = clientDemographicsService.returnClientDemographicsByClientId(1);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client demographics matching your criteria were found"));

        Optional<ClientDemographics> returnedTestClientDemographics = clientDemographicsRepository.findByClientId(1);
        assertThat(returnedTestClientDemographics, is(Optional.empty()));
    }

    @Test
    public void testUpdateClientContactInfo(){
        clientDemographicsRepository.save(testClientDemographics1);

        ResponseEntity<?> response = clientDemographicsService.updateClientDemographics(testClient.getId(), testUpdatedClientDemographics);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ClientDemographics responseBody = (ClientDemographics) response.getBody();
        ClientDemographics returnedTestClientDemographics = clientDemographicsRepository.findByClientId(testClient.getId()).get();

        assertThat(responseBody, notNullValue());
        assertThat(returnedTestClientDemographics, is(responseBody));
//        assertThat(returnedTestClientDemographics.getGender(), is(responseBody.getGender()));
//        assertThat(returnedTestClientDemographics.getRacePrimary(), is(responseBody.getRacePrimary()));
//        assertThat(returnedTestClientDemographics.getRaceSecondary(), is(responseBody.getRaceSecondary()));
//        assertThat(returnedTestClientDemographics.getEthnicity(), is(responseBody.getEthnicity()));
//        assertThat(returnedTestClientDemographics.getVeteranStatus(), is(responseBody.getVeteranStatus()));
    }

    @Test
    public void testUpdateClientDemographicsNotFound(){
        ResponseEntity<?> response = clientDemographicsService.updateClientDemographics(1, testUpdatedClientDemographics);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client demographics matching your criteria were found"));

        Optional<ClientDemographics> returnedTestClientDemographics = clientDemographicsRepository.findByClientId(1);
        assertThat(returnedTestClientDemographics, is(Optional.empty()));
    }

}
