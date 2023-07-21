package com.outreachingmappingsolutions.outreachmappingsolutions.Controllers;

import com.outreachmappingsolutions.outreachmappingsolutions.controllers.ClientDemographicsController;
import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.outreachmappingsolutions.services.ClientDemographicsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientDemographicsControllerUnitTest {

    @Mock
    private ClientDemographicsService clientDemographicsService;

    @InjectMocks
    private ClientDemographicsController clientDemographicsController;

    ClientBase testClient1;
    ClientBase testClient2;
    ClientDemographics testClientDemographics1;
    ClientDemographics testClientDemographics2;
    ClientDemographics testUpdatedClientDemographics;
    List<ClientDemographics> allClientDemographics = new ArrayList<>();

    @BeforeEach
    public void createTestData() {
        MockitoAnnotations.openMocks(this);

        testClient1 = new ClientBase("John", null, "Doe", null, null, null, null, null, null, null, null, null);
        testClient1.setId(1);
        testClientDemographics1 = new ClientDemographics(testClient1, "Male", "Black/African-American", null, null, null);
        testClientDemographics1.setId(1);
        testClient1.setClientDemographics(testClientDemographics1);

        testClient2 = new ClientBase("Jane", null, "Doe", null, null, null, null, null, null, null, null, null);
        testClient2.setId(2);
        testClientDemographics2 = new ClientDemographics(testClient2, null, null, null, null, null);
        testClientDemographics2.setId(2);
        testClient2.setClientDemographics(testClientDemographics2);

        allClientDemographics.add(testClientDemographics1);


        testUpdatedClientDemographics = new ClientDemographics(testClient1, "Female", "White/Caucasian", "Not Applicable",
                "Non-Hispanic", "Veteran");
    }

    @Test
    public void testReturnAllClientDemographicsSuccess() {
        ResponseEntity<?> expectedResponse = new ResponseEntity<>(allClientDemographics, HttpStatus.OK);
        when(clientDemographicsService.returnAllClientDemographics()).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientDemographicsController.returnAllClientDemographics();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(allClientDemographics));
        verify(clientDemographicsService).returnAllClientDemographics();
        verifyNoMoreInteractions(clientDemographicsService);
    }

    @Test
    public void testReturnAllClientDemographicsNotFound() {
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("No client demographics matching your criteria" +
                " were found", HttpStatus.NOT_FOUND);
        when(clientDemographicsService.returnAllClientDemographics()).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientDemographicsController.returnAllClientDemographics();

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client demographics matching your criteria were found"));
        verify(clientDemographicsService).returnAllClientDemographics();
        verifyNoMoreInteractions(clientDemographicsService);
    }

    @Test
    public void testReturnClientDemographicsByClientIdSuccess() {
        ResponseEntity<?> expectedResponse = new ResponseEntity<>(testClientDemographics1, HttpStatus.OK);
        when(clientDemographicsService.returnClientDemographicsByClientId(testClient1.getId()))
                .thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientDemographicsController.returnClientDemographicsByClientId(testClient1.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(testClientDemographics1));
        verify(clientDemographicsService).returnClientDemographicsByClientId(testClient1.getId());
        verifyNoMoreInteractions(clientDemographicsService);
    }

    @Test
    public void testReturnClientDemographicsByClientIdNotFound() {
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("No client demographics matching your criteria" +
                " were found", HttpStatus.NOT_FOUND);
        when(clientDemographicsService.returnClientDemographicsByClientId(testClient1.getId()))
                .thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientDemographicsController.returnClientDemographicsByClientId(testClient1.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client demographics matching your criteria were found"));
        verify(clientDemographicsService).returnClientDemographicsByClientId(testClient1.getId());
        verifyNoMoreInteractions(clientDemographicsService);
    }

    @Test
    public void testUpdateClientDemographics() {
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("Client demographics were successfully updated", HttpStatus.OK);
        when(clientDemographicsService.updateClientDemographics(testClient1.getId(), testUpdatedClientDemographics))
                .thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientDemographicsController.updateClientDemographics(testClient1.getId(), testUpdatedClientDemographics);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Client demographics were successfully updated"));
        verify(clientDemographicsService).updateClientDemographics(testClient1.getId(), testUpdatedClientDemographics);
        verifyNoMoreInteractions(clientDemographicsService);
    }

    @Test
    public void testUpdateClientDemographicsNotFound() {
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("No client demographics matching your criteria" +
                " were found", HttpStatus.NOT_FOUND);
        when(clientDemographicsService.updateClientDemographics(testClient1.getId(), testUpdatedClientDemographics))
                .thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientDemographicsController.updateClientDemographics(testClient1.getId(), testUpdatedClientDemographics);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client demographics matching your criteria were found"));
        verify(clientDemographicsService).updateClientDemographics(testClient1.getId(), testUpdatedClientDemographics);
        verifyNoMoreInteractions(clientDemographicsService);
    }
}

