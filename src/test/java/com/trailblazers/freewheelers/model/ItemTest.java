package com.trailblazers.freewheelers.model;

import org.junit.Test;

import java.math.BigDecimal;

import static com.trailblazers.freewheelers.model.ItemType.FRAME;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void shouldNotBeEqualWhenItemHasDifferentID() {
        Item firstItem = new Item();
        firstItem.setItemId(1L);
        Item secondItem = new Item();
        assertNotEquals(firstItem, secondItem);
    }

    @Test
    public void shouldNotBeEqualWhenItemIDIsNull() {
        Item firstItem = new Item();
        firstItem.setItemId(1L);
        Item secondItem = new Item();
        secondItem.setItemId(null);
        assertNotEquals(firstItem, secondItem);
    }

    @Test
    public void shouldNotBeEqualWhenItemHasDifferentName() {
        Item firstItem = new Item();
        firstItem.setName("itemName");
        Item secondItem = new Item();
        assertNotEquals(firstItem, secondItem);
    }

    @Test
    public void shouldNotBeEqualWhenItemHasNullName() {
        Item firstItem = new Item();
        firstItem.setName("itemName");
        Item secondItem = new Item();
        secondItem.setName(null);
        assertNotEquals(firstItem, secondItem);
    }

    @Test
    public void shouldNotBeEqualWhenItemHasDifferentPrice() {
        Item firstItem = new Item();
        firstItem.setPrice(new BigDecimal(10));
        Item secondItem = new Item();
        assertNotEquals(firstItem, secondItem);
    }

    @Test
    public void shouldNotBeEqualWhenItemHasNullPrice() {
        Item firstItem = new Item();
        firstItem.setPrice(new BigDecimal(10));
        Item secondItem = new Item();
        secondItem.setPrice(null);
        assertNotEquals(firstItem, secondItem);
    }

    @Test
    public void shouldNotBeEqualWhenItemHasNoDescription() {
        Item firstItem = new Item();
        firstItem.setDescription("description");
        Item secondItem = new Item();
        assertNotEquals(firstItem, secondItem);
    }

    @Test
    public void shouldNotBeEqualWhenItemHasDifferentDescription() {
        Item firstItem = new Item();
        firstItem.setDescription("description");
        Item secondItem = new Item();
        secondItem.setDescription("anotherdescription");
        assertNotEquals(firstItem, secondItem);
    }

    @Test
    public void shouldNotBeEqualWhenItemHasDifferentType() {
        Item firstItem = new Item();
        firstItem.setType(FRAME);
        Item secondItem = new Item();
        assertNotEquals(firstItem, secondItem);
    }

    @Test
    public void shouldNotBeEqualWhenItemHasDifferentQuantity() {
        Item firstItem = new Item();
        firstItem.setQuantity(1L);
        Item secondItem = new Item();
        assertNotEquals(firstItem, secondItem);
    }

    @Test
    public void shouldBeEqualWhenTwoItemsEqual(){
        Item firstItem = new Item();
        firstItem.setItemId(1L);
        Item secondItem = firstItem;

        assertEquals(firstItem, secondItem);
    }

    @Test
    public void shouldNotBeEqualWhenItemIsNull() {
        Item firstItem = new Item();

        assertNotEquals(firstItem, null);
    }

}