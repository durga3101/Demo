package com.trailblazers.freewheelers.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MockGatewayClient implements GatewayClient {

    private String valid = "4111111111111111";

    private String success = "<authorisation-response>" +
                             "<SUCCESS id=\"13248487483\" />" +
                             "</authorisation-response>";

    private String failure = "<authorisation-response>" +
                             "<UNAUTH id=\"13248487483\" />" +
                             "</authorisation-response>";

    @Override
    public ResponseEntity<String> paymentRequest(String cc_no, String cvc, String exp_month, String exp_year, String amount) {

        if (cc_no.equals(valid)) return new ResponseEntity<String>(success, new HttpHeaders(), HttpStatus.OK);

        return new ResponseEntity<String>(failure, new HttpHeaders(), HttpStatus.OK);
    }
}
