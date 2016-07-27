package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.CountryMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.service.CountryService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryMapper countryMapper;

    public CountryServiceImpl() {this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    public CountryServiceImpl(SqlSession sqlSession) {
        this.countryMapper = sqlSession.getMapper(CountryMapper.class);
    }

    @Override
    public Country getByName(String name) {
        return countryMapper.getByName(name);
    }

    @Override
    public List<String> getCountries(){
        return countryMapper.getCountries();
    }
}
