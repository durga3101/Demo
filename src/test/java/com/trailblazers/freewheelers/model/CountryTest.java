package com.trailblazers.freewheelers.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CountryTest {

    public static final String COUNTRY_NAME = "UK";

    @Test
    public void shouldReturnTrueIfCountriesAreSame() {
        Country first = new Country(COUNTRY_NAME, 20, 0);
        Country second = new Country(COUNTRY_NAME, 20, 0);
        assertTrue(first.equals(second));
    }


}