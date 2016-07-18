package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.service.impl.PaymentRequestBuilderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.security.Principal;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GatewayControllerTest {

    private GatewayController gatewayController;
    private RestTemplate restTemplate;
    private ItemServiceImpl itemService;
    private Item item;
    private PaymentRequestBuilderServiceImpl builder;
    private Principal principal;
    private AccountService accountService;
    private ReserveOrderService reserveOrderService;

    @Before
    public void setUp() throws Exception {
        restTemplate= mock(RestTemplate.class);
        itemService = mock(ItemServiceImpl.class);
        builder = mock(PaymentRequestBuilderServiceImpl.class);
        when(builder.buildXMLRequestBody(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn("fake XML");

        accountService = mock(AccountService.class);
        reserveOrderService = mock(ReserveOrderService.class);
        principal = mock(Principal.class);
        gatewayController = new GatewayController(reserveOrderService, accountService, restTemplate, itemService, builder);

    }

    @Test
    public void shouldReturnRedirectToErrorWhenAPICallToPaymentGatewayFails() throws Exception {
        when(restTemplate.postForEntity(anyString(), any(), any(Class.class))).thenReturn(new ResponseEntity<String>("NET_ERR", HttpStatus.OK));
        String expected = "redirect:/gateway/reserve-error";
        String actual = gatewayController.post(null, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRedirectToReservePageWhenAPICallToPaymentGatewaySucceeds() throws Exception {
        Account account = mock(Account.class);
        when(accountService.getAccountIdByName(anyString())).thenReturn(account);

        when(restTemplate.postForEntity(anyString(), any(), any(Class.class))).thenReturn(new ResponseEntity<String>("SUCCESS", HttpStatus.OK));

        item = mock(Item.class);

        when(itemService.get(anyLong())).thenReturn(item);

        HttpSession mockSession = mock(HttpSession.class);
        when(mockSession.getAttribute("itemOnConfirm")).thenReturn(item);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getSession()).thenReturn(mockSession);


        String expected = "redirect:/reserve";
        String actual = gatewayController.post(mockRequest, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldAddItemToPurchasedItemAttributeWhenPurchaseIsSuccessful() {
        Account account = mock(Account.class);

        when(restTemplate.postForEntity(anyString(), any(), any(Class.class))).thenReturn(new ResponseEntity<String>("SUCCESS", HttpStatus.OK));

        item = mock(Item.class);
        when(itemService.get(anyLong())).thenReturn(item);
        when(accountService.getAccountIdByName(anyString())).thenReturn(account);

        HttpSession mockSession = mock(HttpSession.class);
        when(mockSession.getAttribute("itemOnConfirm")).thenReturn(item);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getSession()).thenReturn(mockSession);

        gatewayController.post(mockRequest, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");

        verify(mockSession).setAttribute("purchasedItem", item);
    }
}
