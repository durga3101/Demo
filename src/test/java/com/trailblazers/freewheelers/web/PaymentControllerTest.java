package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.utilities.Calculator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;

import static com.trailblazers.freewheelers.web.Session.SHOPPING_CART;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PaymentControllerTest {
    private HttpSession httpSession;
    private HttpServletRequest request;
    private Model model;
    private PaymentController controller;
    private ItemService service;
    private String expected;
    private Calculator calculator;
    private Session session;


    @Before
    public void setUp() throws Exception {
        service = mock(ItemServiceImpl.class);
        calculator = mock(Calculator.class);
        session = mock(Session.class);
        controller = new PaymentController(service, calculator, session);
        request = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        model = mock(Model.class);
        when(request.getSession()).thenReturn(httpSession);

    }

    @Test
    public void shouldRedirectToHomeBeforeCreatingItemModelWhenNoItemInCart() throws Exception {
        when(httpSession.getAttribute(anyString())).thenReturn(null);

        String actual = controller.get(model,request);
        expected = "redirect:/";
        assertEquals(expected, actual);

    }

    @Test
    public void shouldReturnPaymentAfterCreatingItemModelWhenItemInCart() throws Exception {
        HashMap<Item, Long> cart = mock(HashMap.class);
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(cart);
        when(cart.isEmpty()).thenReturn(false);

        String actual = controller.get(model,request);

        expected = "payment";
        assertEquals(expected, actual);

    }

    @Test
    public void shouldAddSubtotalToModel() {
        HashMap<Item, Long> cart = mock(HashMap.class);
        when(cart.isEmpty()).thenReturn(false);
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(cart);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(httpSession);
        String grandTotal = "200.00";
        when(httpSession.getAttribute(anyString())).thenReturn(grandTotal);

        controller.get(model, request);

        verify(model).addAttribute("grandTotal", grandTotal);
    }

}