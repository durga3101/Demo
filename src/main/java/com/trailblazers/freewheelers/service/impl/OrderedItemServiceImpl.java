package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.mappers.OrderedItemMapper;
import com.trailblazers.freewheelers.model.OrderedItem;
import com.trailblazers.freewheelers.service.OrderedItemService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {
    private final OrderedItemMapper orderedItemMapper;
    private SqlSession sqlSession;

    public OrderedItemServiceImpl(){
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    public OrderedItemServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.orderedItemMapper = sqlSession.getMapper(OrderedItemMapper.class);
    }

    @Override
    public void save(OrderedItem orderedItem) {
        orderedItemMapper.insert(orderedItem);
        sqlSession.commit();
    }
}
