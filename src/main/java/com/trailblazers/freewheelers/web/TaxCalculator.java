package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Country;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TaxCalculator {

    public BigDecimal calculateVat(BigDecimal subTotal, Country country) {
        BigDecimal vat_rate = new BigDecimal(country.getVat_rate()/100);
        BigDecimal vat = subTotal.multiply(vat_rate);
        return vat.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}
