package com.trailblazers.freewheelers.web;

import org.junit.Before;
import org.junit.Test;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.*;
import static junit.framework.TestCase.assertTrue;

public class MockGatewayClientTest {

    private MockGatewayClient client;
    private String actual;
    private String expected;

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
