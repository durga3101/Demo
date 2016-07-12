package com.trailblazers.freewheelers.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoginControllerTest {

    private Model model;
    private LoginController loginController;

    @Before
    public void setUp() throws Exception {
        model = mock(Model.class);
        this.loginController = new LoginController();
    }

    @Test
    public void shouldReturnLoginWhenLoginIsCalled(){
        assertEquals("login", loginController.login(model));
    }

    @Test
    public void shouldReturnLoginWhenLoginErrorIsCalled(){
        String message = loginController.loginError(model);
        verify(model).addAttribute(anyString(), anyString());
        assertEquals("login", message);
    }

    @Test
    public void shouldReturnLogoutWhenLogoutIsCalled(){
        assertEquals("logout", loginController.logout(model));
    }

    @Test
    public void shouldAccessDeniedWhenAccessDeniedIsCalled(){
        assertEquals("accessDenied", loginController.accessDenied());
    }
}