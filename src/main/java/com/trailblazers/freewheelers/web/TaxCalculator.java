package com.trailblazers.freewheelers.web;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TaxCalculator {

    public BigDecimal calculateVat(BigDecimal subTotal) {
        return new BigDecimal(10);
    }
}
