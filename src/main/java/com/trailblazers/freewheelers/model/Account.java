package com.trailblazers.freewheelers.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Account {

    private Long account_id;
    private String account_name;
    private String password;
    private boolean enabled;
    private String email_address;
    private String phone_number;
    private String country;


    public String getCountry() {
        return country;
    }

    public Account(String password, boolean enabled, String email_address, String phoneNumber, String country, String account_name) {
        this.account_id = 0L;
        this.account_name = account_name;
        this.setPassword(password);
        this.enabled = enabled;
        this.email_address = email_address;
        this.phone_number = phoneNumber;
        this.country = country;
    }

    public Account() {
        this.account_id = 0L;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public String getPassword() {
        return password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone_number(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public Account setPassword(String password) {

        if(!password.equals("")){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            password = passwordEncoder.encode(password);
        }

        this.password = password;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail_address() {
        return email_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setAccountName(String accountName) {
        this.account_name = accountName;
    }
}
