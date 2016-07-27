package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.OrderedItemMapper;
import com.trailblazers.freewheelers.model.OrderedItem;
import com.trailblazers.freewheelers.service.OrderedItemService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrderedItemServiceImplTest {

    @Mock
    SqlSession sqlSession;
    @Mock
    OrderedItemMapper orderedItemMapper;

    OrderedItemService orderedItemService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(sqlSession.getMapper(OrderedItemMapper.class)).thenReturn(orderedItemMapper);

        orderedItemService = new OrderedItemServiceImpl(sqlSession);
    }

    @Test
    public void shouldCallMapperWhenSavingOrderedItemIntoDatabase(){
        OrderedItem orderedItem = new OrderedItem(1l, 2l, 2l);

        orderedItemService.save(orderedItem);

        verify(orderedItemMapper).insert(orderedItem);
        verify(sqlSession).commit();
    }

    @Test
    public void shouldReturnListOfOrderedItemsForOrder(){
        Long orderId = 1L;
        List<OrderedItem> orderedItems = new ArrayList<>();
        when(orderedItemMapper.getByOrderId(orderId)).thenReturn(orderedItems);

        assertEquals(orderedItems, orderedItemService.getAllOrderedItemsByOrderId(orderId));
    }
}