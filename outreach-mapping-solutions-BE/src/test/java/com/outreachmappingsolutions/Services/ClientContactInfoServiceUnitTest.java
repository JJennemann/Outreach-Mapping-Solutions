package com.outreachmappingsolutions.Services;

import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.repositories.ClientContactInfoRepository;
import com.outreachmappingsolutions.services.ClientContactInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientContactInfoServiceUnitTest {

    @Mock
    private ClientContactInfoRepository clientContactInfoRepository;

    @InjectMocks
    private ClientContactInfoService clientContactInfoService;

    ClientBase testClient1;
    ClientBase testClient2;
    ClientContactInfo testClientContactInfo1;
    ClientContactInfo testUpdatedClientContactInfo;
    List<ClientContactInfo> allClientContactInfo = new ArrayList<>();

    @BeforeEach
    public void createTestData() {
        MockitoAnnotations.openMocks(this);

        testClient1 = new ClientBase("John", null, "Doe", null, null, null, null, null, null, null, null, null);
        testClient1.setId(1);
        testClientContactInfo1 = new ClientContactInfo();
        testClientContactInfo1.setClient(testClient1);
        testClientContactInfo1.setId(1);
        testClient1.setClientContactInfo(testClientContactInfo1);

        testClient2 = new ClientBase("Jane", null, null, null, null, null, null, null, null, null, null, null);
        testClient2.setId(2);

        allClientContactInfo.add(testClientContactInfo1);

        testUpdatedClientContactInfo = new ClientContactInfo();
        testUpdatedClientContactInfo.setPhonePrimary("111-111-1111");
        testUpdatedClientContactInfo.setPhoneSecondary("222-222-2222");
        testUpdatedClientContactInfo.setEmail("email@test.com");
        testUpdatedClientContactInfo.setIceName("James Doe");
        testUpdatedClientContactInfo.setIceRelationship("Brother");
        testUpdatedClientContactInfo. setIcePhonePrimary("333-333-3333");
        testUpdatedClientContactInfo.setIcePhoneSecondary("444-444-4444");
        testUpdatedClientContactInfo.setIceEmail("test@email.com");
    }

    @Test
    public void testReturnAllClientContactInfoSuccess(){
        when(clientContactInfoRepository.findAll()).thenReturn(allClientContactInfo);
        ResponseEntity<?> response = clientContactInfoService.returnAllClientContactInfo();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(allClientContactInfo));
        verify(clientContactInfoRepository).findAll();
        verifyNoMoreInteractions(clientContactInfoRepository);
    }

    @Test
    public void testReturnAllClientContactInfoNotFound(){
        when(clientContactInfoRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = clientContactInfoService.returnAllClientContactInfo();

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));
        verify(clientContactInfoRepository).findAll();
        verifyNoMoreInteractions(clientContactInfoRepository);
    }

    @Test
    public void testReturnAllClientContactInfoError(){
        when(clientContactInfoRepository.findAll()).thenThrow(new RuntimeException());
        ResponseEntity<?> response = clientContactInfoService.returnAllClientContactInfo();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to retrieve all client contact information", response.getBody());
        verify(clientContactInfoRepository).findAll();
        verifyNoMoreInteractions(clientContactInfoRepository);
    }

    @Test
    public void testReturnClientContactInfoByClientIdSuccess(){
        when(clientContactInfoRepository.findByClientId(testClient1.getId())).thenReturn(Optional.of(testClientContactInfo1));
        ResponseEntity<?> response = clientContactInfoService.returnClientContactInfoByClientId(testClient1.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(testClientContactInfo1));
        verify(clientContactInfoRepository).findByClientId(testClient1.getId());
        verifyNoMoreInteractions(clientContactInfoRepository);
    }

    @Test
    public void testReturnClientContactInfoByClientIdNotFound(){
        when(clientContactInfoRepository.findByClientId(testClient2.getId())).thenReturn(Optional.empty());
        ResponseEntity<?> response = clientContactInfoService.returnClientContactInfoByClientId(testClient2.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));
        verify(clientContactInfoRepository).findByClientId(testClient2.getId());
        verifyNoMoreInteractions(clientContactInfoRepository);
    }

    @Test
    public void testReturnClientContactInfoByClientIdError(){
        when(clientContactInfoRepository.findByClientId(testClient1.getId())).thenThrow(new RuntimeException());
        ResponseEntity<?> response = clientContactInfoService.returnClientContactInfoByClientId(testClient1.getId());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to retrieve the client's contact information", response.getBody());
        verify(clientContactInfoRepository).findByClientId(testClient1.getId());
        verifyNoMoreInteractions(clientContactInfoRepository);
    }

    @Test
    public void testUpdateClientContactInfoSuccess(){
        when(clientContactInfoRepository.findByClientId(testClient1.getId())).thenReturn(Optional.of(testClientContactInfo1));
        ResponseEntity<?> response = clientContactInfoService.updateClientContactInfo(testClient1.getId(), testUpdatedClientContactInfo);
        
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Client contact information was successfully updated"));
        assertThat(testClientContactInfo1.getId(), is(1));
        assertThat(testClientContactInfo1.getClient().getId(), is(testClient1.getId()));
        assertThat(testClientContactInfo1.getPhonePrimary(), is(testUpdatedClientContactInfo.getPhonePrimary()));
        assertThat(testClientContactInfo1.getPhoneSecondary(), is(testUpdatedClientContactInfo.getPhoneSecondary()));
        assertThat(testClientContactInfo1.getEmail(), is(testUpdatedClientContactInfo.getEmail()));
        assertThat(testClientContactInfo1.getIceName(), is(testUpdatedClientContactInfo.getIceName()));
        assertThat(testClientContactInfo1.getIceRelationship(), is(testUpdatedClientContactInfo.getIceRelationship()));
        assertThat(testClientContactInfo1.getIcePhonePrimary(), is(testUpdatedClientContactInfo.getIcePhonePrimary()));
        assertThat(testClientContactInfo1.getIcePhoneSecondary(), is(testUpdatedClientContactInfo.getIcePhoneSecondary()));
        assertThat(testClientContactInfo1.getIceEmail(), is(testUpdatedClientContactInfo.getIceEmail()));
        verify(clientContactInfoRepository).findByClientId(testClient1.getId());
        verify(clientContactInfoRepository).save(testClientContactInfo1);
        verifyNoMoreInteractions(clientContactInfoRepository);
        
    }

    @Test
    public void testUpdateClientContactInfoNotFound(){
        when(clientContactInfoRepository.findByClientId(testClient2.getId())).thenReturn(Optional.empty());
        ResponseEntity<?> response = clientContactInfoService.updateClientContactInfo(testClient2.getId(), testUpdatedClientContactInfo);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));
        verify(clientContactInfoRepository).findByClientId(testClient2.getId());
        verifyNoMoreInteractions(clientContactInfoRepository);
    }

    @Test
    public void testUpdateClientContactInfoError(){
        when(clientContactInfoRepository.findByClientId(testClient1.getId())).thenThrow(new RuntimeException());
        ResponseEntity<?> response = clientContactInfoService.updateClientContactInfo(testClient1.getId(), testUpdatedClientContactInfo);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to update the client's contact information", response.getBody());
        verify(clientContactInfoRepository).findByClientId(testClient1.getId());
        verifyNoMoreInteractions(clientContactInfoRepository);
    }
}
