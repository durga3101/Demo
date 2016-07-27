package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.OrderedItemMapper;
import com.trailblazers.freewheelers.model.OrderedItem;
import com.trailblazers.freewheelers.persistence.persistence.MapperTestBase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderedItemMapperTest extends MapperTestBase{


    private OrderedItemMapper orderedItemMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        orderedItemMapper = getSqlSession().getMapper(OrderedItemMapper.class);

    }

    @Test
    public void shouldInsertAndRetrieveOrderedItemIntoDatabase(){
        Long orderId = 1L;
        Long itemId = 2L;
        Long quantity = 3L;
        OrderedItem orderedItemOne = new OrderedItem(orderId, itemId, quantity);
        OrderedItem orderedItemTwo = new OrderedItem(orderId, itemId +1 , quantity + 1);

        orderedItemMapper.insert(orderedItemOne);
        orderedItemMapper.insert(orderedItemTwo);

        List<OrderedItem> returnedOrderedItems = orderedItemMapper.getByOrderId(orderId);

        assertEquals(orderedItemOne.getOrder_id(), returnedOrderedItems.get(0).getOrder_id());
        assertEquals(orderedItemOne.getItem_id(), returnedOrderedItems.get(0).getItem_id());
        assertEquals(orderedItemOne.getQuantity(), returnedOrderedItems.get(0).getQuantity());
        assertEquals(2, returnedOrderedItems.size());


        assertEquals(orderedItemTwo.getOrder_id(), returnedOrderedItems.get(1).getOrder_id());
        assertEquals(orderedItemTwo.getItem_id(), returnedOrderedItems.get(1).getItem_id());
        assertEquals(orderedItemTwo.getQuantity(), returnedOrderedItems.get(1).getQuantity());
    }

}