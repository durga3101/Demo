package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.web.Order;

public interface OrderService {
    Order createOrder(Account account);
}
