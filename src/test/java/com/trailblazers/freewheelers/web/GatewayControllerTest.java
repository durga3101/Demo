package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.OrderService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.service.impl.OrderServiceImpl;
import com.trailblazers.freewheelers.service.impl.PaymentRequestBuilderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import static com.trailblazers.freewheelers.web.GatewayController.PURCHASED_ITEMS;
import static com.trailblazers.freewheelers.web.Session.SHOPPING_CART;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class GatewayControllerTest {

    private GatewayController gatewayController;
    private  LiveGatewayClient client;
    private ItemServiceImpl itemService;
    private Principal principal;
    private AccountService accountService;
    private ReserveOrderService reserveOrderService;
    private HttpSession httpSession;
    private HttpServletRequest request;
    private Account account;
    private HashMap<Item, Long> fullCart;
    private Item item1;
    private Item item2;
    private HashMap<Item, Long> items;
    private Session session;
    private OrderService orderService;


    @Before
    public void setUp() throws Exception {
        client = mock(LiveGatewayClient.class);
        itemService = mock(ItemServiceImpl.class);
        httpSession = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        account = mock(Account.class);
        session = mock(Session.class);
        orderService = mock(OrderService.class);

        when(builder.buildXMLRequestBody(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn("fake XML");

        accountService = mock(AccountService.class);
        reserveOrderService = mock(ReserveOrderService.class);
        principal = mock(Principal.class);

        gatewayController = new GatewayController(orderService, reserveOrderService, accountService, restTemplate, itemService, builder, session);
        when(request.getSession()).thenReturn(httpSession);
        when(restTemplate.postForEntity(anyString(), any(), any(Class.class))).thenReturn(new ResponseEntity<String>("SUCCESS", HttpStatus.OK));
        when(principal.getName()).thenReturn("Luke");
        when(accountService.getAccountIdByName("Luke")).thenReturn(account);
        when(account.getAccount_id()).thenReturn(11L);

        item1 = mock(Item.class);
        item2 = mock(Item.class);
        gatewayController = new GatewayController(reserveOrderService, accountService, itemService, client, session);
        items = new HashMap<>();
        fullCart = new HashMap<>();
        items.put(item1, 3L);
        items.put(item2, 2L);

        when(client.paymentRequest(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(new ResponseEntity<String>("SUCCESS", HttpStatus.OK));
        when(request.getSession()).thenReturn(httpSession);
        when(principal.getName()).thenReturn("Luke");
        when(accountService.getAccountIdByName("Luke")).thenReturn(account);
        when(account.getAccount_id()).thenReturn(11L);
        when(item1.getItemId()).thenReturn(6L);
        when(item2.getItemId()).thenReturn(7L);
    }

    @Test
    public void shouldReturnRedirectToErrorWhenAPICallToPaymentGatewayFails() throws Exception {
        when(client.paymentRequest(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(new ResponseEntity<String>("NET_ERR", HttpStatus.OK));
        String expected = "redirect:/gateway/reserve-error";
        String actual = gatewayController.post(null, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnRedirectToReservePageWhenAPICallToPaymentGatewaySucceeds() throws Exception {
        Account account = mock(Account.class);
        when(accountService.getAccountIdByName(anyString())).thenReturn(account);
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(items);

        String expected = "redirect:/reserve";
        String actual = gatewayController.post(request, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");
        assertEquals(expected, actual);
    }

    @Test
    public void postShouldUpdateSessionAttributes() throws Exception {

        HashMap<Item, Long> fullCart = new HashMap<>();
        fullCart.put(new Item(), 2L);

        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(fullCart);

        gatewayController.post(request, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");

        verify(httpSession).setAttribute(SHOPPING_CART, null);
        verify(httpSession).setAttribute(PURCHASED_ITEMS, fullCart);

    }

    @Test
    public void postShouldUpdateDatabaseToDecrementItems() throws Exception {
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(items);

        gatewayController.post(request, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");

        verify(itemService, times(3)).decreaseQuantityByOne(item1);
        verify(itemService, times(2)).decreaseQuantityByOne(item2);
    }

    @Test
    public void shouldCreateANewOrderWhenPaymentIsSuccessful(){
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(items);

        gatewayController.post(request, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");

        verify(orderService).createOrder(account);
    }

//    @Test
//    public void shouldSaveAllPurchasedItemsWithOrderID(){
//
//        gatewayController.post(request, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");
//
//        verify(reserveOrderService).save((ReserveOrder) any(), anyLong());
//    }

}
