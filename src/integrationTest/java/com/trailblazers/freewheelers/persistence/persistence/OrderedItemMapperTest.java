package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.OrderedItemMapper;
import com.trailblazers.freewheelers.model.OrderedItem;
import com.trailblazers.freewheelers.persistence.persistence.MapperTestBase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderedItemMapperTest extends MapperTestBase{


    private OrderedItemMapper orderedItemMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        orderedItemMapper = getSqlSession().getMapper(OrderedItemMapper.class);

    }

    @Test
    public void shouldInsertOrderedItemIntoDatabase(){
        Long orderId = 1L;
        Long itemId = 2L;
        Long quantity = 3L;
        OrderedItem orderedItem = new OrderedItem(orderId, itemId, quantity);

        orderedItemMapper.insert(orderedItem);

        OrderedItem returnedOrderedItem = orderedItemMapper.getByOrderId(orderId);

        assertEquals(orderedItem.getOrder_id(), returnedOrderedItem.getOrder_id());
        assertEquals(orderedItem.getItem_id(), returnedOrderedItem.getItem_id());
        assertEquals(orderedItem.getQuantity(), returnedOrderedItem.getQuantity());
    }

}