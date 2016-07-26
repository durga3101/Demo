package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.OrderedItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderItemMapper {
    @Insert(
            "INSERT into item_order (order_id, item_id) " +
            "VALUES (#{orderId}, #{itemId})"
    )
    void insert(Long orderId, @Param("itemId")  Long itemId);

    @Select(
           "SELECT item_id " +
           "FROM item_order " +
           "WHERE order_id = #{orderId}"
    )
    List<Long> getAllItemsByOrderId(@Param("orderId") Long orderId);

    void insert(OrderedItem any);
}