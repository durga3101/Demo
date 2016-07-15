package com.trailblazers.freewheelers;

import org.junit.Ignore;
import org.junit.Test;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

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

    // IGNORED UNTIL GATEWAY SERVER IS BACK UP
    @Ignore
    @Test
    public void shouldShowReservePageWhenUsingValidCardDetails() throws Exception {

        String jan = "Jan Plewka";
        String type = "Visa";
        String card_no = "4111111111111111";
        String invalid_card_no = "123";
        String ccv = "534";

        String exp_month = "11";
        String exp_year = "2020";

        admin
                .there_is_no_item(SIMPLON_FRAME)
                .there_is_a_frame(SIMPLON_FRAME, ONLY_ONE_LEFT);

        user
                .logs_in_with(jan, SOME_PASSWORD)
                .visits_home_page()
                .reservesAnItem()
                .add_item_to_cart(SIMPLON_FRAME)
                .visits_cart_page();
        user
                .checksOutItem()
                .entersShippingAddressDetails("Street 1","Street 2","City","Uk","12345678")
                .entersPaymentDetails(type, card_no, ccv, exp_month, exp_year)
                .submits_payment_details();
        screen
                .shouldSeePaymentSuccess()
                .shouldDisplayPurchasedItem(SIMPLON_FRAME);
    }

    @Ignore
    @Test
    public void shouldShowReserveErrorPageWhenUsingInvalidCardDetails() throws Exception {

        String jan = "Jan Plewka";
        String type = "MasterCard";
        String card_no = "123";
        String ccv = "534";
        String exp_month = "11";
        String exp_year = "2020";

        user.checksOutItem()
                .entersPaymentDetails(type, card_no, ccv, exp_month, exp_year)
                .submits_payment_details();
        screen
                .shouldSeePaymentFailure();
    }

    @Ignore
    @Test
    public void shouldShowReserveErrorPageWhenCardIsRevoked() throws Exception {

        String jan = "Jan Plewka";
        String type = "Visa";
        String card_no = "4111111111111116";
        String ccv = "534";
        String exp_month = "11";
        String exp_year = "2020";

        user.checksOutItem()
                .entersPaymentDetails(type, card_no, ccv, exp_month, exp_year)
                .submits_payment_details();
        screen
                .shouldSeePaymentFailure();
    }

}
