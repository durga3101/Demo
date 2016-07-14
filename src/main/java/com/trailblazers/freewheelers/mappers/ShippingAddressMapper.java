package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.ShippingAddress;
import org.apache.ibatis.annotations.Insert;

public interface ShippingAddressMapper {
    @Insert(
            "INSERT INTO shippingaddress (street1,street2,city,state,postcode,country) " +
                    "VALUES (#{street1}, #{street2}, #{city}, #{state}, #{postcode} ,#{country})"
    )
    Integer insert(ShippingAddress shippingAddress);
}
