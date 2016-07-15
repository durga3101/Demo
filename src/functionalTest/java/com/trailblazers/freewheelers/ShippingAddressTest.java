package com.trailblazers.freewheelers;

import org.junit.Test;

public class ShippingAddressTest extends UserJourneyBase{
    String jan = "Jan Plewka";
    String password = "Password@1";

    @Test
    public void checkTheShippingAddress() {
        user
                .logs_in_with(jan,password)
                .visits_home_page()
                .reservesAnItem()
                .checksOutItem();
        screen
            .shouldDisplayShippingAddressPage();
    }
}
