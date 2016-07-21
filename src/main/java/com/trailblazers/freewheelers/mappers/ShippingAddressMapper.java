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
            "SELECT street_1, street_2, city, state, postcode " +
                    "FROM shipping_address " +
                    "WHERE account_id = #{account_id}"
    )
    ShippingAddress getFromAccountId(long account_id);
}
