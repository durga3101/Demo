package com.trailblazers.freewheelers.model;

import java.util.Date;

//PurchasedItem
public class PurchasedItem {

    private Long order_id;
    private Long account_id;
    private Long item_id;
    private Date reservation_timestamp;
    private OrderStatus status = OrderStatus.NEW;
    private String note = "";

    public PurchasedItem(){

    }

    public PurchasedItem(Long account_id, Long item_id, Date rightNow) {
        this.account_id = account_id;
        this.item_id = item_id;
        this.reservation_timestamp = rightNow;
    }

    public Long getItem_id() {
        return item_id;
    }

    public PurchasedItem setItem_id(Long item_id) {
        this.item_id = item_id;
        return this;
    }

    public Date getReservation_timestamp() {
        return reservation_timestamp;
    }

    public PurchasedItem setReservation_timestamp(Date reservation_timestamp) {
        this.reservation_timestamp = reservation_timestamp;
        return this;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public PurchasedItem setAccount_id(Long account_id) {
        this.account_id = account_id;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PurchasedItem setNote(String note) {
        this.note = note;
        return this;
    }

    public String getNote() {
        return note;
    }

}
