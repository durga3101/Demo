package com.trailblazers.freewheelers;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.Principal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserNameInterceptorTest {
    @Mock
    HttpServletRequest request;
    @Mock
    AccountService accountService;
    @Mock
    HttpSession httpSession;
    @Mock
    Principal principal;
    @Mock
    Account account;
    @Mock
    HttpServletResponse response;
    @Mock
    Object handler;
    private UserNameInterceptor interceptor;

    @Before
    public void setUp(){
        initMocks(this);
        interceptor = new UserNameInterceptor(accountService);
        when(request.getSession()).thenReturn(httpSession);
        when(accountService.getAccountFromEmail(anyString())).thenReturn(account);
    }

    @Test
    public void shouldNotSetUserNameAsSessionAttributeWhenNoOneLoggedIn() throws Exception {
        when(request.getUserPrincipal()).thenReturn(null);

        interceptor.preHandle(request,response,handler);

        verify(httpSession,never()).setAttribute(anyString(),any());
    }
    @Test
    public void shouldSetUserNameAsSessionAttributeWhenUserLoggedIn() throws Exception {
        when(request.getUserPrincipal()).thenReturn(principal);

        interceptor.preHandle(request,response,handler);

        verify(httpSession).setAttribute("UserName", accountService.getAccountFromEmail(principal.getName()).getAccount_name());
    }

}