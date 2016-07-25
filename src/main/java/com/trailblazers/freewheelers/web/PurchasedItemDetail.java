package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.OrderStatus;

import java.util.Date;

public class PurchasedItemDetail {

    private Item item;
    private Account account;
    private Date reserve_time;
    private OrderStatus status;
    private String note;
    private Long orderId;

    public PurchasedItemDetail(Long orderId, Account account, Item item, Date reserve_time, OrderStatus status, String note){
        this.orderId = orderId;
        this.item = item;
        this.account = account;
        this.reserve_time = reserve_time;
        this.status = status;
        this.note = note;
    }

    public PurchasedItemDetail() {
    }

    public Item getItem() {
        return item;
    }


    public Account getAccount() {
        return account;
    }

    public Date getReserve_time() {
        return reserve_time;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderStatus[] getStatusOptions() {
        return OrderStatus.values();
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getNote() {
        return note;
    }

}
