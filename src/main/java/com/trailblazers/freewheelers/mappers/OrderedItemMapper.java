package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.OrderedItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface OrderedItemMapper {

    @Insert(
            "INSERT INTO ordered_items (order_id, item_id, quantity) "+
            "VALUES (#{order_id}, #{item_id}, #{quantity})"

    )void insert(OrderedItem orderedItem);

    @Select(
            "SELECT order_id, item_id, quantity " +
                    "FROM ordered_items " +
                    "WHERE order_id = #{orderId}"
    )
    OrderedItem getByOrderId(Long orderId);
}
