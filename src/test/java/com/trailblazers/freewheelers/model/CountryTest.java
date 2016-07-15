package com.trailblazers.freewheelers.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CountryTest {

    public static final String COUNTRY_NAME = "UK";

    @Test
    public void shouldReturnTrueIfCountriesAreSame() {
        Country first = new Country();
        Country second = new Country();
        assert(first.equals(second));
    }


}