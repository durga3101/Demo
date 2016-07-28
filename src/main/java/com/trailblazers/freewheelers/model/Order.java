package com.trailblazers.freewheelers.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Order {
    private Long order_id;
    private Long account_id;
    private Date reservation_timestamp;
    private OrderStatus status;
    private String accountName;
    private String note;
    List<Item> orderedItems = new ArrayList<>();

    public Order(){
        //Must have an empty constructor for mapper to build an Order
    }

    public Order(Long account_id, Date date, OrderStatus status) {
        this.account_id = account_id;
        this.reservation_timestamp=date;
        this.status=status;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public Date getReservation_timestamp() {
        return reservation_timestamp;
    }

    public void setReservation_timestamp(Date reservation_timestamp) {
        this.reservation_timestamp = reservation_timestamp;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() { return status; }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long orderId) {
        this.order_id = orderId;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Item> getOrderedItems() {
        return orderedItems;
    }

    public void addToOrderedItems(Item orderedItem) {
        if(orderedItems.indexOf(orderedItem) == -1) {
            orderedItems.add(orderedItem);
        }
    }
}
