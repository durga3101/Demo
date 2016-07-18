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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CartControllerTest {
<<<<<<< 3d58c66340391012ea6309bb43aa733ed226e4a2
    private static final String EMPTY_CART = "isCartEmpty";
    private static final String ITEMS = "items";
=======
    public static final String SOME_COUNTRY = "UK";
>>>>>>> [Prajakta/Shil] [#197] Add country service implementation
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
    private TaxCalculator taxCalculator;
<<<<<<< 3d58c66340391012ea6309bb43aa733ed226e4a2
=======
    private AccountService accountService;
    private CountryService countryService;
>>>>>>> [Prajakta/Shil] [#197] Add country service implementation

    @Before
    public void setUp() throws Exception {
        model = mock(Model.class);
        principal = mock(Principal.class);
        request = mock(HttpServletRequest.class);
        itemService = mock(ItemServiceImpl.class);
        taxCalculator = mock(TaxCalculator.class);
        accountService = mock(AccountService.class);
        countryService = mock(CountryService.class);
        cartController = new CartController(itemService,taxCalculator, accountService,countryService);
        httpSession = mock(HttpSession.class);
        item = mock(Item.class);
        when(request.getSession()).thenReturn(httpSession);
        emptyCart = new HashMap<>();
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

        verify(model).addAttribute(anyString(), anyMap());
    }


    @Test
    public void getShouldGetItemObjsFromServiceAndAddToModel() throws Exception {
        HashMap<Item, Long> fullCart = emptyCart;
        fullCart.put(item, 1L);

        when(itemService.getItemHashMap((HashMap<Long, Long>) anyMap())).thenReturn(fullCart);

        cartController.get(item, request, model, principal);

        verify(itemService).getItemHashMap((HashMap<Long, Long>) anyMap());
        verify(model).addAttribute(ITEMS, fullCart);
        verify(model).addAttribute(EMPTY_CART, false);
        verify(model, never()).addAttribute(EMPTY_CART, true);

    }

    @Test
    public void getShouldSetEmptyCartAttributeOnModelIfCartIsEmpty() throws Exception {
        when(itemService.getItemHashMap((HashMap<Long, Long>) anyMap())).thenReturn(emptyCart);

        cartController.get(item, request, model, principal);

        verify(model).addAttribute(EMPTY_CART, true);
        verify(model, never()).addAttribute(ITEMS, emptyCart);
    }

    @Test
    public void shouldCalculateTaxWhenGetIsCalled() throws Exception {
        BigDecimal subTotal = new BigDecimal(50);
        BigDecimal vat = new BigDecimal(10);
        Country country = new Country();
        country.setCountry_name(SOME_COUNTRY);
        Account account = new Account();
        account.setCountry(SOME_COUNTRY);

        when(httpSession.getAttribute("itemForReserve")).thenReturn(item);
        when(countryService.getByName(SOME_COUNTRY)).thenReturn(country);
        when(accountService.getAccountIdByName("ABC")).thenReturn(account);
        when(taxCalculator.calculateVat(subTotal,country)).thenReturn(vat);
        when(item.getPrice()).thenReturn(subTotal);

        when(principal.getName()).thenReturn("ABC");

        String result = cartController.get(item,request, model, principal);
        assertEquals("cart",result);
        verify(taxCalculator).calculateVat(subTotal, country);
        verify(accountService).getAccountIdByName("ABC");
        verify(countryService).getByName(SOME_COUNTRY);
        verify(model).addAttribute("vat",vat.toString());
    }
}