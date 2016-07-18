package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.ShippingAddressMapper;
import com.trailblazers.freewheelers.model.ShippingAddress;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ShippingAddressServiceTest {

    ShippingAddressService shippingAddressService;

    @Mock
    SqlSession sqlSession;

    @Mock
    ShippingAddressMapper shippingAddressMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(sqlSession.getMapper(ShippingAddressMapper.class)).thenReturn(shippingAddressMapper);

        shippingAddressService = new ShippingAddressService(sqlSession);
    }

    @Test
    public void shouldCreateShippingAddressWhenThereAreNotValidationErrors(){
        ShippingAddress shippingAddress =someShippingAddress();
        shippingAddressService.createShippingAddress(shippingAddress);

        verify(shippingAddressMapper, times(1)).insert(shippingAddress);
        verify(sqlSession,times(1)).commit();

    }

    private ShippingAddress someShippingAddress(){
        return new ShippingAddress(1L, "Some Street 1","Some Street 2", "Some City",
                "Some State", "Some PostCode");

    }
}