package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.OrderStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class OrderTest {

    private Order order;
    private Date date = new Date();
    private Long accountId = 1l;
    private Long orderId = 2l;
    private OrderStatus status = OrderStatus.NEW;

    @Before
    public void setUp() throws Exception {
        order = new Order(accountId, date, status);
    }

    @Test
    public void shouldReturnAccountId() throws Exception {
        assertEquals(accountId,order.getAccount_id());
    }

    @Test
    public void shouldReturnDate() throws Exception {
        assertEquals(date, order.getReservation_timestamp());
    }

    @Test
    public void shouldReturnStatus() throws Exception {
        assertEquals(status, order.getStatus());
    }

    @Test
    public void shouldReturnOrderId() throws Exception {
        order.setOrder_id(orderId);
        assertEquals(orderId, order.getOrder_id());
    }

}