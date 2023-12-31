package com.outreachmappingsolutions.Services;
import com.outreachmappingsolutions.models.ClientBase;
import com.outreachmappingsolutions.models.ClientContactInfo;
import com.outreachmappingsolutions.repositories.ClientBaseRepository;
import com.outreachmappingsolutions.repositories.ClientContactInfoRepository;
import com.outreachmappingsolutions.services.ClientContactInfoService;
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
public class ClientContactInfoServiceIntegrationTest {

    @Autowired
    private ClientContactInfoRepository clientContactInfoRepository;

    @Autowired
    private ClientContactInfoService clientContactInfoService;

    @Autowired
    private ClientBaseRepository clientBaseRepository;

    ClientBase testClient;
    ClientContactInfo testClientContactInfo1;
    ClientContactInfo testClientContactInfo2;
    ClientContactInfo testClientContactInfo3;
    ClientContactInfo testUpdatedClientContactInfo;

    @BeforeEach
    public void setup(){
        testClient = new ClientBase("Miles", null, "O'Brien", null, null, null, null, null, null, null, null, null);
        clientBaseRepository.save(testClient);

        testClientContactInfo1 = createTestClientContactInfo("111-111-1111", "111-111-1112", "miles@test.com",
                "Keiko O'Brien", "Ex-Wife", "111-111-1113", "111-111-1114", "keiko@test.com");
        testClientContactInfo2 = createTestClientContactInfo("222-222-2221", "222-222-2222", "wesley@test.com",
                "Beverly Crusher", "Mother", "222-222-2223", "222-222-2224", "dr.crusher@email.com");
        testClientContactInfo3 = createTestClientContactInfo("111-111-1111", "222-222-2222", "thomas@test.com",
                "Will Riker", "Brother", "333-333-3333", "444-444-4444", "will@email.com");
        testUpdatedClientContactInfo = createTestClientContactInfo("111-111-1115", "111-111-1116", "miles@test.com",
                "Julian Bashir", "Friend", "111-111-1117", "111-111-1118", "julian@test.com");

        testClientContactInfo1.setClient(testClient);
    }

    private ClientContactInfo createTestClientContactInfo(String phonePrimary, String phoneSecondary, String email,
                                                          String iceName, String iceRelationship,
                                                          String icePhonePrimary, String icePhoneSecondary, String iceEmail) {
        ClientContactInfo clientContactInfo = new ClientContactInfo();
        clientContactInfo.setPhonePrimary(phonePrimary);
        clientContactInfo.setPhoneSecondary(phoneSecondary);
        clientContactInfo.setEmail(email);
        clientContactInfo.setIceName(iceName);
        clientContactInfo.setIceRelationship(iceRelationship);
        clientContactInfo.setIcePhonePrimary(icePhonePrimary);
        clientContactInfo.setIcePhoneSecondary(icePhoneSecondary);
        clientContactInfo.setIceEmail(iceEmail);
        return clientContactInfo;
    }

    @Test
    public void testReturnAllClientContactInfoSuccess(){
        clientContactInfoRepository.save(testClientContactInfo1);
        clientContactInfoRepository.save(testClientContactInfo2);
        clientContactInfoRepository.save(testClientContactInfo3);

        ResponseEntity<?> response = clientContactInfoService.returnAllClientContactInfo();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        List<ClientContactInfo> responseBody = (List<ClientContactInfo>) response.getBody();
        assertThat(responseBody, hasSize(3));

        List<ClientContactInfo> allTestClientContactInfo = (List<ClientContactInfo>) clientContactInfoRepository.findAll();
        assertThat(allTestClientContactInfo, hasSize(3));

        assertThat(allTestClientContactInfo.get(0), is(responseBody.get(0)));
        assertThat(allTestClientContactInfo.get(1), is(responseBody.get(1)));
        assertThat(allTestClientContactInfo.get(2), is(responseBody.get(2)));
    }

    @Test
    public void testReturnAllClientContactInfoNotFound(){
        ResponseEntity<?> response = clientContactInfoService.returnAllClientContactInfo();
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));

        List<ClientContactInfo> allTestClientContactInfo = (List<ClientContactInfo>) clientContactInfoRepository.findAll();
        assertThat(allTestClientContactInfo, is(empty()));
    }

    @Test
    public void testReturnClientContactInfoByClientIdSuccess(){
        clientContactInfoRepository.save(testClientContactInfo1);

        ResponseEntity<?> response = clientContactInfoService.returnClientContactInfoByClientId(testClient.getId());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ClientContactInfo responseBody = (ClientContactInfo) response.getBody();
        ClientContactInfo returnedTestClientContactInfo = clientContactInfoRepository.findByClientId(testClient.getId()).get();

        assertThat(responseBody, notNullValue());
        assertThat(returnedTestClientContactInfo, is(responseBody));
    }

    @Test
    public void testReturnClientContactInfoByClientIdNotFound(){
        ResponseEntity<?> response = clientContactInfoService.returnClientContactInfoByClientId(1);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));

        Optional<ClientContactInfo> returnedTestClientContactInfo = clientContactInfoRepository.findByClientId(1);
        assertThat(returnedTestClientContactInfo, is(Optional.empty()));
    }

    @Test
    public void testUpdateClientContactInfo(){
        clientContactInfoRepository.save(testClientContactInfo1);

        ResponseEntity<?> response = clientContactInfoService.updateClientContactInfo(testClient.getId(), testUpdatedClientContactInfo);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ClientContactInfo responseBody = (ClientContactInfo) response.getBody();
        ClientContactInfo returnedTestClientContactInfo = clientContactInfoRepository.findByClientId(testClient.getId()).get();

        assertThat(responseBody, notNullValue());
        assertThat(returnedTestClientContactInfo, is(responseBody));
//        assertThat(returnedTestClientContactInfo.getPhonePrimary(), is(responseBody.getPhonePrimary()));
//        assertThat(returnedTestClientContactInfo.getPhoneSecondary(), is(responseBody.getPhoneSecondary()));
//        assertThat(returnedTestClientContactInfo.getEmail(), is(responseBody.getEmail()));
//        assertThat(returnedTestClientContactInfo.getIceName(), is(responseBody.getIceName()));
//        assertThat(returnedTestClientContactInfo.getIceRelationship(), is(responseBody.getIceRelationship()));
//        assertThat(returnedTestClientContactInfo.getIcePhonePrimary(), is(responseBody.getIcePhonePrimary()));
//        assertThat(returnedTestClientContactInfo.getIcePhoneSecondary(), is(responseBody.getIcePhoneSecondary()));
//        assertThat(returnedTestClientContactInfo.getIceEmail(), is(responseBody.getIceEmail()));
    }

    @Test
    public void testUpdateClientContactInfoNotFound(){
        ResponseEntity<?> response = clientContactInfoService.updateClientContactInfo(1, testUpdatedClientContactInfo);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(), is("No client contact information matching your criteria was found"));

        Optional<ClientContactInfo> returnedTestClientContactInfo = clientContactInfoRepository.findByClientId(1);
        assertThat(returnedTestClientContactInfo, is(Optional.empty()));
    }

}
