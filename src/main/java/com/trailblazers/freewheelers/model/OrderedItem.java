package com.trailblazers.freewheelers.model;

import com.trailblazers.freewheelers.web.Order;

public class OrderedItem {
    private Order order;
    private Item item;

    public OrderedItem(Order order, Item item) {
        this.order = order;
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
