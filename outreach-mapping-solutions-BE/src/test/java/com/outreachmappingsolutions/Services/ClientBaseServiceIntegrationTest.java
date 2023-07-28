package com.outreachmappingsolutions.Services;

import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.repositories.ClientBaseRepository;
import com.outreachmappingsolutions.services.ClientBaseService;
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
public class ClientBaseServiceIntegrationTest {

    @Autowired
    private ClientBaseRepository clientBaseRepository;

    @Autowired
    private ClientBaseService clientBaseService;

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
    public void testCreateNewClientSuccess(){
        ResponseEntity<?> response = clientBaseService.createNewClient(testClient1);
        ClientBase responseBody = (ClientBase) response.getBody();

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        List<ClientBase> allTestClients = (List<ClientBase>) clientBaseRepository.findAll();
        assertThat(allTestClients, hasSize(1));

        ClientBase createdTestClient = allTestClients.get(0);
        assertThat(responseBody, notNullValue());
        assertThat(createdTestClient, is(responseBody));
//        assertThat(createdTestClient.getFirstName(), is(responseBody.getFirstName()));
//        assertThat(createdTestClient.getMiddleName(), is(responseBody.getMiddleName()));
//        assertThat(createdTestClient.getLastName(), is(responseBody.getLastName()));
//        assertThat(createdTestClient.getNameDataQuality(), is(responseBody.getNameDataQuality()));
//        assertThat(createdTestClient.getDobMonth(), is(responseBody.getDobMonth()));
//        assertThat(createdTestClient.getDobDay(), is(responseBody.getDobDay()));
//        assertThat(createdTestClient.getDobYear(), is(responseBody.getDobYear()));
//        assertThat(createdTestClient.getDobDataQuality(), is(responseBody.getDobDataQuality()));
//        assertThat(createdTestClient.getFirstThreeSsn(), is(responseBody.getFirstThreeSsn()));
//        assertThat(createdTestClient.getMiddleTwoSsn(), is(responseBody.getMiddleTwoSsn()));
//        assertThat(createdTestClient.getLastFourSsn(), is(responseBody.getLastFourSsn()));
//        assertThat(createdTestClient.getSsnDataQuality(), is(responseBody.getSsnDataQuality()));
//        assertThat(createdTestClient.getClientDemographics().getId(), is(responseBody.getClientDemographics().getId()));
//        assertThat(createdTestClient.getClientContactInfo().getId(), is(responseBody.getClientContactInfo().getId()));
    }

    @Test
    public void testReturnAllClientsSuccess(){
        clientBaseRepository.save(testClient1);
        clientBaseRepository.save(testClient2);
        clientBaseRepository.save(testClient3);

        ResponseEntity<?> response = clientBaseService.returnAllClients();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        List<ClientBase> responseBody = (List<ClientBase>) response.getBody();
        assertThat(responseBody, hasSize(3));

        List<ClientBase> allTestClients = (List<ClientBase>) clientBaseRepository.findAll();
        assertThat(allTestClients, hasSize(3));

        assertThat(allTestClients.get(0), is(responseBody.get(0)));
        assertThat(allTestClients.get(1), is(responseBody.get(1)));
        assertThat(allTestClients.get(2), is(responseBody.get(2)));
    }

    @Test
    public void testReturnAllClientsNotFound(){
        ResponseEntity<?> response = clientBaseService.returnAllClients();
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));

        List<ClientBase> allTestClients = (List<ClientBase>) clientBaseRepository.findAll();
        assertThat(allTestClients, is(empty()));
    }

    @Test
    public void testReturnClientByIdSuccess(){
        clientBaseRepository.save(testClient1);

        ResponseEntity<?> response = clientBaseService.returnClientById(testClient1.getId());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ClientBase responseBody = (ClientBase) response.getBody();
        ClientBase returnedTestClient = clientBaseRepository.findById(testClient1.getId()).get();

        assertThat(responseBody, notNullValue());
        assertThat(returnedTestClient, is(responseBody));
    }

    @Test
    public void testReturnClientByIdNotFound(){
        ResponseEntity<?> response = clientBaseService.returnClientById(1);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));

        Optional<ClientBase> returnedTestClient = clientBaseRepository.findById(1);
        assertThat(returnedTestClient, is(Optional.empty()));
    }

    @Test
    public void testUpdateClientSuccess(){
        testClient1.setClientDemographics(new ClientDemographics());
        testClient1.setClientContactInfo(new ClientContactInfo());
        clientBaseRepository.save(testClient1);

        ResponseEntity<?> response = clientBaseService.updateClient(testClient1.getId(), testUpdatedClient);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ClientBase responseBody = (ClientBase) response.getBody();
        ClientBase returnedTestClient = clientBaseRepository.findById(testClient1.getId()).get();

        assertThat(responseBody, notNullValue());
        assertThat(returnedTestClient, is(responseBody));
//        assertThat(returnedTestClient.getFirstName(), is(responseBody.getFirstName()));
//        assertThat(returnedTestClient.getMiddleName(), is(responseBody.getMiddleName()));
//        assertThat(returnedTestClient.getLastName(), is(responseBody.getLastName()));
//        assertThat(returnedTestClient.getNameDataQuality(), is(responseBody.getNameDataQuality()));
//        assertThat(returnedTestClient.getDobMonth(), is(responseBody.getDobMonth()));
//        assertThat(returnedTestClient.getDobDay(), is(responseBody.getDobDay()));
//        assertThat(returnedTestClient.getDobYear(), is(responseBody.getDobYear()));
//        assertThat(returnedTestClient.getDobDataQuality(), is(responseBody.getDobDataQuality()));
//        assertThat(returnedTestClient.getFirstThreeSsn(), is(responseBody.getFirstThreeSsn()));
//        assertThat(returnedTestClient.getMiddleTwoSsn(), is(responseBody.getMiddleTwoSsn()));
//        assertThat(returnedTestClient.getLastFourSsn(), is(responseBody.getLastFourSsn()));
//        assertThat(returnedTestClient.getSsnDataQuality(), is(responseBody.getSsnDataQuality()));
//        assertThat(returnedTestClient.getClientDemographics().getId(), is(responseBody.getClientDemographics().getId()));
//        assertThat(returnedTestClient.getClientContactInfo().getId(), is(responseBody.getClientContactInfo().getId()));
    }

    @Test
    public void testUpdateClientNotFound(){
        ResponseEntity<?> response = clientBaseService.updateClient(1, testUpdatedClient);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));

        Optional<ClientBase> returnedTestClient = clientBaseRepository.findById(1);
        assertThat(returnedTestClient, is(Optional.empty()));
    }

    @Test
    public void testDeleteClientSuccess(){
        clientBaseRepository.save(testClient1);
        clientBaseRepository.save(testClient2);
        clientBaseRepository.save(testClient3);

        ResponseEntity<?> response = clientBaseService.deleteClient(testClient1.getId());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Client was successfully deleted"));

        List<ClientBase> allTestClients = (List<ClientBase>) clientBaseRepository.findAll();

        assertThat(allTestClients, hasSize(2));
        assertThat(allTestClients.get(0), is(testClient2));
        assertThat(allTestClients.get(1), is(testClient3));
    }

    @Test
    public void testDeleteClientNotFound(){
        ResponseEntity<?> response = clientBaseService.deleteClient(1);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));

        Optional<ClientBase> returnedTestClient = clientBaseRepository.findById(1);
        assertThat(returnedTestClient, is(Optional.empty()));
    }
}
