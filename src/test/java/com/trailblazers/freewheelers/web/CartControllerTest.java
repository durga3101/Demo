package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CartControllerTest {

    @Test
    public void shouldReturnCartStringWhenGetIsCalled() {
        CartController cartController = new CartController();

        assertEquals("cart", cartController.get());
    }

    @Test
    public void shouldAddItemToModelWhenUserAddsToCart() {
        Model model = new ExtendedModelMap();
        Principal principal = mock(Principal.class);
        Item item = mock(Item.class);
//        Long itemId = new Long("10");
//        item.setItemId(itemId);
        CartController cartController = new CartController();

        cartController.addToCart(model, principal, item);

        assert(model.containsAttribute("item"));
    }
}