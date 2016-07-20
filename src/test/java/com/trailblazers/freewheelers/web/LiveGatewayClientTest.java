package com.trailblazers.freewheelers.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LiveGatewayClientTest {

    private static final String CC_NO = "123";
    private static final String CVC = "456";
    private static final String MONTH = "01";
    private static final String YEAR = "12";
    private static final String AMOUNT = "10.19";
    private LiveGatewayClient client;
    private RestTemplate restTemplate;
    private ResponseEntity<String> expected;

    @Before
    public void setUp() throws Exception {
        restTemplate = mock(RestTemplate.class);
        expected = mock(ResponseEntity.class);
        client = new LiveGatewayClient(restTemplate);
    }

    @Test
    public void shouldReturnResponseStringGivenRequestParams() throws Exception {

        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), any(Class.class))).thenReturn(expected);

        ResponseEntity<String> actual = client.paymentRequest(CC_NO, CVC, MONTH, YEAR, AMOUNT);

        assertEquals(expected, actual);
    }
}
