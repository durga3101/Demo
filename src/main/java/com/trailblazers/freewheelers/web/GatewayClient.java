package com.trailblazers.freewheelers.web;

import org.springframework.http.ResponseEntity;

public interface GatewayClient {

    ResponseEntity<String> paymentRequest(String cc_no, String cvc, String exp_month, String exp_year, String amount);

}
