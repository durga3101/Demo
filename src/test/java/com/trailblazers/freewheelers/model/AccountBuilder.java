package com.trailblazers.freewheelers.model;

/**
 * Created by jmariano on 7/8/16.
 */
public class AccountBuilder {

    private Long account_id = 0l;
    private String account_name = "";
    private String password = "";
    private boolean enabled = true;
    private String emailAddress = "";
    private String phoneNumber = "";
    private String country = "";

    public Account build(){

        return new Account(password, enabled, emailAddress, phoneNumber, country, account_name);
    }

        public AccountBuilder setAccountId(Long account_id){
            this.account_id = account_id;
            return this;
        }

        public AccountBuilder setAccountName(String account_name){
            this.account_name = account_name;
            return this;
        }

        public AccountBuilder setAccountPassword(String password){
            this.password = password;
            return this;
        }

        public AccountBuilder setAccountEmailAddress(String emailAddress){
            this.emailAddress = emailAddress;
            return this;
        }

        public AccountBuilder setAccountPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public AccountBuilder setAccountCountry(String country){
        this.country = country;
        return this;
    }
}
