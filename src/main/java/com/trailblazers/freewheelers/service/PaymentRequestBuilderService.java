package com.trailblazers.freewheelers.service;

public interface PaymentRequestBuilderService {

    String buildXMLRequestBody(String cc_no, String cvc, String exp_month, String exp_year, String amount);
}
