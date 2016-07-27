package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    @Insert(
            "INSERT into order_table (account_id, status, reservation_timestamp) " +
                    "values (#{account_id}, #{status}, #{reservation_timestamp}) "
    )
    @Options(keyProperty = "order_id", useGeneratedKeys = true)
    Integer insert(Order order);

    @Select(
            "select * from order_table " +
                    "where account_id = #{account_id}"
    )
    List<Order> getAllOrdersByAccountId(Long account_id);
}
