package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Country;

public interface CountryService {
    Country getByName(String name);
}
