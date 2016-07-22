package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.web.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReserveOrderService {

    void save(ReserveOrder reserveOrder);

    List<ReserveOrder> findAllOrdersByAccountId(Long account_id);

    List<ReserveOrder> getAllOrdersByAccount();

    void updateOrderDetails(Long order_id, OrderStatus status, String note);

    void saveOrder(Order order);

    Order getOrder(Long account_id);
}


