package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculatorTest {

    private Calculator calculator;
    private Country country;

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
        country = new Country();
    }

    @Test
    public void shouldReturnVatAs10IfAmountIs50ForUK() {
        BigDecimal expected = new BigDecimal(10);
        country.setCountry_name("UK");
        country.setVat_rate(20);

        BigDecimal actual = calculator.calculateVat(new BigDecimal(50), country);

        assertEquals(expected.intValue(),actual.intValue());
    }
    @Test
    public void shouldReturnVatAs0ForUSAForAnyAmount() {
        BigDecimal expected = new BigDecimal(0);
        country.setCountry_name("UK");
        country.setVat_rate(0);

        BigDecimal actual = calculator.calculateVat(new BigDecimal(50), country);

        assertEquals(expected.intValue(),actual.intValue());
    }

    @Test
    public void shouldComputeCorrectTotalPriceFromShoppingCart() {
        Item item1 = mock(Item.class);
        Item item2 = mock(Item.class);
        when(item1.getPrice()).thenReturn(new BigDecimal(10.00));
        when(item2.getPrice()).thenReturn(new BigDecimal(20.00));
        HashMap<Item, Long> cartMap = new HashMap<>();
        cartMap.put(item1, new Long(2));
        cartMap.put(item2, new Long(3));

        assertEquals(new BigDecimal("80.00"), calculator.getSubtotalFromCart(cartMap));

    }



    @Test
    public void shouldReturnDutyOf54IfInputIs1000WhenCountryNameIsUSA(){
        BigDecimal expected = new BigDecimal(54.00);
        country.setCountry_name("USA");
        country.setDuty_rate(5.4);

        BigDecimal actual = calculator.calculateDuty(new BigDecimal(1000),country);

        assertEquals(expected.intValue(),actual.intValue());
    }

    @Test
    public void shouldReturnDutyOf54IfInputIs1000WhenCountryNameIsCanada() {
        BigDecimal expected = new BigDecimal(9.00);
        country.setCountry_name("CANADA");
        country.setDuty_rate(9);

        BigDecimal actual = calculator.calculateDuty(new BigDecimal(100), country);

        assertEquals(expected.intValue(), actual.intValue());
    }
}