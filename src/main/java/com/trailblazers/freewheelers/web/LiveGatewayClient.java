package com.trailblazers.freewheelers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LiveGatewayClient implements GatewayClient {

    private RestTemplate restTemplate;

    @Autowired
    public LiveGatewayClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String GATEWAY_URL = "http://ops.freewheelers.bike:5000/authorise";

    @Override
    public ResponseEntity<String> paymentRequest(String cc_no, String cvc, String exp_month, String exp_year, String amount) {
        String body = createRequestBody(cc_no, cvc, exp_month, exp_year, amount);

        HttpEntity<String> request = createRequest(body);
        ResponseEntity<String> response = restTemplate.postForEntity(GATEWAY_URL, request, String.class);

        return response;
    }

    private String createRequestBody (String cc_no, String cvc, String exp_month, String exp_year, String amount) {

        String expiry = exp_month + "-" + exp_year;

        String result =
                "<authorisation-request>" +
                        "<cc_number>" + cc_no + "</cc_number>" +
                        "<csc>" + cvc + "</csc>" +
                        "<expiry>" + expiry + "</expiry>" +
                        "<amount>" + amount + "</amount>" +
                        "</authorisation-request>";

        return result;
    }

    private HttpEntity<String> createRequest(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        return new HttpEntity<>(body, headers);
    }
}
