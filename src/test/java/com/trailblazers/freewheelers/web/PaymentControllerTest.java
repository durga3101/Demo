package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PaymentControllerTest {
    private HttpSession httpSession;
    private HttpServletRequest request;
    @Test
    public void shouldPutCorrectTotalAsAttribute() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        Item item = new Item();
        item.setPrice(BigDecimal.valueOf(10));

        PaymentController paymentController = new PaymentController();

        paymentController.payForItem(model, principal, item);

        verify(model).addAttribute("totalAmount", "10");
    }
    @Test
    public void addToCartShouldReturnLoginWhenPrincipalIsNull() throws Exception {
        request = mock(HttpServletRequest.class);
        httpSession =mock(HttpSession.class);
        when(request.getSession()).thenReturn(httpSession);
        Model model = mock(Model.class);
        assertEquals("payment", new PaymentController().get(model,request));

    }

}