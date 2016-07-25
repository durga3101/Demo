package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.PurchasedItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PurchasedItemMapper {

    @Insert(
        "INSERT INTO reserve_order (account_id, item_id, status, note, reservation_timestamp) " +
        "VALUES (#{account_id}, #{item_id}, #{status}, #{note}, #{reservation_timestamp})"
    )
    @Options(keyProperty = "order_id", useGeneratedKeys = true)
    Integer insert(PurchasedItem order);

    @Delete(
        "DELETE FROM reserve_order WHERE order_id = #{order_id}"
    )
    void delete(PurchasedItem purchasedItem);

    @Update(
        "UPDATE reserve_order " +
        "SET account_id=#{account_id}, item_id=#{item_id}, status=#{status}, note=#{note}, reservation_timestamp=#{reservation_timestamp} " +
        "WHERE order_id=#{order_id}"
    )
    void update(PurchasedItem purchasedItem);

    @Select(
        "SELECT order_id, account_id, item_id, status, note, reservation_timestamp " +
        "FROM reserve_order " +
        "ORDER BY account_id"
    )
    @Results(value = {
        @Result(property="order_id"),
        @Result(property="account_id"),
        @Result(property="item_id"),
        @Result(property="status"),
        @Result(property="note"),
        @Result(property="reservation_timestamp")
    })
    List<PurchasedItem> getAllPurchasedItems();

    @Select(
            "SELECT order_id, account_id, item_id, status, note, reservation_timestamp " +
                    "FROM reserve_order " +
                    "WHERE order_id = #{order_id}"
    )
    PurchasedItem getOrderByOrderId(Long order_id);

    @Select(
            "SELECT order_id, account_id, item_id, status, note, reservation_timestamp " +
            "FROM reserve_order " +
            "WHERE account_id=#{account_id}"
    )
    @Results(value = {
            @Result(property="order_id"),
            @Result(property="account_id"),
            @Result(property="item_id"),
            @Result(property="status"),
            @Result(property="note"),
            @Result(property="reservation_timestamp")
    })
    List<PurchasedItem> getOrderByAccountId(Long account_id);

}
