package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ReserveOrderTest {

    private Long accountId;
    private Long itemId;
    private Date date;
    private ReserveOrder reserveOrder;

    @Before
    public void setUp() throws Exception {
        accountId = new Long("5");
        itemId = new Long("10");
        date = mock(Date.class);

        reserveOrder = new ReserveOrder(accountId, itemId, date);

    }

    @Test
    public void shouldReturnItemIdFromConstructorWhenGetItemIdIsCalled() {
        assertEquals(itemId, reserveOrder.getItem_id());
    }

    @Test
    public void shouldReturnDateFromConstructorWhenGetDateIsCalled() {
        assertEquals(date, reserveOrder.getReservation_timestamp());
    }

    @Test
    public void shouldReturnAccountIdFromConstructorWhenGetAccountIdIsCalled() {
        assertEquals(accountId, reserveOrder.getAccount_id());
    }

    @Test
    public void shouldSetOrderStatusAndReturnNewStatus() {
        reserveOrder.setStatus(OrderStatus.IN_PROGRESS);

        assertEquals(OrderStatus.IN_PROGRESS, reserveOrder.getStatus());
    }

    @Test
    public void shouldSetNoteAndReturnNewNote() {
        reserveOrder.setNote("Test note");

        assertEquals("Test note", reserveOrder.getNote());
    }

}