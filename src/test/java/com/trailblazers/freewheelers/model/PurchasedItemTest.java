package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

//PurchasedItemTest
public class PurchasedItemTest {

    private Long accountId;
    private Long itemId;
    private Date date;
    private PurchasedItem purchasedItem;

    @Before
    public void setUp() throws Exception {
        accountId = new Long("5");
        itemId = new Long("10");
        date = mock(Date.class);

        purchasedItem = new PurchasedItem(accountId, itemId, date);

    }

    @Test
    public void shouldReturnItemIdFromConstructorWhenGetItemIdIsCalled() {
        purchasedItem.setItem_id(itemId);
        assertEquals(itemId, purchasedItem.getItem_id());
    }

    @Test
    public void shouldReturnDateFromConstructorWhenGetDateIsCalled() {
        purchasedItem.setReservation_timestamp(date);
        assertEquals(date, purchasedItem.getReservation_timestamp());
    }

    @Test
    public void shouldReturnAccountIdFromConstructorWhenGetAccountIdIsCalled() {
        purchasedItem.setAccount_id(accountId);
        assertEquals(accountId, purchasedItem.getAccount_id());
    }

    @Test
    public void shouldSetOrderStatusAndReturnNewStatus() {
        purchasedItem.setStatus(OrderStatus.IN_PROGRESS);

        assertEquals(OrderStatus.IN_PROGRESS, purchasedItem.getStatus());
    }

    @Test
    public void shouldSetNoteAndReturnNewNote() {
        purchasedItem.setNote("Test note");

        assertEquals("Test note", purchasedItem.getNote());
    }

}