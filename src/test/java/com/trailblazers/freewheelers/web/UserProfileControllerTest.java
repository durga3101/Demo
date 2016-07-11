package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProfileControllerTest {
    private Model model;
    private Principal principal;
    private HttpServletRequest request;
    private UserProfileController userProfileController;
    private HttpSession httpSession;

    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
        principal = mock(Principal.class);
        request = mock(HttpServletRequest.class);
        userProfileController = new UserProfileController();
        httpSession = mock(HttpSession.class);

    }
    @Test
    public void shouldReturnRedirectToCartStringWhenGetIsCalled() {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("itemForReserve")).thenReturn(mock(Item.class));
        assertEquals("redirect:/cart", userProfileController.get(null,model,principal,request));
    }
}