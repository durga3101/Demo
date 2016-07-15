package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.mappers.ShippingAddressMapper;
import com.trailblazers.freewheelers.model.ShippingAddress;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressService {

    private ShippingAddressMapper shippingAddressMapper;
    private final SqlSession sqlSession;

    public ShippingAddressService() {
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    public ShippingAddressService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.shippingAddressMapper= sqlSession.getMapper(ShippingAddressMapper.class);
    }


    public void createShippingAddress(ShippingAddress shippingAddress) {
        shippingAddressMapper.insert(shippingAddress);
        sqlSession.commit();
    }
}
