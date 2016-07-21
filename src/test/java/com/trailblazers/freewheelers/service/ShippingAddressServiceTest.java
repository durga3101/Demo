package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.ShippingAddressMapper;
import com.trailblazers.freewheelers.model.ShippingAddress;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ShippingAddressServiceTest {

    ShippingAddressService shippingAddressService;

    @Mock
    SqlSession sqlSession;

    @Mock
    ShippingAddressMapper shippingAddressMapper;

    private ShippingAddress someShippingAddress(){
        return new ShippingAddress(1L, "Some Street 1","Some Street 2", "Some City",
                "Some State", "Some PostCode");

    }

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

    @Test
    public void shouldReturnAValidShippingAddressWhenGetAddressIsCalled() throws Exception {
        //test get address retrun valid shipping address
        Long account_id  = 1l;
        //actions
       shippingAddressService.getLatestAddress(account_id);
        verify(shippingAddressMapper).getFromAccountId(account_id);

    }

    @Test
    public void shouldReturnNullAddressIfUserDontHaveAnyAddress(){
        long account_id_with_no_address = 55l;
        assertEquals(shippingAddressService.getLatestAddress(account_id_with_no_address),null);
    }

    @Test
    public void shouldReturnLatestShippingAddressWhenGetAddressIsCalled() throws Exception {
        Long account_id  = 1l;
        List<ShippingAddress> addresses = mock(List.class);
        when(shippingAddressMapper.getFromAccountId(1l)).thenReturn(addresses);
        when(addresses.size()).thenReturn(1);

       shippingAddressService.getLatestAddress(account_id);

        verify(addresses, atLeastOnce()).get(addresses.size()-1);
    }
}