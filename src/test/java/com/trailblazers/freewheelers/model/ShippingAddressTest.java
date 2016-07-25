package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ShippingAddressTest {

    private ShippingAddress shippingAddress;

    @Before
    public void setUp() throws Exception {
        shippingAddress = new ShippingAddress(1L, "abc", "def", "pune", "Maharastra", "4111069","UK");
    }

    @Test
    public void checkingStreet1Details() throws Exception {
        shippingAddress.setStreet_1("street1");
        assertThat(shippingAddress.getStreet_1(), is("street1"));
    }

    @Test
    public void checkingStrret2Details() throws Exception {
        shippingAddress.setStreet_2("street2");
        assertThat(shippingAddress.getStreet_2(), is("street2"));
    }

    @Test
    public void checkingCityDetails() throws Exception {
        shippingAddress.setCity("city");
        assertThat(shippingAddress.getCity(), is("city"));
    }

    @Test
    public void checkingStateDetails() throws Exception {
        shippingAddress.setState("state");
        assertThat(shippingAddress.getState(), is("state"));
    }

    @Test
    public void checkingPostcodeDetails() throws Exception {
        shippingAddress.setPostcode("123456");
        assertThat(shippingAddress.getPostcode(), is("123456"));
    }

    @Test
    public void checkingCountryDetails() throws Exception {
        shippingAddress.setCountry("country");
        assertThat(shippingAddress.getCountry(), is("country"));
    }
}