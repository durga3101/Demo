package com.trailblazers.freewheelers;

import org.junit.Ignore;
import org.junit.Test;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.ONLY_ONE_LEFT;
import static com.trailblazers.freewheelers.helpers.SyntaxSugar.SOME_PASSWORD;

public class OrderTest extends UserJourneyBase {

    @Test
    public void loggedInUserViewsShoppingCartWithItem() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra " + System.currentTimeMillis();

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Bob, SOME_PASSWORD)
                .visits_home_page();

        screen
                .should_list_item(Simplon_Frame);

        user
                .add_item_to_cart(Simplon_Frame);

        screen
                .shows_cart_page()
                .should_show_cart_item(Simplon_Frame);
    }

    @Ignore
    public void shouldGoToHomePageAndWhenUserCancelsOrder(){
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra " + System.currentTimeMillis();

        admin
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);


    }

    @Test
    public void loggedInUserCancelsOrderThenGoesToHomePage() throws Exception {
        String Raju = "Raju User";
        String Simplon_Frame = "Simplon Pavo 3 Ultra " + System.currentTimeMillis();


        admin
                .there_is_a_user(Raju, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Raju, SOME_PASSWORD)
                .visits_home_page()
                .add_item_to_cart(Simplon_Frame);

        screen
                .shows_cart_page();

        user
                .click_cancel_button();

        screen
                .shows_home_page();

    }

    @Test
    public void loggedOutUserShouldLoginToReserveAnItem() throws Exception {
        String Raju = "Raju User";
        String Simplon_Frame = "Simplon Pavo 3 Ultra " + System.currentTimeMillis();

        admin
                .there_is_a_user(Raju, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .visits_home_page()
                .add_item_to_cart(Simplon_Frame);

        screen
                .shows_login();

        user
                .logs_in_with(Raju, SOME_PASSWORD);

        screen
                .shows_cart_page();

    }

    @Ignore
    public void testOrderProcess() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";
        String Simplon_Frame = "Simplon Pavo 3 Ultra " + System.currentTimeMillis();

        admin
                .there_is_an_admin(Arno, SOME_PASSWORD)
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_a_frame(Simplon_Frame, ONLY_ONE_LEFT);

        user
                .logs_in_with(Bob, SOME_PASSWORD)
                .visits_home_page();

        screen
                .should_list_item(Simplon_Frame);

        user
                .add_to_cart_and_check_out(Simplon_Frame)
                .visits_home_page();

        screen
                .should_not_list_item(Simplon_Frame);

        user
                .logs_in_with(Arno, SOME_PASSWORD)
                .visits_admin_profile();

        screen
                .there_should_be_an_order(Simplon_Frame, "NEW");

        user
                .changes_order_status(Simplon_Frame, "IN_PROGRESS");

        screen
                .there_should_be_an_order(Simplon_Frame, "IN_PROGRESS");
    }

}
