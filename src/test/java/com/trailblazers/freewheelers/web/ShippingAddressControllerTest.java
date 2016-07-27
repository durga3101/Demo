package com.trailblazers.freewheelers.web;


import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.ShippingAddress;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class ShippingAddressControllerTest {

    private HttpServletRequest request;
    private HttpSession httpSession;
    private ShippingAddress shippingAddress;
    private AccountService accountService;
    private ShippingAddressController shippingAddressController;
    private Model model;
    private Principal principal;
    private ItemService itemService;
    private Account account;
    private HashMap<Long, Long> cart;

    @Before
    public void setUp() throws Exception {
        shippingAddress = mock(ShippingAddress.class);
        accountService = mock(AccountService.class);
        model = mock(Model.class);
        request = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        principal = mock(Principal.class);
        itemService = mock(ItemService.class);
        account = mock(Account.class);
        shippingAddressController = new ShippingAddressController(accountService, itemService);

        cart = mock(HashMap.class);
    }

    @Test
    public void shouldReturnHomePageWhenShoppingCartIsEmpty() throws Exception {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("shoppingCart")).thenReturn(null);

        String actual = shippingAddressController.get(model, request, principal);

        assertEquals("redirect:/", actual);
    }

    @Test
    public void shouldRedirectToPaymentPage() throws Exception {
        request = getHttpServletRequest();
        String userName = "ABC";
        when(principal.getName()).thenReturn(userName);
        when(accountService.getAccountFromEmail(userName)).thenReturn(account);
        when(request.getSession()).thenReturn(httpSession);
        shippingAddressController.getShippingAddress(request, principal);

        ArgumentCaptor<ShippingAddress> captor = ArgumentCaptor.forClass(ShippingAddress.class);
        verify(accountService).getAccountFromEmail(userName);
        verify(httpSession).setAttribute(anyString(), Matchers.any());
    }

    @Test
    public void shouldGetUserCountryFromAccountDatabase() throws Exception {
        when(request.getHeader("Referer")).thenReturn("some");
        when(accountService.getAccountFromEmail(anyString())).thenReturn(account);
        when(account.getCountry()).thenReturn("UK");
        when(principal.getName()).thenReturn("ABC");

        shippingAddressController.get(model, request, principal);

        verify(accountService).getAccountFromEmail(anyString());
        verify(model).addAttribute("country", "UK");

    }

    private HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("street1")).thenReturn("street1");
        when(request.getParameter("street2")).thenReturn("street2");
        when(request.getParameter("city")).thenReturn("city");
        when(request.getParameter("state")).thenReturn("state");
        when(request.getParameter("postcode")).thenReturn("123456789");

        return request;
    }

    @Test
    public void shouldAddAmountInTheModel() {
        request = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        model = mock(Model.class);

        when(request.getHeader("Referer")).thenReturn("some");
        when(principal.getName()).thenReturn("ABC");
        when(accountService.getAccountFromEmail(anyString())).thenReturn(account);
        when(account.getCountry()).thenReturn("USA");
        
        shippingAddressController.get(model, request, principal);

        verify(model).addAttribute("country","USA");

    }

    @Test
    public void shouldSetDefaultCountryAsUKWhenUserDoesNotHaveCountry() throws Exception {
        request = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        model = mock(Model.class);

        when(request.getHeader("Referer")).thenReturn("some");
        when(principal.getName()).thenReturn("ABC");
        when(accountService.getAccountFromEmail(anyString())).thenReturn(account);
        when(account.getCountry()).thenReturn(null);

        shippingAddressController.get(model, request, principal);

        verify(model).addAttribute("country","UK");
    }
}