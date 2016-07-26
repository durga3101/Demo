package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.OrderItemMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class OrderItemMapperTest extends MapperTestBase {

    private OrderItemMapper orderItemMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        orderItemMapper = getSqlSession().getMapper(OrderItemMapper.class);
    }

//    @Test
//    public void shouldInsertAndRetrieveOneItem(){
//        Long orderId = 1L;
//        Long itemId = 2L;
//
//        orderItemMapper.insert(orderId, itemId);
//
//        assertEquals(1, orderItemMapper.getAllItemsByOrderId(orderId).size());
//    }
//
//    @Test
//    public void shouldInsertItemAndOrderAndReturnAListOfItems(){
//        Long oneOrderId = 1l;
//        Long oneItemId = 1l;
//        Long twoItemId = 2l;
//        Long threeItemId = 3l;
//
//        Long twoOrderId = 2l;
//        Long fourItemId = 4l;
//        Long fiveItemId = 5l;
//
//        orderItemMapper.insert(oneOrderId, oneItemId);
//        orderItemMapper.insert(oneOrderId, twoItemId);
//        orderItemMapper.insert(oneOrderId, threeItemId);
//
//        orderItemMapper.insert(twoOrderId, fourItemId);
//        orderItemMapper.insert(twoOrderId, fiveItemId);
//
//        List<Long> itemIdOfOrderOne = orderItemMapper.getAllItemsByOrderId(oneOrderId);
//        assertEquals( 3, itemIdOfOrderOne.size());
//
//
//        List<Long> itemIdOfOrderTwo = orderItemMapper.getAllItemsByOrderId(twoOrderId);
//        assertEquals( 2, itemIdOfOrderTwo.size());
//
//    }
//
//    @Test
//    public void shouldGetListOfPurchasedItemsFromOrderId(){
//
//    }

}