package com.trailblazers.freewheelers.service.impl;

import org.junit.Before;
import org.junit.Test;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

public class PaymentRequestBuilderServiceImplTest {

    private PaymentRequestBuilderServiceImpl builder;

    @Before
    public void setUp() throws Exception {
        builder = new PaymentRequestBuilderServiceImpl();

    }

    @Test
    public void shouldReturnCorrectlyFormattedXMLRequestBody() throws Exception {
        String expected =   "<authorisation-request>" +
                                "<cc_number>123</cc_number>" +
                                    "<csc>456</csc>" +
                                    "<expiry>01-2016</expiry>" +
                                "<amount>0.00</amount>" +
                            "</authorisation-request>";

        String actual = builder.buildXMLRequestBody("123","456","01","2016", "0.00");
        assertEquals(actual, expected);
    }
}
