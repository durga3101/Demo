package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.mappers.OrderMapper;
import com.trailblazers.freewheelers.mappers.ReserveOrderMapper;
import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.web.Order;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveOrderServiceImpl implements ReserveOrderService {


    private final SqlSession sqlSession;
    private final ReserveOrderMapper reserveOrderMapper;
    private final OrderMapper orderMapper;

    public ReserveOrderServiceImpl() {
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    public ReserveOrderServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        reserveOrderMapper = sqlSession.getMapper(ReserveOrderMapper.class);
        orderMapper=sqlSession.getMapper(OrderMapper.class);

    }

    public void save(ReserveOrder reserveOrder) {
        if (reserveOrder.getOrder_id() == null) {
            reserveOrderMapper.insert(reserveOrder);
        } else {
            reserveOrderMapper.update(reserveOrder);
        }
        sqlSession.commit();
    }

    public List<ReserveOrder> findAllOrdersByAccountId(Long account_id) {
        sqlSession.clearCache();
        return reserveOrderMapper.getOrderByAccountId(account_id);
    }

    public List<ReserveOrder> getAllOrdersByAccount() {
        sqlSession.clearCache();
        return reserveOrderMapper.getAllOrders();
    }

    public void updateOrderDetails(Long order_id, OrderStatus status, String note) {
        ReserveOrder order = reserveOrderMapper.getOrderByOrderId(order_id);

        order.setStatus(status);
        order.setNote(note);

        reserveOrderMapper.update(order);
        sqlSession.commit();
    }

    @Override
    public void saveOrder(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public Order getOrder(Long account_id) {
       return orderMapper.getOrderByAccountId(account_id);
    }


}
