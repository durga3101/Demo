package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.mappers.ShippingAddressMapper;
import com.trailblazers.freewheelers.model.ShippingAddress;
import org.apache.ibatis.session.SqlSession;

public class ShippingAddressService {

    private static ShippingAddressMapper shippingAddressMapper;
    private final SqlSession sqlSession;

    public ShippingAddressService() {
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    public ShippingAddressService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.shippingAddressMapper= sqlSession.getMapper(ShippingAddressMapper.class);
    }
    public static Integer createShippingAddress(ShippingAddress shippingAddress) {
        return shippingAddressMapper.insert(shippingAddress);
    }
}
