package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.OrderedItem;

import java.util.List;

public interface OrderedItemService {
    void save(OrderedItem orderedItem);

    List<OrderedItem> getAllOrderedItemsByOrderId(Long orderId);
}
