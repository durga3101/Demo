package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.PurchasedItemMapper;
import com.trailblazers.freewheelers.model.ReserveOrder;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PurchasedItemServiceImplTest {

    private ReserveOrder reserveOrder;
    private SqlSession sqlSession;
    private PurchasedItemMapper purchasedItemMapper;

    @Before
    public void setUp() throws Exception {
        reserveOrder = mock(ReserveOrder.class);
        sqlSession = mock(SqlSession.class);
        purchasedItemMapper = mock(PurchasedItemMapper.class);
        when(sqlSession.getMapper(PurchasedItemMapper.class)).thenReturn(purchasedItemMapper);
    }

    @Test
    public void shouldInsertOrderIntoDatabaseWhenOrderIsNotThere(){
        when(reserveOrder.getOrder_id()).thenReturn(null);

        PurchasedItemServiceImpl reserveOrderService = new PurchasedItemServiceImpl(sqlSession);
        reserveOrderService.save(reserveOrder);

        verify(purchasedItemMapper).insert(reserveOrder);

    }

    @Test
    public void shouldUpdateOrderIntoDatabaseWhenOrderExists(){
        when(reserveOrder.getOrder_id()).thenReturn(anyLong());

        PurchasedItemServiceImpl reserveOrderService = new PurchasedItemServiceImpl(sqlSession);
        reserveOrderService.save(reserveOrder);

        verify(purchasedItemMapper).update(reserveOrder);

    }

//    @Test
//    public void shouldInvokeOrderMapperWhenSavingAnOrder() throws Exception {
//        OrderMapper orderMapper = mock(OrderMapper.class);
//        Order order = mock(Order.class);
//        when(sqlSession.getMapper(OrderMapper.class)).thenReturn(orderMapper);
//
//        ReserveOrderServiceImpl reserveOrderService = new ReserveOrderServiceImpl(sqlSession);
//        reserveOrderService.saveOrder(order);
//
//        verify(orderMapper).insert(order);
//
//    }
//
//    @Test
//    public void shouldInvokeOrderMapperWhenGettingAnOrder() throws Exception {
//        SqlSession sqlSession = mock(SqlSession.class);
//        OrderMapper orderMapper = mock(OrderMapper.class);
//        when(sqlSession.getMapper(OrderMapper.class)).thenReturn(orderMapper);
//
//        ReserveOrderServiceImpl reserveOrderService = new ReserveOrderServiceImpl(sqlSession);
//
//        reserveOrderService.getAllPurchasedItems(anyLong());
//
//        verify(orderMapper).getAllOrdersByAccountId(anyLong());
//    }
}