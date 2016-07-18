package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Country;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface CountryMapper {

    @Insert(
            "INSERT INTO country (country_name, vat_rate, duty_rate) " +
                    "VALUES (#{country_name}, #{vat_rate}, #{duty_rate})"
    )
    @Options(keyProperty = "country_id", useGeneratedKeys = true)
    Integer insert(Country country);

    @Select(
            "SELECT country_id,country_name, vat_rate, duty_rate " +
                    "FROM country " +
                    "WHERE country_name = #{country_name} "
    )
    Country getByName(String country_name);
}
