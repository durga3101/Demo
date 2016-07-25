package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.PurchasedItemMapper;
import com.trailblazers.freewheelers.model.PurchasedItem;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PurchasedItemServiceImplTest {

    private PurchasedItem purchasedItem;
    private SqlSession sqlSession;
    private PurchasedItemMapper purchasedItemMapper;

    @Before
    public void setUp() throws Exception {
        purchasedItem = mock(PurchasedItem.class);
        sqlSession = mock(SqlSession.class);
        purchasedItemMapper = mock(PurchasedItemMapper.class);
        when(sqlSession.getMapper(PurchasedItemMapper.class)).thenReturn(purchasedItemMapper);
    }

    @Test
    public void shouldInsertOrderIntoDatabaseWhenOrderIsNotThere(){
        when(purchasedItem.getOrder_id()).thenReturn(null);

        PurchasedItemServiceImpl reserveOrderService = new PurchasedItemServiceImpl(sqlSession);
        reserveOrderService.save(purchasedItem);

        verify(purchasedItemMapper).insert(purchasedItem);

    }

    @Test
    public void shouldUpdateOrderIntoDatabaseWhenOrderExists(){
        when(purchasedItem.getOrder_id()).thenReturn(anyLong());

        PurchasedItemServiceImpl reserveOrderService = new PurchasedItemServiceImpl(sqlSession);
        reserveOrderService.save(purchasedItem);

        verify(purchasedItemMapper).update(purchasedItem);

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