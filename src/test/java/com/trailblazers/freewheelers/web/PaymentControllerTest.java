package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.HashMap;

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


    @Before
    public void setUp() throws Exception {
        service = mock(ItemServiceImpl.class);
        controller = new PaymentController(service);
        request = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        model = mock(Model.class);
        when(request.getSession()).thenReturn(httpSession);
        expected = "payment";

    }

    @Test
    public void shouldRedirectToPaymentBeforeCreatingItemModelWhenNoItemInCart() throws Exception {
        when(httpSession.getAttribute(anyString())).thenReturn(null);

        String actual = controller.get(model,request);

        assertEquals(expected, actual);

    }

    @Test
    public void shouldRedirectToPaymentAfterCreatingItemModelWhenItemInCart() throws Exception {
        Item item = mock(Item.class);
        when(httpSession.getAttribute(anyString())).thenReturn(item);
        when(service.get(anyLong())).thenReturn(item);

        String actual = controller.get(model,request);

        assertEquals(expected, actual);

    }

    //Will be moved to calculator test class when merge conflicts resolved
    @Test
    public void shouldComputeCorrectTotalPriceFromShoppingCart() {
        Item item1 = mock(Item.class);
        Item item2 = mock(Item.class);
        when(item1.getPrice()).thenReturn(new BigDecimal(10.00));
        when(item2.getPrice()).thenReturn(new BigDecimal(20.00));
        HashMap<Item, Long> cartMap = new HashMap<>();
        cartMap.put(item1, new Long(2));
        cartMap.put(item2, new Long(3));

        when(service.getItemHashMap(any(HashMap.class))).thenReturn(cartMap);

        assertEquals(new BigDecimal("80.00"), controller.getTotalPriceFromCart(request));

    }

    @Test
    public void shouldAddTotalPriceToModel() {
        Item item = mock(Item.class);
        when(httpSession.getAttribute(anyString())).thenReturn(item);
        when(service.get(anyLong())).thenReturn(item);

        Item item1 = mock(Item.class);
        Item item2 = mock(Item.class);
        when(item1.getPrice()).thenReturn(new BigDecimal(10.00));
        when(item2.getPrice()).thenReturn(new BigDecimal(20.00));
        HashMap<Item, Long> cartMap = new HashMap<>();
        cartMap.put(item1, new Long(2));
        cartMap.put(item2, new Long(3));

        when(service.getItemHashMap(any(HashMap.class))).thenReturn(cartMap);

        controller.get(model, request);
//        verify(model).addAttribute("totalAmount", totalPrice);
    }

}