package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.ShippingAddress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface ShippingAddressMapper {
    @Insert(
            "INSERT INTO shipping_address (account_id, street_1, street_2, city, state, postcode, country) " +
                    "VALUES (#{account_id}, #{street_1}, #{street_2}, #{city}, #{state}, #{postcode} ,#{country})"
    )
    @Options(keyProperty = "shipping_address_id", useGeneratedKeys = true)
    Integer insert(ShippingAddress shippingAddress);

    @Select(
            ""
    )
    ShippingAddress getFromAccountId(long l);
}
