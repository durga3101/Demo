import com.trailblazers.freewheelers.FeatureToggels;
import com.trailblazers.freewheelers.UserJourneyBase;
import org.junit.Test;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class UserFlowTest extends UserJourneyBase {

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
                .logs_in_with(RAJU, SOME_PASSWORD);

        screen
                .shows_error_alert(UNSUCCESSFUL_LOGIN);

        user
                .creates_an_account(RAJU,
                                    SOME_EMAIL,
                                    SOME_PASSWORD,
                                    EMPTY_PASSWORD,
                                    EMPTY_PHONE_NUMBER,
                                    UNSELECTED_COUNTRY
                );

        screen
                .shows_error(PASSWORD_ERROR, PASSWORD_FIELD)
                .shows_error(PHONE_ERROR, PHONE_FIELD)
                .shows_error(COUNTRY_ERROR, COUNTRY_FIELD);

        user
                .creates_an_account(RAJU,
                                    SOME_EMAIL,
                                    SOME_PASSWORD,
                                    SOME_PASSWORD,
                                    SOME_PHONE_NUMBER,
                                    SOME_COUNTRY
                );

        screen
                .shows_message(ACCOUNT_CREATION_SUCCESS);

        user
                .is_logged_out()
                .creates_an_account(DURGA,
                                    SOME_EMAIL,
                                    SOME_PASSWORD,
                                    SOME_PASSWORD,
                                    SOME_PHONE_NUMBER,
                                    SOME_COUNTRY
                );

        screen
                .shows_message(ACCOUNT_CREATION_ERROR);

        user
                .visits_home_page();

        screen
                .shouldNotShowAddToCartMessages();

        user
                .visits_cart_page();

        screen
                .shows_login();

        user
                .logs_in_with(RAJU, SOME_PASSWORD);

        screen
                .showsMessageInClass(EMPTY_CART, EMPTY_CART_CLASS);

        user
                .is_logged_out()
                .add_item_to_cart(SIMPLON_FRAME);

        screen
                .shows_login();

        user
                .logs_in_with(RAJU, SOME_PASSWORD);

        screen
                .should_list_item(SIMPLON_FRAME)
                .showsMessageInClass(ADD_TO_CART_SUCCESS, ADD_TO_CART_SUCCESS_CLASS);

        user
                .visits_his_profile();

        screen
                .shows_profile_for(RAJU)
                .shows_profile_for_country(SOME_COUNTRY);

        user
                .visits_admin_profile();

        screen
                .should_show_access_denied();

        user
                .visits_home_page()
                .add_item_to_cart(CHROME_FRAME)
                .add_item_to_cart(SIMPLON_FRAME);

        screen
                .showsMessageInClass(ADD_TO_CART_FAILURE, ADD_TO_CART_FAILURE_CLASS);

        user
                .visits_cart_page();

        screen
                .should_list_item(SIMPLON_FRAME)
                .should_list_item(CHROME_FRAME);

        user
                .click_checkout_button();

        user
                .click_proceed_to_payment_button();

        screen
                .shows_error(EMPTY_STREET_ERR, STREET_1_FIELD)
                .shows_error(EMPTY_CITY_ERR, CITY_FIELD)
                .shows_error(EMPTY_STATE_ERR, STATE_FIELD)
                .shows_error(EMPTY_PC_ERR, PC_FIELD);

        user
                .entersShippingAddressDetails(  FIELD_WITH_MORETHAN_255_CHARACTERS,
                                                FIELD_WITH_MORETHAN_255_CHARACTERS,
                                                FIELD_WITH_MORETHAN_255_CHARACTERS,
                                                FIELD_WITH_MORETHAN_255_CHARACTERS,
                                                FIELD_WITH_MORETHAN_255_CHARACTERS
                )
                .click_proceed_to_payment_button();

        screen
                .shows_error(INVALID_STREET_ERR, STREET_1_FIELD)
                .shows_error(INVALID_STREET_ERR, STREET_2_FIELD)
                .shows_error(INVALID_CITY_ERR, CITY_FIELD)
                .shows_error(INVALID_STATE_ERR, STATE_FIELD)
                .shows_error(INVALID_PC_ERR, PC_FIELD);

        user
                .entersShippingAddressDetails(  ADDRESS_1,
                                                ADDRESS_2,
                                                CITY,
                                                STATE,
                                                POSTAL_CODE
                )
                .click_proceed_to_payment_button();

        screen
                .showsMessageInClass(BIG_DEC_120, SUMMARY_CLASS);

        user
                .entersPaymentDetails(  VISA,
                                        INVALID_CARD_NO,
                                        CCV,
                                        EXP_MONTH,
                                        EXP_YEAR
                )
                .click_payment_button();

        screen
                .shows_error(ENTER_CARD_NO, CARD_NO_FIELD);

        user
                .entersPaymentDetails(  VISA,
                                        REVOKED_CARD_NO,
                                        CCV,
                                        EXP_MONTH,
                                        EXP_YEAR
                )
                .click_payment_button();

        screen
                .shouldSeePaymentFailure();

        user
                .clicks_back_to_checkout_button()
                .entersPaymentDetails(  VISA,
                                        VALID_CARD_NO,
                                        CCV,
                                        EXP_MONTH,
                                        EXP_YEAR
                )
                .click_payment_button();

        screen
                .shouldSeePaymentSuccess()
                .shouldSeeSurvey();

//        user
//                .click_submit_button();
//
//        screen
//                .shouldSeeConfirmation();
//
//        user
//                .click_cancel_button();
//
//        screen
//                .shouldSeePaymentSuccess();
        
        if(FeatureToggels.DISPLAY_ADDRESS_ON_USER_PROFILE) {
            user
                    .visits_his_profile();
            screen
                    .shows_shipping_address(ADDRESS_1,
                            ADDRESS_2,
                            CITY,
                            STATE,
                            POSTAL_CODE);
        }
    }
}
