package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartControllerTest {
    private Model model;
    private Principal principal;
    private HttpServletRequest request;
    private CartController cartController;
    private HttpSession httpSession;

    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
        principal = mock(Principal.class);
        request = mock(HttpServletRequest.class);
        cartController = new CartController();
        httpSession = mock(HttpSession.class);


    }

    @Test
    public void shouldReturnCartStringWhenGetIsCalled() {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("itemForReserve")).thenReturn(mock(Item.class));
        assertEquals("cart", cartController.get(request,model,principal));
    }

    @Test
    public void getShouldReturnLoginWhenPrincipalIsNull() throws Exception {
        when(request.getSession()).thenReturn(httpSession);
        assertEquals("redirect:/login", cartController.get(request,model,null));

    } @Test
    public void addToCartShouldReturnLoginWhenPrincipalIsNull() throws Exception {
        Item item = mock(Item.class);
        when(request.getSession()).thenReturn(httpSession);
        assertEquals("redirect:/login", cartController.addToCart(request,model,null,item));

    }

    @Test
    public void shouldAddItemToModelWhenUserAddsToCart() {
        Item item = mock(Item.class);
        when(request.getSession()).thenReturn(httpSession);
        cartController.addToCart(request, model, principal, item);
        assert (model.containsAttribute("item"));
    }
}