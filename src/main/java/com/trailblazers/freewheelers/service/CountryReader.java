package com.trailblazers.freewheelers.service;

public class CountryReader {

    public CountryReader() {

    }

    public String[] getCountries() {
       String [] countries = new String[]{
               "UK",
               "USA",
               "FRANCE",
               "GERMANY",
               "CANADA",
               "ITALY"
       };
        return countries;
    }
}

