package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.PurchasedItem;
import com.trailblazers.freewheelers.model.ShippingAddress;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.OrderService;
import com.trailblazers.freewheelers.service.PurchasedItemService;
import com.trailblazers.freewheelers.service.ShippingAddressService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
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
import static com.trailblazers.freewheelers.web.Session.ORDER;
import static com.trailblazers.freewheelers.web.Session.SHOPPING_CART;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class GatewayControllerTest {

    private GatewayController gatewayController;
    private LiveGatewayClient client;
    private ItemServiceImpl itemService;
    private Principal principal;
    private AccountService accountService;
    private PurchasedItemService purchasedItemService;
    private HttpSession httpSession;
    private HttpServletRequest request;
    private Account account;
    private HashMap<Item, Long> fullCart;
    private Item item1;
    private Item item2;
    private HashMap<Item, Long> items;
    private Session session;
    private OrderService orderService;
    private ShippingAddressService shippingAddressService;
    private Order order;
    private Date date;

    @Before
    public void setUp() throws Exception {
        client = mock(LiveGatewayClient.class);
        itemService = mock(ItemServiceImpl.class);
        httpSession = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        account = mock(Account.class);
        session = mock(Session.class);
        orderService = mock(OrderService.class);
        order = mock(Order.class);


        accountService = mock(AccountService.class);
        purchasedItemService = mock(PurchasedItemService.class);
        shippingAddressService = mock(ShippingAddressService.class);
        principal = mock(Principal.class);

        gatewayController = new GatewayController(orderService, purchasedItemService,accountService, itemService, client, session,shippingAddressService);
        when(request.getSession()).thenReturn(httpSession);
        when(principal.getName()).thenReturn("Luke");
        when(accountService.getAccountIdByName("Luke")).thenReturn(account);
        when(account.getAccount_id()).thenReturn(11L);

        item1 = mock(Item.class);
        item2 = mock(Item.class);
        gatewayController = new GatewayController(orderService, purchasedItemService, accountService, itemService, client, session, shippingAddressService);
        items = new HashMap<>();
        fullCart = new HashMap<>();
        items.put(item1, 3L);
        items.put(item2, 2L);

        when(client.paymentRequest(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(new ResponseEntity<String>("SUCCESS", HttpStatus.OK));
        when(request.getSession()).thenReturn(httpSession);
        when(principal.getName()).thenReturn("Luke");
        when(accountService.getAccountIdByName("Luke")).thenReturn(account);

        when(accountService.getAccountFromEmail(anyString())).thenReturn(account);

        when(account.getAccount_id()).thenReturn(11L);
        when(item1.getItemId()).thenReturn(6L);
        when(item2.getItemId()).thenReturn(7L);

        date = mock(Date.class);
        when(orderService.createOrder(account)).thenReturn(order);
        when(order.getOrder_id()).thenReturn(1l);
        when(order.getReservation_timestamp()).thenReturn(date);
        when(date.toString()).thenReturn("10-10-2016");
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
        when(accountService.getAccountFromEmail(anyString())).thenReturn(account);
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(items);
        when(orderService.createOrder(account)).thenReturn(order);


        String expected = "redirect:/reserve";
        String actual = gatewayController.post(request, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");
        assertEquals(expected, actual);
    }

    @Test
    public void postShouldUpdateSessionAttributes() throws Exception {

        HashMap<Item, Long> fullCart = new HashMap<>();
        fullCart.put(new Item(), 2L);

        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(fullCart);
        when(orderService.createOrder(account)).thenReturn(order);

        gatewayController.post(request, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");

        verify(httpSession).setAttribute(SHOPPING_CART, null);
        verify(httpSession).setAttribute(PURCHASED_ITEMS, fullCart);

    }

    @Test
    public void postShouldUpdateDatabaseToDecrementItems() throws Exception {
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(items);
        when(orderService.createOrder(account)).thenReturn(order);

        gatewayController.post(request, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");

        verify(itemService, times(3)).decreaseQuantityByOne(item1);
        verify(itemService, times(2)).decreaseQuantityByOne(item2);
    }

    @Test
    public void shouldCreateANewOrderWhenPaymentIsSuccessful(){
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(items);
        when(orderService.createOrder(account)).thenReturn(order);


        gatewayController.post(request, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");

        verify(orderService).createOrder(account);
        verify(purchasedItemService, times(5)).save(any(PurchasedItem.class));
    }

    @Test
    public void shouldSaveAllPurchasedInModelItemsWithOrderID(){
        when(orderService.createOrder(any(Account.class))).thenReturn(order);

        gatewayController.post(request, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");

        verify(httpSession).setAttribute(ORDER,1l);

    }
    public void postShouldStoreAddressInDatabaseIfPaymentIsSuccessful() throws Exception {
        ShippingAddress shippingAddress = mock(ShippingAddress.class);
        when(httpSession.getAttribute("shippingAddress")).thenReturn(shippingAddress);
        gatewayController.post(request, principal, "cc_number", "csc", "expiry_month", "expiry_year", "amount");
        verify(shippingAddressService).createShippingAddress(shippingAddress);
    }
}
