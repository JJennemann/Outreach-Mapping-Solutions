package com.outreachmappingsolutions.Services;

import com.outreachmappingsolutions.Config.DatabaseTestConfiguration;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.repositories.ClientBaseRepository;
import com.outreachmappingsolutions.services.ClientBaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.config.name=application-test"})
@Import(DatabaseTestConfiguration.class)
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
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody(), notNullValue());
    }
}
