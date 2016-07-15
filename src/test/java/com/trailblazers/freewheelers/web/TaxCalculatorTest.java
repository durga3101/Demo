package com.trailblazers.freewheelers.web;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxCalculatorTest {

    private TaxCalculator taxCalculator;

    @Before
    public void setUp() throws Exception {
        taxCalculator = new TaxCalculator();
    }

    @Test
    public void shouldReturnVatAs10IfAmountIs50() {
        BigDecimal expected = new BigDecimal(10);
        assertEquals(expected,taxCalculator.calculateVat(new BigDecimal(50)));
    }
    @Test
    public void shouldReturnVatAs10IfAmountIs5() {
        BigDecimal expected = new BigDecimal(10);
        assertEquals(expected,taxCalculator.calculateVat(new BigDecimal(50) ));
    }
}