package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.mappers.PurchasedItemMapper;
import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.model.PurchasedItem;
import com.trailblazers.freewheelers.service.PurchasedItemService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedItemServiceImpl implements PurchasedItemService {


    private final SqlSession sqlSession;

    private final PurchasedItemMapper purchasedItemMapper;

    public PurchasedItemServiceImpl() {
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    public PurchasedItemServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        purchasedItemMapper = sqlSession.getMapper(PurchasedItemMapper.class);

    }

    public void save(PurchasedItem purchasedItem) {
        if (purchasedItem.getOrder_id() == null) {
            purchasedItemMapper.insert(purchasedItem);
        } else {
            purchasedItemMapper.update(purchasedItem);
        }
        sqlSession.commit();
    }

    public List<PurchasedItem> findAllPurchasedItemsByAccountId(Long account_id) {
        sqlSession.clearCache();
        return purchasedItemMapper.getOrderByAccountId(account_id);
    }

    public List<PurchasedItem> getAllPurchasedItemsSortedByAccount() {
        sqlSession.clearCache();
        return purchasedItemMapper.getAllPurchasedItems();
    }

    public void updatePurchasedItemDetails(Long order_id, OrderStatus status, String note) {
        PurchasedItem order = purchasedItemMapper.getOrderByOrderId(order_id);

        order.setStatus(status);
        order.setNote(note);

        purchasedItemMapper.update(order);
        sqlSession.commit();
    }
}

