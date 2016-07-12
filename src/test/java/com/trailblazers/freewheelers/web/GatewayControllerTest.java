package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GatewayControllerTest {

    private GatewayController gatewayController;
    private RestTemplate restTemplate;
    private ItemServiceImpl itemService;
    private Item item;

    @Before
    public void setUp() throws Exception {
        restTemplate= mock(RestTemplate.class);
        itemService = mock(ItemServiceImpl.class);
        gatewayController = new GatewayController(restTemplate, itemService);
    }

    @Test
    public void shouldReturnRedirectToErrorWhenAPICallToPaymentGatewayFails() throws Exception {
        when(restTemplate.postForEntity(anyString(), any(), any(Class.class))).thenReturn(new ResponseEntity<String>("NET_ERR", HttpStatus.OK));
        String expected = "redirect:/gateway/reserve-error";
        String actual = gatewayController.post(null, "cc_number", "csc", "expiry_month", "expiry_year", "amount");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRedirectToReservePageWhenAPICallToPayentGatewaySucceeds() throws Exception {
        when(restTemplate.postForEntity(anyString(), any(), any(Class.class))).thenReturn(new ResponseEntity<String>("SUCCESS", HttpStatus.OK));

        item = mock(Item.class);
        when(itemService.get(anyLong())).thenReturn(item);

        HttpSession mockSession = mock(HttpSession.class);
        when(mockSession.getAttribute("itemOnConfirm")).thenReturn(item);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getSession()).thenReturn(mockSession);

        String expected = "redirect:/reserve";
        String actual = gatewayController.post(mockRequest, "cc_number", "csc", "expiry_month", "expiry_year", "amount");
        assertEquals(expected, actual);
    }
}
