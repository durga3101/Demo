package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.model.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Component
public class Calculator {
    public BigDecimal getSubtotalFromCart(HashMap<Item, Long> cart) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Item, Long> entry : cart.entrySet()) {
            BigDecimal itemPrice = entry.getKey().getPrice();

            for(int quantity = 0; quantity < entry.getValue(); quantity++){
                totalPrice = totalPrice.add(itemPrice);
            }

        }
        totalPrice = totalPrice.setScale(2, RoundingMode.CEILING);
        return totalPrice;
    }

    public BigDecimal calculateVat(BigDecimal subTotal, Country country) {
        BigDecimal vat_rate = new BigDecimal(country.getVat_rate()/100);
        BigDecimal vat = subTotal.multiply(vat_rate);
        return vat.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal calculateDuty(BigDecimal subtotal, Country country) {
        BigDecimal duty_rate = new BigDecimal(country.getDuty_rate() / 100);
        BigDecimal duty = subtotal.multiply(duty_rate);
        return duty.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public long noOfItemsInCart(HashMap<Item, Long> items) {
        long noOfItemsInCart=0;
        for (int index=0;index<items.size();index++) {
            noOfItemsInCart+=items.get(index);
        }
        return noOfItemsInCart;
    }
}
