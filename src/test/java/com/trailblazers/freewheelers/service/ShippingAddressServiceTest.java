package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.ShippingAddressMapper;
import com.trailblazers.freewheelers.model.ShippingAddress;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ShippingAddressServiceTest {
    private SqlSession sqlSession;
    private ShippingAddressService shippingAddressService;
    private ShippingAddressMapper shippingAddressMapper;
    private ShippingAddress shippingAddress;

    @Before
    public void setUp() throws Exception {
        shippingAddressMapper=mock(ShippingAddressMapper.class);
        shippingAddress=mock(ShippingAddress.class);
        when(sqlSession.getMapper(ShippingAddressMapper.class)).thenReturn(shippingAddressMapper);
        shippingAddressService = new ShippingAddressService(sqlSession);
    }
    @Test
    public void createShippingAddress() throws Exception {
        verify(shippingAddressMapper).insert(shippingAddress);
    }

}