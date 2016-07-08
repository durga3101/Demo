package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.trailblazers.freewheelers.model.AccountValidator.verifyInputs;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class AccountValidatorTest {

    public static final String SOME_EMAIL = "guenter.grass@gmail.com";
    public static final String SOME_PASSWORD = "V3ry Secure!";
    public static final String SOME_NAME = "Günter Grass";
    public static final String SOME_PHONE = "004945542741";
    public static final String SOME_COUNTRY = "India";
    private Account account;
    private AccountBuilder accountBuilder;

    @Before
    public void setup() {
        accountBuilder = new AccountBuilder()
                .setAccountName(SOME_NAME)
                .setAccountPassword(SOME_PASSWORD)
                .setAccountEmailAddress(SOME_EMAIL)
                .setAccountPhoneNumber(SOME_PHONE)
                .setAccountCountry(SOME_COUNTRY);
    }
    @Test
    public void shouldHaveNoErrorsForValidInput() throws Exception {
        account = accountBuilder.build();
        HashMap errors = verifyInputs(account);

        assertThat(errors.size(), is(0));
    }
    
    @Test
    public void shouldComplainAboutAnInvalidEmail() throws Exception {
        String invalidEmail = "invalid.email.address";
        account = accountBuilder.setAccountEmailAddress(invalidEmail).build();

        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("email", "enter a valid email", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyPassword() throws Exception {
        String emptyPassword = "";

        account = accountBuilder.setAccountPassword(emptyPassword).build();

        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("password", "enter a password", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyName() throws Exception {
        String emptyName = "";

        account = accountBuilder.setAccountName(emptyName).build();

        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("name", "enter a name", errors);
    }

    @Test
    public void shouldComplainAboutAnEmptyPhoneNumber() throws Exception {
        String emptyPhoneNumber = "";

        account = accountBuilder.setAccountPhoneNumber(emptyPhoneNumber).build();

        HashMap errors = verifyInputs(account);

        assertThereIsOneErrorFor("phoneNumber", "enter valid phone number", errors);
    }

    private void assertThereIsOneErrorFor(String field, String expected, HashMap<String, String> errors) {
        assertThat(errors.size(), is(1));
        assertThat(errors.get(field), containsString(expected));
    }
}
