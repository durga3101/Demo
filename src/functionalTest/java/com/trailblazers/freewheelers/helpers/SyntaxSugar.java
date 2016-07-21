package com.trailblazers.freewheelers.helpers;

import java.math.BigDecimal;

public class SyntaxSugar {

    public static final String SOME_PHONE_NUMBER = "555-123456";
    public static final String EMPTY_PHONE_NUMBER = "";
    public static final String EMPTY_STRING = "";

    public static final String SOME_PASSWORD = "Password@1";
    public static final String DIFFERENT_PASSWORD = "Password@2";
    public static final String INVAILD_PASSWORD = "password";
    public static final String SOME_EMAIL = "somebody@something.de";
    public static final String SOME_COUNTRY = "UK";
    public static final BigDecimal SOME_PRICE = new BigDecimal(50);
    public static final String UNSELECTED_COUNTRY = "Choose A Country";

    public static final String EMPTY_CART_CLASS = "empty-cart";

    public static final String PHONE_FIELD = "phoneNumber_field";
    public static final String COUNTRY_FIELD = "country_field";
    public static final String PHONE_ERROR = "Must enter valid phone number!";
    public static final String COUNTRY_ERROR = "Must select a country!";

    public static final String ACCOUNT_CREATION_ERROR = "An error has occurred while creating the account.";
    public static final String DURGA = "Durga";

    public static final String UNSUCCESSFUL_LOGIN = "login attempt was not successful";

    public static final String EMPTY_PASSWORD = "";
    public static final String NO_QUANTITY = "";
    public static final long ONLY_ONE_LEFT = 1L;
    public static final long ONLY_TWO_LEFT = 2L;
    public static final String ADDED_ITEM = "addedItemName";
    public static final String HAS_ITEM_BEEN_ADDED = "hasItemBeenAdded";
    public static final String SIMPLON_FRAME = "Simplon Pavo 3 Ultra " + System.currentTimeMillis();
    public static final String SPOKE_REFLECTORS = "Spoke - Reflectors Arrow red " + System.currentTimeMillis();
    public static final String ADD_TO_CART_SUCCESS = "Awesome, you added " + SIMPLON_FRAME + " to your cart! You can keep shopping, or view my cart.";
    public static final String ADD_TO_CART_FAILURE = "Sorry, that item is temporarily out of stock. Please check back later!";
    public static final String ADD_TO_CART_SUCCESS_CLASS = "add-to-cart-success";
    public static final String ADD_TO_CART_FAILURE_CLASS = "add-to-cart-failure";
    public static final String EMPTY_CART = "Oops, looks like your cart is empty. Add items";
    public static final String REALLY_EXPENSIVE = "2899.00";
    public static final String SOME_DESCRIPTION = "4 x red, curved Arrow shape, screw fastening";
    public static final String A_LOT = "1000";
    public static final String CHROME_FRAME = "Chrome Pavo 5 Ultra ";
    public static final String PASSWORD_ERROR = "Must enter a password!";
    public static final String PASSWORD_FIELD = "confirmPassword_field";
    public static final String RAJU = "Raju";
    public static final String ACCOUNT_CREATION_SUCCESS = "account has been created";
    public static final String VISA = "Visa";
    public static final String CCV = "534";
    public static final String EXP_MONTH = "11";
    public static final String EXP_YEAR = "2020";
    public static final String INVALID_CARD_NO = "abc";
    public static final String VALID_CARD_NO = "4111111111111111";
    public static final String REVOKED_CARD_NO = "4111111111111116";

    public static final String ADDRESS_1 = "An Address";
    public static final String ADDRESS_2 = "More of address";
    public static final String CITY = "A city";
    public static final String STATE = "A state";
    public static final String POSTAL_CODE = "12345";
    public static final String FIELD_WITH_MORETHAN_255_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String ENTER_CARD_NO = "Must enter a card number.";
    public static final String CARD_NO_FIELD = "card_number_field";

    public static final String EMPTY_STREET_ERR = "Must enter street details";
    public static final String EMPTY_CITY_ERR = "Must enter city";
    public static final String EMPTY_STATE_ERR = "Must enter state/province";
    public static final String EMPTY_PC_ERR = "Must enter postal code";

    public static final String INVALID_STREET_ERR = "Must enter valid street details";
    public static final String INVALID_CITY_ERR = "Must enter valid city";
    public static final String INVALID_STATE_ERR = "Must enter valid state/province";
    public static final String INVALID_PC_ERR = "Must enter valid postal code";

    public static final String STREET_1_FIELD = "street1_field";
    public static final String STREET_2_FIELD = "street2_field";
    public static final String CITY_FIELD = "city_field";
    public static final String STATE_FIELD = "state_field";
    public static final String PC_FIELD = "postcode_field";

    public static final String SUMMARY_CLASS = "summary";
    public static final String BIG_DEC_120 = "120.00";


    public static String emailFor(String userName) {
        return userName.replace(' ', '-') + "@random-email.com";
    }

    public static String from(String s) {
        return s;
    }

    public static String to(String s) {
        return s;
    }



}