package com.trailblazers.freewheelers.model;

import org.junit.Test;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CountryTest {

    @Test
    public void shouldReturnTrueIfCountriesAreSame() {
        Country first = new Country();
        Country second = new Country();
        assert(first.equals(second));
    }

    @Test
    public void shouldReturnCorrectCountryString() {
        Country country = new Country();
        country.setCountry_id(1L);
        country.setCountry_name("country");
        country.setVat_rate(10);
        country.setDuty_rate(20);
        String expectedString = "Country{country_id=1, country_name='country', vat_rate=10.0, duty_rate=20.0}";
        assertEquals(expectedString, country.toString());
    }


    @Test
    public void shouldNotBeEqualWhenCountryHasDifferentVat() {
        Country firstCountry = new Country();
        Country secondCountry = new Country();

        firstCountry.setVat_rate(10);
        secondCountry.setVat_rate(20);

        assertNotEquals(firstCountry, secondCountry);
    }
}