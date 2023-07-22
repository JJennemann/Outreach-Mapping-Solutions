package com.outreachmappingsolutions.Services;

import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.repositories.ClientDemographicsRepository;
import com.outreachmappingsolutions.services.ClientDemographicsService;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientDemographicsServiceUnitTest {

    @Mock
    private ClientDemographicsRepository clientDemographicsRepository;

    @InjectMocks
    private ClientDemographicsService clientDemographicsService;

    ClientBase testClient1;
    ClientBase testClient2;
    ClientDemographics testClientDemographics1;
    ClientDemographics testUpdatedClientDemographics;
    List<ClientDemographics> allClientDemographics = new ArrayList<>();

    @BeforeEach
    public void createTestData() {
        MockitoAnnotations.openMocks(this);

        testClient1 = new ClientBase("John", null, "Doe", null, null, null, null, null, null, null, null, null);
        testClient1.setId(1);
        testClientDemographics1 = new ClientDemographics();
        testClientDemographics1.setId(1);
        testClientDemographics1.setClient(testClient1);
        testClient1.setClientDemographics(testClientDemographics1);

        testClient2 = new ClientBase("Jane", null, "Doe", null, null, null, null, null, null, null, null, null);
        testClient2.setId(2);

        allClientDemographics.add(testClientDemographics1);

        testUpdatedClientDemographics = new ClientDemographics();
        testUpdatedClientDemographics.setGender("Female");
        testUpdatedClientDemographics.setRacePrimary("White/Caucasian");
        testUpdatedClientDemographics.setRaceSecondary("Asian/Pacific Islander");
        testUpdatedClientDemographics.setEthnicity("Non-Hispanic");
        testUpdatedClientDemographics.setVeteranStatus("Not a Veteran");
    }

    @Test
    public void testReturnAllClientDemographicsSuccess(){
        when(clientDemographicsRepository.findAll()).thenReturn(allClientDemographics);
        ResponseEntity<?> response = clientDemographicsService.returnAllClientDemographics();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(allClientDemographics));
        verify(clientDemographicsRepository).findAll();
        verifyNoMoreInteractions(clientDemographicsRepository);
    }

    @Test
    public void testReturnAllClientDemographicsNotFound(){
        when(clientDemographicsRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = clientDemographicsService.returnAllClientDemographics();

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client demographics matching your criteria were found"));
        verify(clientDemographicsRepository).findAll();
        verifyNoMoreInteractions(clientDemographicsRepository);
    }

    @Test
    public void testReturnClientDemographicsByClientIdSuccess(){
        when(clientDemographicsRepository.findByClientId(testClient1.getId())).thenReturn(Optional.of(testClientDemographics1));
        ResponseEntity<?> response = clientDemographicsService.returnClientDemographicsByClientId(testClient1.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(testClientDemographics1));
        verify(clientDemographicsRepository).findByClientId(testClient1.getId());
        verifyNoMoreInteractions(clientDemographicsRepository);
    }

    @Test
    public void testReturnClientDemographicsByClientIdNotFound(){
        when(clientDemographicsRepository.findByClientId(testClient2.getId())).thenReturn(Optional.empty());
        ResponseEntity<?> response = clientDemographicsService.returnClientDemographicsByClientId(testClient2.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client demographics matching your criteria were found"));
        verify(clientDemographicsRepository).findByClientId(testClient2.getId());
        verifyNoMoreInteractions(clientDemographicsRepository);
    }

    @Test
    public void testUpdateClientDemographicsSuccess(){
        when(clientDemographicsRepository.findByClientId(testClient1.getId())).thenReturn(Optional.of(testClientDemographics1));
        ResponseEntity<?> response = clientDemographicsService.updateClientDemographics(testClient1.getId(), testUpdatedClientDemographics);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Client demographics were successfully updated"));
        assertThat(testClientDemographics1.getId(), is(1));
        assertThat(testClientDemographics1.getClient().getId(), is(testClient1.getId()));
        assertThat(testClientDemographics1.getGender(), is(testUpdatedClientDemographics.getGender()));
        assertThat(testClientDemographics1.getRacePrimary(), is(testUpdatedClientDemographics.getRacePrimary()));
        assertThat(testClientDemographics1.getRaceSecondary(), is(testUpdatedClientDemographics.getRaceSecondary()));
        assertThat(testClientDemographics1.getEthnicity(), is(testUpdatedClientDemographics.getEthnicity()));
        assertThat(testClientDemographics1.getVeteranStatus(), is(testUpdatedClientDemographics.getVeteranStatus()));
        verify(clientDemographicsRepository).findByClientId(testClient1.getId());
        verify(clientDemographicsRepository).save(testClientDemographics1);
        verifyNoMoreInteractions(clientDemographicsRepository);
    }

    @Test
    public void testUpdateClientDemographicsNotFound(){
        when(clientDemographicsRepository.findByClientId(testClient2.getId())).thenReturn(Optional.empty());
        ResponseEntity<?> response = clientDemographicsService.updateClientDemographics(testClient2.getId(), testUpdatedClientDemographics);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client demographics matching your criteria were found"));
        verify(clientDemographicsRepository).findByClientId(testClient2.getId());
        verifyNoMoreInteractions(clientDemographicsRepository);
    }
}

















