package com.trailblazers.freewheelers;

import org.junit.Ignore;
import org.junit.Test;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class OrderTest extends UserJourneyBase {

    @Test
    public void loggedInUserViewsShoppingCartWithItem() throws Exception {
        String Arno = "Arno Admin";
        String Bob = "Bob Buyer";

        admin
                .there_is_no_account_for(Bob)
                .there_is_a_user(Bob, SOME_PASSWORD)
                .there_is_no_item(SIMPLON_FRAME)
                .there_is_a_frame(SIMPLON_FRAME, ONLY_ONE_LEFT);

        user
                .clearCookies()
                .logs_in_with(Bob, SOME_PASSWORD)
                .visits_cart_page();

        screen
                .showsMessageInClass(EMPTY_CART, "empty-cart");

        user
                .visits_home_page();

        screen
                .should_list_item(SIMPLON_FRAME)
                .shouldNotShowAddToCartMessages();

        user
                .add_item_to_cart(SIMPLON_FRAME);

        screen
                .showsMessageInClass(ADD_TO_CART_SUCCESS, ADD_TO_CART_SUCCESS_CLASS);

        user
                .add_item_to_cart(SIMPLON_FRAME);

        screen
                .should_list_item(SIMPLON_FRAME)
                .showsMessageInClass(ADD_TO_CART_FAILURE, ADD_TO_CART_FAILURE_CLASS);

        user
                .visits_cart_page();

        screen
                .shows_cart_page()
                .should_list_item(SIMPLON_FRAME);
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
                .add_item_to_cart(Simplon_Frame)
                .visits_cart_page();

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
        admin
                .there_is_a_user(Raju, SOME_PASSWORD)
                .there_is_a_frame(SIMPLON_FRAME, ONLY_ONE_LEFT);

        user
                .visits_home_page()
                .add_item_to_cart(SIMPLON_FRAME);

        screen
                .shows_login();

        user
                .logs_in_with(Raju, SOME_PASSWORD);

        screen
                .shows_home_page();

    }

    //        TODO: Fix this test to reflect new flow - add the payment step
    @Ignore
    @Test
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

    @Test
    @Ignore
    public void shouldShowTaxesOnCart(){
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
                .show_tax_on_cart_page("9.998");

    }
}
