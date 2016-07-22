package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.web.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper {
    @Insert(
            "Insert into order_table(account_id, status, reservation_timestamp) "+
                    "Values (#{account_id}, #{status}, #{reservation_timestamp})"
    )
    @Options(keyProperty = "order_id")
    Integer insert(Order order) ;

    @Select(
            "select * From order_table " +
                    "Where account_id = #{account_id} " +
                    "Limit 1"
    )
    Order getOrderByAccountId(Long account_id);
}
