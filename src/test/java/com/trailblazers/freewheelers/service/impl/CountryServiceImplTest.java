package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.CountryMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.stereotype.Service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Service
public class CountryServiceImplTest {
   @Test
   public void shouldGetCountryFromCountryMapper(){
       CountryMapper countryMapper = mock(CountryMapper.class);
       SqlSession sqlSession = mock(SqlSession.class);
       when(sqlSession.getMapper(CountryMapper.class)).thenReturn(countryMapper);
       CountryServiceImpl countryService = new CountryServiceImpl(sqlSession);

       countryService.getByName("UK");
       verify(countryMapper).getByName("UK");
   }
}
