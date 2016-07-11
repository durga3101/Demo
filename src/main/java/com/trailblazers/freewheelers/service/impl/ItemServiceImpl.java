package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.ItemMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private final SqlSession sqlSession;
    private final ItemMapper itemMapper;

    public ItemServiceImpl() {
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    protected ItemServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.itemMapper = sqlSession.getMapper(ItemMapper.class);
    }

    @Override
    public Item get(Long item_id) {
        return itemMapper.getByItemId(item_id);
    }

    @Override
    public Item getByName(String name) {
        return itemMapper.getByName(name);
    }

    @Override
    public void delete(Item item) {
        itemMapper.delete(item);
        sqlSession.commit();
    }

    @Override
    public List<Item> findAll() {
        sqlSession.clearCache();
        return itemMapper.getAllItems();
    }

    @Override
    public List<Item> getItemsWithNonZeroQuantity() {
        sqlSession.clearCache();
        return itemMapper.getAvailableItems();
    }

    @Override
    public void saveAll(List<Item> items) {
        for (Item item : items) {
            saveItem(item);
        }
    }

    @Override
    public void refreshItemList(Item item) {
        List<Item> allItems = findAll();
        allItems.add(item);
    }

    @Override
    public void deleteItems(List<Item> items) {
        for (Item item : items) {
            delete(item);
        }
    }

    @Override
    public void decreaseQuantityByOne(Item item) {
        item.setQuantity(item.getQuantity() - 1);
        itemMapper.update(item);
        sqlSession.commit();
    }

    @Override
    public Item saveItem(Item item) {
        if( item.getQuantity() > 0){
            insertOrUpdate(item);
            sqlSession.commit();
            return item;
        }
        item.setQuantity(null);
        return item;
    }

    private void insertOrUpdate(Item item) {

        if (item.getQuantity() == null ||item.getPrice() == null) return;

        if (item.getItemId() == null) {
            itemMapper.insert(item);
        } else {
            itemMapper.update(item);
        }
    }
}
