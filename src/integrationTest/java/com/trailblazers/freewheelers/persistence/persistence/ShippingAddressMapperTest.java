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
        super.setUp();
        shippingAddressMapper = getSqlSession().getMapper(ShippingAddressMapper.class);
    }

    @Test
    public void shouldInsertAndGetAccount() throws Exception {
        shippingAddress = someShippingAddress();
        shippingAddressMapper.insert(shippingAddress);
    }

    private ShippingAddress someShippingAddress(){
        return new ShippingAddress("Some Street 1","Some Street 2", "Some City",
                "Some State", "Some PostCode");

    }
}
