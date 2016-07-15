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

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(sqlSession.getMapper(AccountMapper.class)).thenReturn(accountMapper);
        when(sqlSession.getMapper(AccountRoleMapper.class)).thenReturn(accountRoleMapper);

        accountService = new AccountServiceImpl(sqlSession);
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
    public void shouldReturnRoleForGivenUser() throws Exception {
        String user = "abc";
        when(accountRoleMapper.get(user)).thenReturn(mock(AccountRole.class));
        accountService.getRole(user);
        verify(accountRoleMapper, times(1)).get(user);
    }

    private Account getAccountWithoutErrors() {
        return new Account("example", true, "example@example.com", "1234567890", "India", "Example Person");
    }

    @Test
    public void shouldNotCreateAccountWhenUsernameInUse() {
        Account account = getAccountWithoutErrors();

        when(accountMapper.getUsernameCount(anyString())).thenReturn(1);

        accountService.createAccount(account);

        verify(accountMapper, never()).insert(account);
        verify(sqlSession, never()).commit();
    }
}
