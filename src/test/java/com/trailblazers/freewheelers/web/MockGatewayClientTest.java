package com.trailblazers.freewheelers.web;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class MockGatewayClientTest {

    private MockGatewayClient client;
    private String actual;
    private String expected;
    private String VALID_CARD_NO = "4111111111111111";
    private String INVALID_CARD_NO = "123";
    private String CCV = "534";
    private String EXP_MONTH = "11";
    private String EXP_YEAR = "2020";
    private String A_LOT = "2000.10";

    @Before
    public void setUp() throws Exception {
        client = new MockGatewayClient();
    }

    @Test
    public void shouldReturnSuccessResponseForValidRequest() throws Exception {
        expected = "SUCCESS";
        actual = client.paymentRequest(VALID_CARD_NO, CCV, EXP_MONTH, EXP_YEAR, A_LOT) + "";

        assertTrue(actual.contains(expected));
    }

    @Test
    public void shouldFailureResponseForInalidRequest() throws Exception {
        expected = "UNAUTH";
        actual = client.paymentRequest(INVALID_CARD_NO, CCV, EXP_MONTH, EXP_YEAR, A_LOT) + "";

        assertTrue(actual.contains(expected));
    }
}
