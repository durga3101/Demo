package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.mappers.OrderItemMapper;
import com.trailblazers.freewheelers.model.OrderedItem;
import com.trailblazers.freewheelers.web.OrderItemService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemMapper orderItemMapper;
    private final SqlSession sqlSession;

    public OrderItemServiceImpl(){
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }
    public OrderItemServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.orderItemMapper = sqlSession.getMapper(OrderItemMapper.class);
    }

    @Override
    public void save(long orderId, Long itemId) {
        orderItemMapper.insert(orderId, itemId);
        sqlSession.commit();
    }

    @Override
    public void save(OrderedItem orderedItem) {
        orderItemMapper.insert(orderedItem);
        sqlSession.commit();
    }
}
