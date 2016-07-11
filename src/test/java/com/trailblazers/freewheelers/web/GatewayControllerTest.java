package com.trailblazers.freewheelers.web;

import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GatewayControllerTest {
//    @Test
//    public void checkTheGateway() throws Exception {
//        GatewayController gatewayController = new GatewayController();
//        RestTemplate restTemplate=mock(RestTemplate.class);
//        gatewayController.post("cc_number","csc","expiry_month","expiry_year", "amount");
//        HttpEntity<String> request = new HttpEntity<>("",new HttpHeaders());
//        verify(restTemplate).postForEntity(anyString(), any(), Matchers.<Class<Object>>any());
//    }
//}
