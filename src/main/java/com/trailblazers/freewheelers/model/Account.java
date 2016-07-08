package com.trailblazers.freewheelers.model;

public class Account {

    private Long account_id;
    private String account_name;
    private String password;
    private boolean enabled;
    private String email_address;
    private String phoneNumber;
    private String country;


    public String getCountry() {
        return country;
    }

    public Account(String password, boolean enabled, String email_address, String phoneNumber, String country, String account_name) {
        this.account_id = 0L;
        this.account_name = account_name;
        this.password = password;
        this.enabled = enabled;
        this.email_address = email_address;
        this.phoneNumber = phoneNumber;
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

    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail_address() {
        return email_address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAccountName(String accountName) {
        this.account_name = accountName;
    }
}
