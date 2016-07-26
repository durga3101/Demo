package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.OrderMapper;
import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.web.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class OrderMapperTest extends MapperTestBase {
    private OrderMapper orderMapper;
    private Order firstOrder;
    private Order secondOrder;
    private Order thirdOrder;
    private Long accountId = Long.valueOf(2);
    private Long anotherAccountId = Long.valueOf(3);

    @Before
    public void setUp() throws Exception {
        super.setUp();
        firstOrder = new Order(accountId, new Date(), OrderStatus.NEW);
        secondOrder = new Order(accountId, new Date(), OrderStatus.IN_PROGRESS);
        thirdOrder = new Order(anotherAccountId, new Date(), OrderStatus.READY_FOR_SHIPMENT);

        orderMapper = getSqlSession().getMapper(OrderMapper.class);
        orderMapper.insert(firstOrder);
        orderMapper.insert(secondOrder);
        orderMapper.insert(thirdOrder);
    }

    @Test
    public void shouldContainAOrderIdAfterInsertedIntoDataBase(){
        assertNotNull(firstOrder.getOrder_id());
        assertNotNull(secondOrder.getOrder_id());
    }

    @Test
    public void shouldInsertAnOrderAndGetAListOfOrdersByAccountId() throws Exception {
        List<Order> fetchedFromDB = orderMapper.getAllOrdersByAccountId(accountId);

        assertEquals(fetchedFromDB.size(), 2);
        assertEquals(fetchedFromDB.get(0).getStatus(), OrderStatus.NEW);
        assertEquals(fetchedFromDB.get(1).getStatus(), OrderStatus.IN_PROGRESS);
    }

    @Test
    public void shouldGetListOfAllOrders() throws Exception {
        List<Order> fetchedFromDB = orderMapper.getAllOrders();

        assertEquals(fetchedFromDB.size(), 3);
        assertEquals(fetchedFromDB.get(0).getStatus(), OrderStatus.NEW);
        assertEquals(fetchedFromDB.get(1).getStatus(), OrderStatus.IN_PROGRESS);
        assertEquals(fetchedFromDB.get(2).getStatus(), OrderStatus.READY_FOR_SHIPMENT);
    }
}
