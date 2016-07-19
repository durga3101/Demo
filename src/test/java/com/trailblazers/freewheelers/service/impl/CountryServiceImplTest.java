package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.CountryMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;

import static org.mockito.Mockito.*;

@Service
public class CountryServiceImplTest {

    private CountryMapper countryMapper;
    private SqlSession sqlSession;
    private CountryServiceImpl countryService;

    @Before
    public void setUp() throws Exception {
        countryMapper = mock(CountryMapper.class);
        sqlSession = mock(SqlSession.class);
        countryService = new CountryServiceImpl(sqlSession);
        when(sqlSession.getMapper(CountryMapper.class)).thenReturn(countryMapper);
    }

    @Test
   public void shouldGetUKFromCountryMapper(){
       countryService.getByName("UK");
       verify(countryMapper).getByName("UK");
   }
}
