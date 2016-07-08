package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import org.junit.Test;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.security.Principal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PaymentControllerTest {
    
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

}