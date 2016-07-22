package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Item;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ItemMapper {

    @Insert(
        "INSERT INTO item (description, name, price, type, quantity, imageURL) " +
        "VALUES (#{description}, #{name}, #{price}, #{type}, #{quantity}, #{imageURL})"
    )
    @Options(keyProperty = "itemId", keyColumn = "item_id", useGeneratedKeys = true)
    void insert(Item item);

    @Delete(
        "DELETE FROM item WHERE item_id = #{itemId}"
    )
    @Options(flushCache = true)
    void delete(Item item);

    @Update(
        "UPDATE item SET description=#{description}, name=#{name}, price=#{price}, type=#{type}, quantity=#{quantity}, imageURL=#{imageURL} WHERE item_id=#{itemId}"
    )
    void update(Item item);

    @Select(
        "SELECT item_id, name, price, type, quantity, description, imageURL FROM item"
    )
    @Results(value = {
            @Result(property="itemId", column = "item_id")
    })
    List<Item> getAllItems();

    @Select(
            "SELECT item_id as itemId, description, name, price, type, quantity, imageURL FROM item WHERE name = #{name} LIMIT 1"
    )
    Item getByName(String name);

    @Select(
            "SELECT item_id as itemId, description, name, price, type, quantity, imageURL FROM item WHERE item_id = #{itemId}"
    )
    Item getByItemId(Long itemId);

    @Select(
        "SELECT item_id, name, price, type, quantity, description, imageURL FROM item WHERE quantity > 0"
    )
    @Results(value = {
            @Result(property="itemId", column = "item_id"),
            @Result(property="name"),
            @Result(property="price"),
            @Result(property="quantity"),
            @Result(property="type"),
            @Result(property="description"),
            @Result(property="imageURL")
    })
    List<Item> getAvailableItems();

}
