package com.trailblazers.freewheelers.web.middleware;

import com.trailblazers.freewheelers.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;


public class UserNameInterceptor extends HandlerInterceptorAdapter {

    private static final String IS_LOGGED_IN = "isLoggedIn";
    AccountService accountService;

    @Autowired
    public UserNameInterceptor(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {

        Principal principal = request.getUserPrincipal();
        HttpSession httpSession = request.getSession();
        if (principal != null) {
            httpSession.setAttribute("UserName", accountService.getAccountFromEmail(principal.getName()).getAccount_name());
            httpSession.setAttribute(IS_LOGGED_IN, true);
        }
        else{
            httpSession.setAttribute(IS_LOGGED_IN,false);
        }
        return true;
    }
}
