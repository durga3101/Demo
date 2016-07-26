package com.trailblazers.freewheelers.model;

public class AccountRole {

    private Long role_id;
    private String account_name;

    private String role;

    private String email_address;

    public String getEmail_address() {
        return email_address;
    }

    public AccountRole setEmail_address(String email_address) {
        this.email_address = email_address;
        return this;
    }

    public Long getRole_id() {
        return role_id;
    }

    public AccountRole setAccount_name(String account_name) {
        this.account_name = account_name;
        return this;
    }

    public AccountRole setRole(String role) {
        this.role = role;
        return this;
    }

    public String getRole() {
        return role;
    }
}
