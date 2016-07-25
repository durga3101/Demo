package com.trailblazers.freewheelers.model;

public class ShippingAddress {
    private long shipping_address_id;
    private long account_id;
    private String street_1;
    private String street_2;
    private String city;
    private String state;
    private String postcode;
    private String country;


    public ShippingAddress(long account_id, String street1, String street2, String city, String state, String postcode,String country) {
        this.account_id = account_id;
        this.shipping_address_id = 0L;
        this.street_1 = street1;
        this.street_2 = street2;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
    }

    public ShippingAddress(){
        this.shipping_address_id = 0L;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public void setShipping_address_id(Long shipping_address_id){
        this.shipping_address_id = shipping_address_id;
    }

    public void setStreet_1(String street_1) {
        this.street_1 = street_1;
    }

    public void setStreet_2(String street_2) {
        this.street_2 = street_2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getShipping_address_id() {
        return shipping_address_id;
    }

    public String getStreet_1() {
        return street_1;
    }

    public String getStreet_2() {
        return street_2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }
}
