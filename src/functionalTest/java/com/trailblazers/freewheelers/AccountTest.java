package com.trailblazers.freewheelers;

import org.junit.Ignore;
import org.junit.Test;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.*;

public class AccountTest extends UserJourneyBase {

    //IGNORED because this flow is now tested in UserFlowTest
    @Ignore
    @Test
    public void testCreateAccount() throws Exception {
        String jan = "Jan Plewka";

        admin
                .there_is_no_account_for(jan);

        user
                .is_logged_out()
                .logs_in_with(jan, SOME_PASSWORD);

        screen
                .shows_error_alert("login attempt was not successful");

        user
                .creates_an_account(jan, SOME_EMAIL, SOME_PASSWORD,SOME_PASSWORD, EMPTY_PHONE_NUMBER, SOME_COUNTRY);

        screen
                .shows_error("Must enter valid phone number!", "phoneNumber_field");

        user
                .creates_an_account(jan, SOME_EMAIL, SOME_PASSWORD, SOME_PASSWORD,SOME_PHONE_NUMBER, UNSELECTED_COUNTRY);

        screen
                .shows_error("Must select a country!", "country_field");


        user
                .creates_an_account(jan, SOME_EMAIL, SOME_PASSWORD,SOME_PASSWORD, SOME_PHONE_NUMBER, UNSELECTED_COUNTRY);

        screen
                .shows_error("Must select a country!", "country_field");

        user
                .creates_an_account(jan, SOME_EMAIL, SOME_PASSWORD,SOME_PASSWORD, SOME_PHONE_NUMBER, SOME_COUNTRY);

        screen
                .shows_message("account has been created");

        user
                .is_logged_out()
                .logs_in_with(jan, SOME_PASSWORD);

        screen
                .shows_in_navbar("Welcome " + jan);
    }

    @Test
    public void testAccessRights() throws Exception {
        String Hugo = "Hugo Huser";
        String Arno = "Arno Admin";

        admin
                .there_is_a_user(Hugo, SOME_PASSWORD)
                .there_is_an_admin(Arno, SOME_PASSWORD);

        user
                .is_logged_out()
                .visits_his_profile();
        screen
                .shows_login();

        user
                .logs_in_with(Hugo, SOME_PASSWORD)
                .visits_his_profile();
        screen
                .shows_profile_for(Hugo);

        user
                .visits_admin_profile();
        screen
                .shows_error_alert("access is denied");

        user
                .logs_in_with(Arno, SOME_PASSWORD)
                .visits_admin_profile();
        screen
                .shows_admin_profile();

        user
                .visits_profile_for(Hugo);
        screen
                .shows_profile_for(Hugo);
    }

    @Test
    public void testViewCountryOnUserProfile() throws Exception {
        admin
                .there_is_a_uesr_create_country("name", SOME_PASSWORD, "UK");
        user
                .is_logged_out()
                .visits_his_profile();
        screen
                .shows_login();

        user
                .logs_in_with("name", SOME_PASSWORD)
                .visits_his_profile();
        screen
                .shows_profile_for_country("name", "UK");
    }


    @Test
    public void passwordShouldBeMasked() throws Exception {
        user
                .openCreateAccountPage();
        screen.
                checkPasswordIsMasked();
    }

    @Ignore
    public void newUserShouldAbleToCreateAccountOnlyWhenEmailDoesNotExist() throws Exception {
        String Raju = "Raju UserOne";
        String rajuEmail = "raj@email.com";
        String Ella = "Ella UserTwo";
        String ellaEmail = "otherUnique@email.in";


        admin
                .there_is_no_account_for(Raju)
                .there_is_no_account_for(Ella);
        user
                .creates_an_account(Raju, rajuEmail, SOME_PASSWORD,SOME_PASSWORD, SOME_PHONE_NUMBER, SOME_COUNTRY);

        screen
                .shows_message("account has been created");

        user
                .is_logged_out()
                .creates_an_account(Raju, rajuEmail, SOME_PASSWORD,SOME_PASSWORD, SOME_PHONE_NUMBER, SOME_COUNTRY);

        screen
                .shows_message("An error has occurred while creating the account.");

        user
                .creates_an_account(Ella, ellaEmail, SOME_PASSWORD,SOME_PASSWORD, SOME_PHONE_NUMBER, SOME_COUNTRY);

        screen
                .shows_message("account has been created");



    }
     @Test
    public void testPassword() throws Exception {
         String jan = "Jan Plewka1";

         admin
                 .there_is_no_account_for(jan);

         user
                 .creates_an_account(jan, SOME_EMAIL, EMPTY_PASSWORD,SOME_PASSWORD, SOME_PHONE_NUMBER, SOME_COUNTRY);

         screen
                .shows_error("Must enter a password!", "password_field");

         user
                 .creates_an_account(jan, SOME_EMAIL, SOME_PASSWORD,EMPTY_PASSWORD, SOME_PHONE_NUMBER, SOME_COUNTRY);

         screen
                 .shows_error("Must enter a password!", "confirmPassword_field");

         user
                 .creates_an_account(jan, SOME_EMAIL, SOME_PASSWORD,DIFFERENT_PASSWORD, SOME_PHONE_NUMBER, SOME_COUNTRY);

         screen
                 .shows_error( "Passwords do not match!", "confirmPassword_field");

         user
                 .creates_an_account(jan, SOME_EMAIL, INVAILD_PASSWORD,SOME_PASSWORD, SOME_PHONE_NUMBER, SOME_COUNTRY);

         screen
                 .shows_error( "Must enter a valid Password!", "password_field");

         user
                 .creates_an_account(jan, "abc@gmail.com", "Password1!","Password1!", SOME_PHONE_NUMBER, SOME_COUNTRY);

         screen
                 .shows_message("account has been created");

    }



}
