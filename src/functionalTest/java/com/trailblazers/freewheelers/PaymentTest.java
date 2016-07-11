package com.trailblazers.freewheelers;

import org.junit.Test;

public class PaymentTest extends UserJourneyBase{

    @Test
    public void shouldShowInvalidFieldErrorsWhenInvalidField() {
        user
                .opens_payment_page()
                .submits_payment_details();
        screen
                .should_see_error_in_card_form("Must enter a card number.")
                .should_see_error_in_card_form("Must enter a valid CCV.")
                .should_see_error_in_card_form("Must enter an expiration date.")
                .should_see_error_in_card_form("Must select card type.");
    }

}
