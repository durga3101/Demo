package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.ShippingAddressMapper;
import com.trailblazers.freewheelers.model.ShippingAddress;
import org.junit.Before;
import org.junit.Test;

public class ShippingAddressMapperTest extends MapperTestBase {
    private ShippingAddressMapper shippingAddressMapper;
    private ShippingAddress shippingAddress;

    @Before
    public void setUp() throws Exception {
        shippingAddressMapper = getSqlSession().getMapper(ShippingAddressMapper.class);
    }

    @Test
    public void shouldInsertAndGetAccount() throws Exception {
        shippingAddress = new ShippingAddress("","","","","");
        shippingAddressMapper.insert(shippingAddress);
    }
}
