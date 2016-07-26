package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.OrderMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.web.Order;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    private OrderServiceImpl orderService;
    private Account account;
    private OrderMapper orderMapper;
    private SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        orderMapper = mock(OrderMapper.class);
        sqlSession = mock(SqlSession.class);
        when(sqlSession.getMapper(OrderMapper.class)).thenReturn(orderMapper);
        orderService = new OrderServiceImpl(sqlSession);
        account = new Account();
        account.setAccount_id(1L);

    }

    @Test
    public void shouldReturnOrderWhenCreated(){

        Order returnedOrder = orderService.createOrder(account);

        assertNotNull(returnedOrder);
    }

    @Test
    public void shouldInvokeOrderMapperWhenCreatingANewOrder(){
        orderService.createOrder(account);

        verify(orderMapper).insert((Order) any());
        verify(sqlSession).commit();
    }

    @Test
    public void shouldReturnAllOrdersWhenGivenAccountId(){
        List<Order> expectedOrders = new ArrayList<>();
        Long account_id = account.getAccount_id();
        when(orderMapper.getAllOrdersByAccountId(account_id)).thenReturn(expectedOrders);

        List<Order> orders = orderService.getOrders(account_id);

        assertEquals(orders, expectedOrders);
        verify(orderMapper).getAllOrdersByAccountId(account_id);
    }

}