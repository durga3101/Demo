package com.trailblazers.freewheelers;

import org.junit.Ignore;
import org.junit.Test;

import static com.trailblazers.freewheelers.UserJourneyBase.screen;
import static com.trailblazers.freewheelers.UserJourneyBase.user;

public class ShippingAddressTest {
    String jan = "Jan Plewka";
    String password = "Password@1";

    @Ignore
    @Test
    public void checkTheShippingAddress() {
        user
                .logs_in_with(jan,password)
                .visits_home_page()
                .reservesAnItem()
                .checksOutItem();
        screen
            .shouldDisplayShippingAddress();
    }
}
