package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CartControllerTest {
    private Model model;
    private Principal principal;
    private HttpServletRequest request;
    private CartController cartController;
    private HttpSession httpSession;
    private ItemService itemService;
    private Item item;
    private String actual;
    private String expected;

    @Before
    public void setUp() throws Exception {
        model = mock(Model.class);
        principal = mock(Principal.class);
        request = mock(HttpServletRequest.class);
        itemService = mock(ItemServiceImpl.class);
        cartController = new CartController(itemService);
        httpSession = mock(HttpSession.class);
        item = mock(Item.class);
        when(request.getSession()).thenReturn(httpSession);
    }

    @Test
    public void getShouldReturnCartJSPWhenPrincipalIsNotNull() {
        when(httpSession.getAttribute("itemForReserve")).thenReturn(item);

        actual = cartController.get(request, model, principal);
        expected = "cart";

        assertEquals(expected, actual);
    }

    @Test
    public void getShouldRedirectToLoginWhenPrincipalIsNull() throws Exception {
        actual = cartController.get(request, model, null);
        expected = "redirect:/login";

        assertEquals(expected, actual);
    }

    @Test
    public void postShouldRedirectToLoginWhenPrincipalIsNull() throws Exception {
        actual = cartController.post(request, model, null, item);
        expected = "redirect:/login";

        assertEquals(expected, actual);
    }

    @Test
    public void postShouldAddTheItemToModelWhenUserAddsItemToTheCart() {
        when(itemService.get(anyLong())).thenReturn(item);
        cartController.post(request, model, principal, item);
        verify(model, times(1)).addAttribute("item", item);
    }
}