package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.CountryMapper;
import com.trailblazers.freewheelers.model.Country;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountryMapperTest extends MapperTestBase {

    public static final String SOME_COUNTRY = "India";
    private CountryMapper countryMapper;
    private Country country;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        countryMapper = getSqlSession().getMapper(CountryMapper.class);
    }

    @Test
    public void shouldGetAccountByName() throws Exception {
        country = someCountry();
        country.setCountry_name(SOME_COUNTRY);
        countryMapper.insert(country);

        Country fetchedFromDB = countryMapper.getByName(SOME_COUNTRY);

        assertEquals(country,fetchedFromDB);
    }

    private Country someCountry() {
        return new Country();
    }
}
