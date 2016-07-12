package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.service.PaymentRequestBuilderService;

public class PaymentRequestBuilderServiceImpl implements PaymentRequestBuilderService {

    @Override
    public String buildXMLRequestBody(String cc_no, String cvc, String exp_month, String exp_year, String amount) {

        String expiry = concatExpiry(exp_month, exp_year);

        String result =
                "<authorisation-request>" +
                        "<cc_number>" + cc_no + "</cc_number>" +
                        "<csc>" + cvc + "</csc>" +
                        "<expiry>" + expiry + "</expiry>" +
                        "<amount>" + amount + "</amount>" +
                "</authorisation-request>";

        return result;
    }

    private String concatExpiry (String month, String year) {
        return month + "-" + year;
    }
}
