package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.OrderStatus;

import java.util.Date;


public class Order {
    private Long order_id;
    private Long account_id;
    private Date reservation_timestamp;
    private OrderStatus status;

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
}
