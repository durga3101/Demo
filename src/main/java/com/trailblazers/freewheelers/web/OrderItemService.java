package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.OrderedItem;

public interface OrderItemService {
    void save(long orderId, Long itemId);

    void save(OrderedItem orderedItem);

}
