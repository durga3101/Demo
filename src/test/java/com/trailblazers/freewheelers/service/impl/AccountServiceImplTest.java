package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.AccountRoleMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountRole;
import com.trailblazers.freewheelers.service.AccountService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountServiceImplTest {

    AccountService accountService;

    @Mock
    SqlSession sqlSession;
    @Mock
    AccountMapper accountMapper;
    @Mock
    AccountRoleMapper accountRoleMapper;
    @Mock
    Account account;

    private Account getAccountWithCountryErrors(String error) {
        return new Account("example", true, "example@example.com", "1234567890", error, "Example Person");
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(sqlSession.getMapper(AccountMapper.class)).thenReturn(accountMapper);
        when(sqlSession.getMapper(AccountRoleMapper.class)).thenReturn(accountRoleMapper);

        accountService = new AccountServiceImpl(sqlSession);
    }

    private Account getAccountWithoutErrors() {
        return new Account("example", true, "example@example.com", "1234567890", "UK", "Example Person");
    }

    @Test
    public void shouldCreateAccountWhenThereAreNoValidationErrors() {
        Account account = getAccountWithoutErrors();

        accountService.createAccount(account);

        verify(accountMapper, times(1)).insert(account);
        verify(accountRoleMapper, times(1)).insert(any(AccountRole.class));
        verify(sqlSession, times(1)).commit();
    }

    @Test
    public void shouldNotCreateAccountWhenCountryIsIndia() {
        Account account = getAccountWithCountryErrors("India");

        accountService.createAccount(account);

        verify(accountMapper, times(0)).insert(account);
    }

    @Test
    public void shouldNotCreateAccountWhenCountryIsBlank() {
        Account account = getAccountWithCountryErrors("");

        accountService.createAccount(account);

        verify(accountMapper, times(0)).insert(account);
    }

    @Test
    public void shouldReturnRoleForGivenUser() throws Exception {
        String user = "abc";
        when(accountRoleMapper.get(user)).thenReturn(mock(AccountRole.class));
        accountService.getRole(user);
        verify(accountRoleMapper, times(1)).get(user);
    }

    @Test
    public void shouldGetAccountIfEmailIsValid(){
        String validEmail = "one@two.com";
        accountService.getAccountFromEmail(validEmail);
        verify(accountMapper).getFromEmail(validEmail);
    }
    @Test
    public void shouldGetNullAccountIfEmailIsInvalid(){
        String validEmail = "one@two.com";
        when(accountMapper.getFromEmail(anyString())).thenReturn(null);
        Account expected = accountService.getAccountFromEmail(validEmail);
        assertEquals(expected,null);
    }


}
