package com.trailblazers.freewheelers;

import org.junit.Test;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class MultipleItemCartTest extends UserJourneyBase {



    @Test
    public void shouldRedirectToLoginWhenAddingToCartIfNotLoggedIn() throws Exception {
        admin
                .there_is_a_frame(SIMPLON_FRAME, ONLY_ONE_LEFT);

        user
                .visits_home_page()
                .add_item_to_cart(SIMPLON_FRAME);
        screen
                .shows_login();
    }


    @Test
    public void shouldSeeMultipleItemsInCartWhenVisitingCartPage() throws Exception {

        admin
                .there_is_a_user(RAJU, SOME_PASSWORD)
                .there_is_a_frame(SIMPLON_FRAME, ONLY_ONE_LEFT)
                .there_is_a_frame(CHROME_FRAME, ONLY_TWO_LEFT);

        user
                .logs_in_with(RAJU, SOME_PASSWORD)
                .visits_home_page()
                .add_item_to_cart(SIMPLON_FRAME)
                .add_item_to_cart(CHROME_FRAME)
                .visits_cart_page();

        screen
                .shows_cart_page()
                .should_list_item(SIMPLON_FRAME)
                .should_list_item(CHROME_FRAME);


    }
}
