package com.trailblazers.freewheelers.model;
public class ShippingAddress {
    private final String street1;
    private final String street2;
    private final String city;
    private final String state;
    private final String postcose;
    private final String country="";

    public ShippingAddress(String street1, String street2, String city, String state, String postcode) {
        this.street1=street1;
        this.street2=street2;
        this.city=city;
        this.state=state;
        this.postcose=postcode;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcose() {
        return postcose;
    }

    public String getCountry() {
        return country;
    }
}
