package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.CountryService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashMap;

import static com.trailblazers.freewheelers.web.Session.SHOPPING_CART;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class CartControllerTest {
    private static final String EMPTY_CART = "isCartEmpty";
    private static final String ITEMS = "items";
    public static final String SOME_COUNTRY = "UK";
    private Model model;
    private Principal principal;
    private HttpServletRequest request;
    private CartController cartController;
    private HttpSession httpSession;
    private ItemService itemService;
    private Item item;
    private String actual;
    private String expected;
    private HashMap<Item, Long> emptyCart;
    private Calculator calculator;
    private AccountService accountService;
    private CountryService countryService;
    private Country country;
    private BigDecimal subTotal;
    private BigDecimal vat;
    private BigDecimal duty;
    private Session session;

    @Before
    public void setUp() throws Exception {
        model = mock(Model.class);
        principal = mock(Principal.class);
        request = mock(HttpServletRequest.class);
        itemService = mock(ItemServiceImpl.class);
        calculator = mock(Calculator.class);
        accountService = mock(AccountService.class);
        countryService = mock(CountryService.class);
        session = mock(Session.class);

        cartController = new CartController(itemService, calculator, accountService,countryService, session);
        httpSession = mock(HttpSession.class);
        item = mock(Item.class);

        country = new Country();
        country.setCountry_name(SOME_COUNTRY);
        Account account = new Account();
        account.setCountry(SOME_COUNTRY);
        subTotal = new BigDecimal(50);

        when(request.getSession()).thenReturn(httpSession);
        emptyCart = new HashMap<>();

        when(httpSession.getAttribute("itemForReserve")).thenReturn(item);
        when(countryService.getByName(SOME_COUNTRY)).thenReturn(country);
        when(accountService.getAccountIdByName("ABC")).thenReturn(account);
        when(calculator.calculateVat((BigDecimal)any(),(Country)any())).thenReturn(new BigDecimal(10));
        when(calculator.getGrandTotal((HashMap)any(),(Country)any())).thenReturn(new BigDecimal(60));

        when(principal.getName()).thenReturn("ABC");


        vat = new BigDecimal(10);
        duty = new BigDecimal(56);
        when(calculator.getSubtotalFromCart((HashMap<Item, Long>) any())).thenReturn(new BigDecimal(100));
        when(calculator.calculateVat((BigDecimal) any(), (Country) any())).thenReturn(vat);
        when(calculator.calculateDuty((BigDecimal) any(), (Country) any())).thenReturn(duty);
        when(item.getPrice()).thenReturn(subTotal);

    }

    @Test
    public void getShouldReturnCartJSPWhenPrincipalIsNotNull() {
        when(httpSession.getAttribute("itemForReserve")).thenReturn(item);

        actual = cartController.get(item, request, model, principal);
        expected = "cart";

        assertEquals(expected, actual);
    }

    // currently ignored due to altered payment flow
    @Test
    public void getShouldRedirectToLoginWhenPrincipalIsNull() throws Exception {

        actual = cartController.get(item, request, model, null);
        expected = "redirect:/login";

        assertEquals(expected, actual);
    }

    @Test
    public void getShouldAddItemsFromCartToModel() {
        when(itemService.get(anyLong())).thenReturn(item);
        cartController.get(item, request, model, principal);

        verify(model,atLeast(1)).addAttribute(anyString(), anyMap());
    }


    @Test
    public void getShouldGetItemObjsFromServiceAndAddToModel() throws Exception {
        HashMap<Item, Long> fullCart = emptyCart;
        fullCart.put(item, 1L);

        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(fullCart);

        cartController.get(item, request, model, principal);

        verify(session).getItemHashMap(SHOPPING_CART, httpSession);
        verify(model).addAttribute(ITEMS, fullCart);
        verify(model).addAttribute(EMPTY_CART, false);
        verify(model, never()).addAttribute(EMPTY_CART, true);

    }

    @Test
    public void getShouldSetEmptyCartAttributeOnModelIfCartIsEmpty() throws Exception {
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(emptyCart);

        cartController.get(item, request, model, principal);

        verify(model).addAttribute(EMPTY_CART, true);
        verify(model, never()).addAttribute(ITEMS, emptyCart);
    }

    @Test
    public void shouldCalculateTaxWhenGetIsCalled() throws Exception {

        String result = cartController.get(item,request, model, principal);
        assertEquals("cart",result);
        verify(calculator).calculateVat((BigDecimal)any(), (Country)any());
        verify(accountService).getAccountIdByName("ABC");
        verify(countryService).getByName(SOME_COUNTRY);
        verify(model).addAttribute("vatRate",country.getVat_rate());
        verify(model).addAttribute("dutyRate",country.getDuty_rate());
        verify(model,atLeast(1)).addAttribute("vat",vat);
        verify(model,atLeast(1)).addAttribute("duty",duty);
    }

//    @Test
//    public void shouldCheckNoOfItemsWhenGetIsCalled(){
//
//        HashMap<Long,Long> items = mock(HashMap.class);
//        BigDecimal vat = new BigDecimal(10);
//        when(taxCalculator.calculateVat(subTotal,country)).thenReturn(vat);
//        when(item.getPrice()).thenReturn(subTotal);
//        when(principal.getName()).thenReturn("ABC");
//        when(httpSession.getAttribute("shoppingCart")).thenReturn(items);
//        String result = cartController.get(item,request, model, principal);
//
//        verify(items,atLeast(1)).get(anyLong());
//    }

    @Test
    public void shouldSetDutyRateAsSevenPointFiveIfCountryIsCanadaAndCartContainsMoreThanThreeItems(){
        HashMap<Item,Long> items = mock(HashMap.class);
        Account account = mock(Account.class);
        Country canada = mock(Country.class);
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(items);
        when(accountService.getAccountIdByName(anyString())).thenReturn(account);
        when(account.getCountry()).thenReturn("CANADA");
        when(countryService.getByName("CANADA")).thenReturn(canada);
        when(calculator.noOfItemsInCart(items)).thenReturn(3l);

        String result = cartController.get(item,request, model, principal);

        verify(canada).setDuty_rate(7.5);
    }

    @Test
    public void shouldReturnCartViewWhenDeleteIsCalled() {
        String returned = cartController.delete(item, request);
        assertEquals("redirect:/cart", returned);
    }

    @Test
    public void shouldRemoveTheItemFromShoppingCartWhenWeDeletedThatItem(){
        HashMap<Item,Long> items = new HashMap<>();
        items.put(item,1l);
        when(itemService.get(item.getItemId())).thenReturn(item);
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(items);

        cartController.delete(item,request);

        assertFalse(items.containsKey(item));
        verify(itemService).get(eq(item.getItemId()));
    }

}