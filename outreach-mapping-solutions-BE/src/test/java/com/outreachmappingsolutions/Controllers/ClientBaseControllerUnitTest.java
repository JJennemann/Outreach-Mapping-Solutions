package com.outreachmappingsolutions.Controllers;

import com.outreachmappingsolutions.controllers.ClientBaseController;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.services.ClientBaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientBaseControllerUnitTest {

    @Mock
    private ClientBaseService clientBaseService;

    @InjectMocks
    private ClientBaseController clientBaseController;

    ClientBase testClient1;
    ClientBase testClient2;
    ClientBase testClientBase;
    List<ClientBase> allClients = new ArrayList<>();

    @BeforeEach
    public void createTestData(){
        MockitoAnnotations.openMocks(this);

        testClientBase = new ClientBase("John", null, "Doe", null, null, null, null, null, null, null, null, null);
        testClient1 = new ClientBase();
        testClient1.setId(1);

        testClient2 = new ClientBase();
        testClient2.setId(2);
    }

    @Test
    public void testCreateNewClientSuccess(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>(1, HttpStatus.CREATED);
        when(clientBaseService.createNewClient(testClientBase)).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientBaseController.createNewClient(testClientBase);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody(), is(1));
        verify(clientBaseService).createNewClient(testClientBase);
        verifyNoMoreInteractions(clientBaseService);
    }

    @Test
    public void testReturnAllClientsSuccess(){
        allClients.add(testClient1);
        ResponseEntity<?> expectedResponse = new ResponseEntity<>(allClients, HttpStatus.OK);
        when(clientBaseService.returnAllClients()).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientBaseController.returnAllClients();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(allClients));
        verify(clientBaseService).returnAllClients();
        verifyNoMoreInteractions(clientBaseService);
    }

    @Test
    public void testReturnAllClientsNotFound(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("No clients matching your criteria were found", HttpStatus.NOT_FOUND);
        when(clientBaseService.returnAllClients()).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientBaseController.returnAllClients();

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));
        verify(clientBaseService).returnAllClients();
        verifyNoMoreInteractions(clientBaseService);
    }

    @Test
    public void testReturnClientByIdSuccess(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>(testClient1, HttpStatus.OK);
        when(clientBaseService.returnClientById(testClient1.getId())).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientBaseController.returnClientById(testClient1.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(testClient1));
        verify(clientBaseService).returnClientById(testClient1.getId());
        verifyNoMoreInteractions(clientBaseService);

    }

    @Test
    public void testReturnClientByIdNotFound(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("No clients matching your criteria were found", HttpStatus.NOT_FOUND);
        when(clientBaseService.returnClientById(testClient2.getId())).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientBaseController.returnClientById(testClient2.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));
        verify(clientBaseService).returnClientById(testClient2.getId());
        verifyNoMoreInteractions(clientBaseService);
    }

    @Test
    public void testUpdateClientSuccess(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("Client was successfully updated", HttpStatus.OK);
        when(clientBaseService.updateClient(testClient1.getId(), testClientBase)).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientBaseController.updateClient(testClient1.getId(), testClientBase);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Client was successfully updated"));
        verify(clientBaseService).updateClient(testClient1.getId(), testClientBase);
        verifyNoMoreInteractions(clientBaseService);
    }

    @Test
    public void testUpdateClientNotFound(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("No clients matching your criteria were found", HttpStatus.NOT_FOUND);
        when(clientBaseService.updateClient(testClient2.getId(), testClientBase)).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientBaseController.updateClient(testClient2.getId(), testClientBase);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));
        verify(clientBaseService).updateClient(testClient2.getId(), testClientBase);
        verifyNoMoreInteractions(clientBaseService);
    }

    @Test
    public void testDeleteClientSuccess(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("Client was successfully deleted", HttpStatus.OK);
        when(clientBaseService.deleteClient(testClient1.getId())).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientBaseController.deleteClient(testClient1.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Client was successfully deleted"));
        verify(clientBaseService).deleteClient(testClient1.getId());
        verifyNoMoreInteractions(clientBaseService);
    }

    @Test
    public void testDeleteClientNotFound(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("No clients matching your criteria were found", HttpStatus.NOT_FOUND);
        when(clientBaseService.deleteClient(testClient2.getId())).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientBaseController.deleteClient(testClient2.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));
        verify(clientBaseService).deleteClient(testClient2.getId());
        verifyNoMoreInteractions(clientBaseService);
    }
}
