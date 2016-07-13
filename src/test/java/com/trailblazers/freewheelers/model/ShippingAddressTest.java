package com.trailblazers.freewheelers.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ShippingAddressTest {
    @Test
    public void checkingAddressDetails() throws Exception {
        ShippingAddress shippingAddress = new ShippingAddress("abc", "def", "pune", "Maharastra", "4111069");

            assertThat(shippingAddress.getStreet1(),is("abc"));
            assertThat(shippingAddress.getStreet2(), is("def"));
            assertThat(shippingAddress.getCity(), is("pune"));
            assertThat(shippingAddress.getState(), is("Maharastra"));
            assertThat(shippingAddress.getPostcose(), is("4111069"));
            assertThat(shippingAddress.getCountry(), is(""));
        }
}