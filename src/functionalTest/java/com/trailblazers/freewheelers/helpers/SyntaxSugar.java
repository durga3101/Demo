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

    public static final String EMPTY_PASSWORD = "";
    public static final String NO_QUANTITY = "";
    public static final long ONLY_ONE_LEFT = 1L;
    public static final long ONLY_TWO_LEFT = 2L;
    public static final String ADDED_ITEM = "addedItemName";
    public static final String HAS_ITEM_BEEN_ADDED = "hasItemBeenAdded";
    public static String SIMPLON_FRAME = "Simplon Pavo 3 Ultra " + System.currentTimeMillis();
    public static String SPOKE_REFLECTORS = "Spoke - Reflectors Arrow red " + System.currentTimeMillis();
    public static final String ADD_TO_CART_SUCCESS = "Awesome, you added " + SIMPLON_FRAME + " to your cart! You can keep shopping, or view my cart.";
    public static final String ADD_TO_CART_FAILURE = "Sorry, that item is temporarily out of stock. Please check back later!";
    public static final String ADD_TO_CART_SUCCESS_CLASS = "add-to-cart-success";
    public static final String ADD_TO_CART_FAILURE_CLASS = "add-to-cart-failure";
    public static final String EMPTY_CART = "Oops, looks like your cart is empty. Add items";
    public static final String REALLY_EXPENSIVE = "2899.00";
    public static final String SOME_DESCRIPTION = "4 x red, curved Arrow shape, screw fastening";
    public static final String A_LOT = "1000";
    public static String CHROME_FRAME = "Chrome Pavo 5 Ultra ";
    public static String PASSWORD_ERROR = "Must enter a password!";
    public static String PASSWORD_FIELD = "confirmPassword_field";
    public static String RAJU = "Raju";
    public static String ACCOUNT_CREATION_SUCCESS = "account has been created";
    public static String VISA = "Visa";
    public static String CCV = "534";
    public static String EXP_MONTH = "11";
    public static String EXP_YEAR = "2020";
    public static String INVALID_CARD_NO = "abc";
    public static String VALID_CARD_NO = "4111111111111111";
    public static String REVOKED_CARD_NO = "4111111111111116";

    public static String ADDRESS_1 = "An Address";
    public static String ADDRESS_2 = "More of address";
    public static String CITY = "A city";
    public static String STATE = "A state";
    public static String POSTAL_CODE = "12345";
    public static String FIELD_WITH_MORETHAN_255_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String ENTER_CARD_NO = "Must enter a card number.";
    public static String CARD_NO_FIELD = "card_number_field";

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