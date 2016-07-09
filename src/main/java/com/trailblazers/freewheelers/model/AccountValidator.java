package com.trailblazers.freewheelers.model;

import java.util.HashMap;

public class AccountValidator {

    public static HashMap<String,String>  verifyInputs(Account account) {
        HashMap<String,String> errors = new HashMap();

        if (!account.getEmail_address().contains("@")) {
           errors.put("email", "Must enter a valid email!");
        }

        if(account.getPassword().isEmpty()) {
            errors.put("password", "Must enter a password!");
        }

        if(account.getAccount_name().isEmpty()) {
            errors.put("name", "Must enter a name!");
        }

        if(account.getPhoneNumber().isEmpty()) {
            errors.put("phoneNumber", "Must enter valid phone number!");
        }

        return errors;
    }

}