package com.trailblazers.freewheelers.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CountryReader {
    private BufferedReader bufferedReader;

    public CountryReader(BufferedReader bufferedReader) {

        this.bufferedReader = bufferedReader;
    }

    public String[] getCountries() {
        String countries = "";
        try {


            String line = bufferedReader.readLine();
            while (line != null) {
                countries += line+" ";
                line = bufferedReader.readLine() ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countries.split(" ");
    }
}

