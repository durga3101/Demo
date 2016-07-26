package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.web.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Account account);

    List<Order> getOrders(Long account_id);

    List<Order> getAllOrders();

    void updateOrder(Long orderId, OrderStatus status, String note);
}
