package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.OrderMapper;
import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.web.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class OrderMapperTest extends MapperTestBase {

    private OrderMapper orderMapper;
    private Order order;
    private Long accountId = Long.valueOf(2);

    @Before
    public void setUp() throws Exception {
        super.setUp();
        order = new Order(accountId, new Date(), OrderStatus.NEW);
        orderMapper = getSqlSession().getMapper(OrderMapper.class);
    }

    @Test
    public void shouldInsertAndGet() throws Exception {

        orderMapper.insert(order);

        Order fetchedFromDB = orderMapper.getOrderByAccountId(order.getAccount_id());

        assertThat(fetchedFromDB.getAccount_id(), is(accountId));
    }

}
