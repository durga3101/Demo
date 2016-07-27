package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Country;

import java.util.List;

public interface CountryService {
    Country getByName(String name);
    List<String> getCountries();
}
