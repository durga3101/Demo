package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.ShippingAddressMapper;
import com.trailblazers.freewheelers.model.ShippingAddress;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShippingAddressMapperTest extends MapperTestBase {
    private ShippingAddressMapper shippingAddressMapper;
    private ShippingAddress shippingAddress;

    private ShippingAddress someShippingAddress() {
        return new ShippingAddress(1L, "Some Street 1", "Some Street 2", "Some City",
                "Some State", "Some PostCode");

    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        shippingAddressMapper = getSqlSession().getMapper(ShippingAddressMapper.class);
    }


    @Test
    public void shouldInsertAndGetAccount() throws Exception {
        shippingAddress = someShippingAddress();
        shippingAddressMapper.insert(shippingAddress);

        List<ShippingAddress> addresses = shippingAddressMapper.getFromAccountId(1l);
        assertEquals(addresses.size(),1);
        assertTrue(addresses.get(0).getStreet_1().equals("Some Street 1"));
    }

    @Test
    public void shouldInsertMultipleAddressesAndGetMultipleAddresses() throws Exception {
        shippingAddress = someShippingAddress();
        shippingAddressMapper.insert(shippingAddress);
        shippingAddress.setStreet_1("another street");
        shippingAddress.setShipping_address_id(1l);
        shippingAddressMapper.insert(shippingAddress);

        List<ShippingAddress> addresses = shippingAddressMapper.getFromAccountId(1l);
        assertEquals(addresses.size(),2);
        assertEquals(addresses.get(0).getStreet_1(),"Some Street 1");
        assertEquals(addresses.get(1).getStreet_1(),"another street");

    }
}
