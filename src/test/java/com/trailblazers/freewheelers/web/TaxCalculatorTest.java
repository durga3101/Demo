package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Country;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxCalculatorTest {

    private TaxCalculator taxCalculator;
    private Country country;

    @Before
    public void setUp() throws Exception {
        taxCalculator = new TaxCalculator();
        country = new Country();
    }

    @Test
    public void shouldReturnVatAs10IfAmountIs50ForUK() {
        BigDecimal expected = new BigDecimal(10);
        country.setCountry_name("UK");
        country.setVat_rate(20);

        BigDecimal actual = taxCalculator.calculateVat(new BigDecimal(50), country);

        assertEquals(expected.intValue(),actual.intValue());
    }
    @Test
    public void shouldReturnVatAs0ForUSAForAnyAmount() {
        BigDecimal expected = new BigDecimal(0);
        country.setCountry_name("UK");
        country.setVat_rate(0);

        BigDecimal actual = taxCalculator.calculateVat(new BigDecimal(50), country);

        assertEquals(expected.intValue(),actual.intValue());
    }
}