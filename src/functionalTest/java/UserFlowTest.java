import com.trailblazers.freewheelers.UserJourneyBase;
import org.junit.Test;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class UserFlowTest extends UserJourneyBase{

    @Test
    public void userFlowTest() throws Exception {

        admin
                .there_is_no_account_for(RAJU)
                .there_is_no_item(SIMPLON_FRAME)
                .there_is_no_item(CHROME_FRAME)
                .there_is_a_frame(SIMPLON_FRAME, ONLY_ONE_LEFT)
                .there_is_a_frame(CHROME_FRAME, ONLY_TWO_LEFT);

        user
                .clearCookies()
                .creates_an_account(RAJU, SOME_EMAIL, SOME_PASSWORD, EMPTY_PASSWORD, SOME_PHONE_NUMBER, SOME_COUNTRY);

        screen
                .shows_error(PASSWORD_ERROR, PASSWORD_FIELD);

        user
                .creates_an_account(RAJU, SOME_EMAIL, SOME_PASSWORD, SOME_PASSWORD, SOME_PHONE_NUMBER, SOME_COUNTRY);

        screen
                .shows_message(ACCOUNT_CREATION_SUCCESS);

        user
                .visits_home_page()
                .add_item_to_cart(SIMPLON_FRAME);

        screen
                .shows_login();

        user
                .logs_in_with(RAJU, SOME_PASSWORD);
        screen
                .should_list_item(SIMPLON_FRAME);

        user
                .visits_his_profile();
        screen
                .shows_profile_for(RAJU);

        user
                .visits_admin_profile();

        screen
                .should_show_access_denied();

        user
                .visits_home_page()
                .add_item_to_cart(CHROME_FRAME)
                .visits_cart_page();

        screen
                .should_list_item(SIMPLON_FRAME)
                .should_list_item(CHROME_FRAME);

        user
                .click_checkout_button();
        user
                .entersShippingAddressDetails(ADDRESS_1, ADDRESS_2, CITY, STATE, POSTAL_CODE);
        user
                .click_proceed_to_payment_button();
        screen
                .showsMessageInClass("120.00", "summary");
        user
                .entersPaymentDetails(VISA, INVALID_CARD_NO, CCV, EXP_MONTH, EXP_YEAR)
                .click_payment_button();

        screen
                .shows_error(ENTER_CARD_NO, CARD_NO_FIELD);

        user
                .entersPaymentDetails(VISA, REVOKED_CARD_NO, CCV, EXP_MONTH, EXP_YEAR)
                .click_payment_button();

        screen
                .shouldSeePaymentFailure();

        user
                .clicks_back_to_checkout_button()
                .entersPaymentDetails(VISA, VALID_CARD_NO, CCV, EXP_MONTH, EXP_YEAR)
                .click_cancel_button();
        screen
                .shows_home_page();
//REMOVING PAYMENT STEP OUT UNTIL SURVEY IS FIXED 
        user
                .visits_cart_page();
        user
                .click_checkout_button()
                .entersShippingAddressDetails(ADDRESS_1, ADDRESS_2, CITY, STATE, POSTAL_CODE)
                .click_proceed_to_payment_button()
                .entersPaymentDetails(VISA, VALID_CARD_NO, CCV, EXP_MONTH, EXP_YEAR)
                .click_payment_button();

        screen
                .shouldSeePaymentSuccess();

        // TODO: Add tests for survey

    }
}
