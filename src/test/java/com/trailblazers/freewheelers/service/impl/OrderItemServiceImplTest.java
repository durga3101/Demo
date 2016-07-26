package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.OrderItemMapper;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.OrderedItem;
import com.trailblazers.freewheelers.web.Order;
import com.trailblazers.freewheelers.web.OrderItemService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderItemServiceImplTest {

    @Test
    public void shouldInvokeMapperWhenSavingAnItemAndOrderId() {
        OrderItemMapper orderItemMapper = mock(OrderItemMapper.class);
        SqlSession sqlSession = mock(SqlSession.class);
        OrderedItem orderedItem =  new OrderedItem(new Order(), new Item());
        when(sqlSession.getMapper(OrderItemMapper.class)).thenReturn(orderItemMapper);

        OrderItemService orderItemService = new OrderItemServiceImpl( sqlSession);
        orderItemService.save(orderedItem);

        verify(orderItemMapper).insert(orderedItem);
        verify(sqlSession).commit();
    }

}