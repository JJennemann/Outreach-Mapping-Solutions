package com.outreachmappingsolutions.Controllers;

import com.outreachmappingsolutions.controllers.ClientContactInfoController;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientContactInfoControllerUnitTest {

    @Mock
    private ClientContactInfoService clientContactInfoService;

    @InjectMocks
    private ClientContactInfoController clientContactInfoController;

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
    public void testReturnAllClientContactInfoSuccess() {
        ResponseEntity<?> expectedResponse = new ResponseEntity<>(allClientContactInfo, HttpStatus.OK);
        when(clientContactInfoService.returnAllClientContactInfo()).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientContactInfoController.returnAllClientContactInfo();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(allClientContactInfo));
        verify(clientContactInfoService).returnAllClientContactInfo();
        verifyNoMoreInteractions(clientContactInfoService);
    }

    @Test
    public void testReturnAllClientContactInfoNotFound(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("No client contact information matching your " +
                "criteria was found", HttpStatus.NOT_FOUND);
        when(clientContactInfoService.returnAllClientContactInfo()).thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientContactInfoController.returnAllClientContactInfo();

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));
        verify(clientContactInfoService).returnAllClientContactInfo();
        verifyNoMoreInteractions(clientContactInfoService);
    }

    @Test
    public void testReturnClientContactInfoByClientIdSuccess(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>(testClientContactInfo1, HttpStatus.OK);
        when(clientContactInfoService.returnClientContactInfoByClientId(testClient1.getId()))
                .thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientContactInfoController.returnClientContactInfoByClientId(testClient1.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(testClientContactInfo1));
        verify(clientContactInfoService).returnClientContactInfoByClientId(testClient1.getId());
        verifyNoMoreInteractions(clientContactInfoService);
    }

    @Test
    public void testReturnClientContactInfoByClientIdNotFound(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("No client contact information matching your " +
                "criteria was found", HttpStatus.NOT_FOUND);
        when(clientContactInfoService.returnClientContactInfoByClientId(testClient1.getId()))
                .thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientContactInfoController.returnClientContactInfoByClientId(testClient1.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));
        verify(clientContactInfoService).returnClientContactInfoByClientId(testClient1.getId());
        verifyNoMoreInteractions(clientContactInfoService);
    }

    @Test
    public void testUpdateClientContactInfoSuccess(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("Client contact information was " +
                "successfully updated", HttpStatus.OK);
        when(clientContactInfoService.updateClientContactInfo(testClient1.getId(), testUpdatedClientContactInfo))
                .then(invocation -> expectedResponse);

        ResponseEntity<?> response = clientContactInfoController.updateClientContactInfo(testClient1.getId(), testUpdatedClientContactInfo);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Client contact information was successfully updated"));
        verify(clientContactInfoService).updateClientContactInfo(testClient1.getId(), testUpdatedClientContactInfo);
        verifyNoMoreInteractions(clientContactInfoService);

    }

    @Test
    public void testUpdateClientContactInfoNotFound(){
        ResponseEntity<?> expectedResponse = new ResponseEntity<>("No client contact information matching " +
                "your criteria was found", HttpStatus.NOT_FOUND);

        when(clientContactInfoService.updateClientContactInfo(testClientContactInfo1.getId(), testUpdatedClientContactInfo))
                .thenAnswer(invocation -> expectedResponse);

        ResponseEntity<?> response = clientContactInfoController.updateClientContactInfo(testClient1.getId(), testUpdatedClientContactInfo);

        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));
        verify(clientContactInfoService).updateClientContactInfo(testClient1.getId(), testUpdatedClientContactInfo);
        verifyNoMoreInteractions(clientContactInfoService);
    }
}
