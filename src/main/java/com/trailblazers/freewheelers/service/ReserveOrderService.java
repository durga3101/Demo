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

<<<<<<< 80711c5f3bb8f11be2c2093cdc6b001eb8324382
=======
//    Order saveOrder(Order order);
//
//    List<Order> getAllOrders(Long account_id);
>>>>>>> [Ella/Luke][#198] sucessfully saving order in the order table and recieving order id
}


