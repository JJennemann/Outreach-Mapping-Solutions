package com.outreachmappingsolutions.Services;

import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.models.ClientDemographics;
import com.outreachmappingsolutions.repositories.ClientBaseRepository;
import com.outreachmappingsolutions.services.ClientBaseService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@ExtendWith(MockitoExtension.class)
public class ClientBaseServiceUnitTest {

    @Mock
    private ClientBaseRepository clientBaseRepository;

    @InjectMocks
    private ClientBaseService clientBaseService;

    ClientBase testClient1;
    ClientBase testClient2;
    ClientBase testUpdatedClientBase;
    List<ClientBase> allClients = new ArrayList<>();

    @BeforeEach
    public void createTestData(){
        MockitoAnnotations.openMocks(this);

        testClient1 = new ClientBase("John", "James", "Doe", "Data Quality Complete", "February", "28", "2001",
                "Data Quality Complete", 111, 11, 1111, "Data Quality Complete");

        testClient2 = new ClientBase("Jane", null, null, null, null, null, null, null, null, null, null, null);
        testClient2.setId(2);

        testUpdatedClientBase = new ClientBase("Jack", "Delano", "Johnson", "Data Quality Complete", "January", "1",
                "1999", "Data Quality Complete", 222, 22, 2222, "Data Quality Complete");
    }

    @Test
    public void testCreateNewClientSuccess(){
        AtomicInteger generatedId = new AtomicInteger(1);

        doAnswer(invocation -> {
            ClientBase clientBase = invocation.getArgument(0);
            clientBase.setId(generatedId.getAndIncrement());
            return clientBase;
        }).when(clientBaseRepository).save(any(ClientBase.class));

        ResponseEntity<?> response = clientBaseService.createNewClient(testClient1);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody(), is(1));
        verify(clientBaseRepository).save(any(ClientBase.class));
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testCreateNewClientError(){
        when(clientBaseRepository.save(any(ClientBase.class))).thenThrow(new RuntimeException());

        ResponseEntity<?> response = clientBaseService.createNewClient(testClient1);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to create new client", response.getBody());
        verify(clientBaseRepository).save(testClient1);
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testReturnAllClientsSuccess(){
        testClient1.setId(1);
        allClients.add(testClient1);

        when(clientBaseRepository.findAll()).thenReturn(allClients);
        ResponseEntity<?> response = clientBaseService.returnAllClients();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(allClients));
        verify(clientBaseRepository).findAll();
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testReturnAllClientsNotFound(){
        when(clientBaseRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = clientBaseService.returnAllClients();

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));
        verify(clientBaseRepository).findAll();
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testReturnAllClientsError(){
        when(clientBaseRepository.findAll()).thenThrow(new RuntimeException());

        ResponseEntity<?> response = clientBaseService.returnAllClients();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to retrieve clients", response.getBody());
        verify(clientBaseRepository).findAll();
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testReturnClientByIdSuccess(){
        when(clientBaseRepository.findById(testClient1.getId())).thenReturn(Optional.of(testClient1));
        ResponseEntity<?> response = clientBaseService.returnClientById(testClient1.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(testClient1));
        verify(clientBaseRepository).findById(testClient1.getId());
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testReturnClientByIdNotFound(){
        when(clientBaseRepository.findById(testClient2.getId())).thenReturn(Optional.empty());
        ResponseEntity<?> response = clientBaseService.returnClientById(testClient2.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));
        verify(clientBaseRepository).findById(testClient2.getId());
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testReturnClientByIdError(){
        when(clientBaseRepository.findById(testClient1.getId())).thenThrow(new RuntimeException());

        ResponseEntity<?> response = clientBaseService.returnClientById(testClient1.getId());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to retrieve the client", response.getBody());
        verify(clientBaseRepository).findById(testClient1.getId());
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testUpdateClientSuccess(){
        testClient1.setId(1);
        when(clientBaseRepository.findById(testClient1.getId())).thenReturn(Optional.of(testClient1));
        ResponseEntity<?> response = clientBaseService.updateClient(testClient1.getId(), testUpdatedClientBase);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Client was successfully updated"));
        assertThat(testClient1.getId(), is(1));
        assertThat(testClient1.getFirstName(), is("Jack"));
        assertThat(testClient1.getMiddleName(), is("Delano"));
        assertThat(testClient1.getLastName(), is("Johnson"));
        assertThat(testClient1.getNameDataQuality(), is("Data Quality Complete"));
        assertThat(testClient1.getDobMonth(), is("January"));
        assertThat(testClient1.getDobDay(), is("1"));
        assertThat(testClient1.getDobYear(), is("1999"));
        assertThat(testClient1.getDobDataQuality(), is("Data Quality Complete"));
        assertThat(testClient1.getFirstThreeSsn(), is(222));
        assertThat(testClient1.getMiddleTwoSsn(), is(22));
        assertThat(testClient1.getLastFourSsn(), is(2222));
        assertThat(testClient1.getSsnDataQuality(), is("Data Quality Complete"));
        verify(clientBaseRepository).findById(testClient1.getId());
        verify(clientBaseRepository).save(testClient1);
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testUpdateClientNotFound(){
    when(clientBaseRepository.findById(testClient2.getId())). thenReturn(Optional.empty());
    ResponseEntity<?> response = clientBaseService.updateClient(testClient2.getId(), testUpdatedClientBase);

    assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    assertThat(response.getBody(), is("No clients matching your criteria were found"));
    verify(clientBaseRepository).findById(testClient2.getId());
    verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testUpdateClientError(){
        when(clientBaseRepository.findById(testClient1.getId())).thenThrow(new RuntimeException());

        ResponseEntity<?> response = clientBaseService.updateClient(testClient1.getId(), testUpdatedClientBase);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to update the client", response.getBody());
        verify(clientBaseRepository).findById(testClient1.getId());
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testDeleteClientSuccess(){
        when(clientBaseRepository.findById(testClient1.getId())).thenReturn(Optional.of(testClient1));
        ResponseEntity<?> response = clientBaseService.deleteClient(testClient1.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Client was successfully deleted"));
        verify(clientBaseRepository).findById(testClient1.getId());
        verify(clientBaseRepository).deleteById(testClient1.getId());
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testDeleteClientNotFound(){
        when(clientBaseRepository.findById(testClient2.getId())).thenReturn(Optional.empty());
        ResponseEntity<?> response = clientBaseService.deleteClient(testClient2.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No clients matching your criteria were found"));
        verify(clientBaseRepository).findById(testClient2.getId());
        verifyNoMoreInteractions(clientBaseRepository);
    }

    @Test
    public void testDeleteClientError(){
        when(clientBaseRepository.findById(testClient1.getId())).thenThrow(new RuntimeException());

        ResponseEntity<?> response = clientBaseService.deleteClient(testClient1.getId());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to delete the client", response.getBody());
        verify(clientBaseRepository).findById(testClient1.getId());
        verifyNoMoreInteractions(clientBaseRepository);
    }

}
