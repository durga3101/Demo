package com.trailblazers.freewheelers.web;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CartControllerTest {

    @Test
    public void shouldReturnCartStringWhenGetIsCalled() {
        CartController cartController = new CartController();

        assertEquals("shoppingCart", cartController.get());
    }

}