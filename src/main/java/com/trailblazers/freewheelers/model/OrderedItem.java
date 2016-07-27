package com.trailblazers.freewheelers.model;

public class OrderedItem {

    private Long order_id;
    private Long item_id;
    private Long quantity;

    public OrderedItem() {
    }

    public OrderedItem(Long order_id, Long item_id, Long quantity) {

        this.order_id = order_id;
        this.item_id = item_id;
        this.quantity = quantity;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
