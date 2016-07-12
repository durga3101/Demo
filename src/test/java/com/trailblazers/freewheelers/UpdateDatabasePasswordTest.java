//package com.trailblazers.freewheelers;
//
//import com.trailblazers.freewheelers.mappers.AccountMapper;
//import com.trailblazers.freewheelers.model.Account;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//
//public class UpdateDatabasePasswordTest {
//
//    @Test
//    public void shouldMapperCallGetAllAccount(){
//        AccountMapper accountMapper = mock(AccountMapper.class);
//        SqlSession sqlSession = mock(SqlSession.class);
//        UpdateDatabasePassword updateDatabasePassword = new UpdateDatabasePassword(accountMapper, sqlSession);
//        updateDatabasePassword.updateDatabaseToEncryptPassword();
//        verify(accountMapper).getAllAccounts();
//    }
//
//    @Test
//    public void shouldUpdatePasswordIfAccountDontHaveCountryField(){
//        AccountMapper accountMapper = mock(AccountMapper.class);
//        SqlSession sqlSession = mock(SqlSession.class);
//        Account  account = mock(Account.class);
//        UpdateDatabasePassword updateDatabasePassword = new UpdateDatabasePassword(accountMapper, sqlSession);
//        List<Account> accountList = new ArrayList<>();
//        accountList.add(account);
//        when(accountMapper.getAllAccounts()).thenReturn(accountList);
//        when(account.getCountry()).thenReturn(null);
//
//        updateDatabasePassword.updateDatabaseToEncryptPassword();
//        verify(accountMapper).updatePassword(account);
//    }
//
//}