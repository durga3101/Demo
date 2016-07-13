package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.apache.http.HttpRequest;
import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomeControllerTest {


    @Test
    public void shouldCallSetAttributeToModelWhenGetIsCalled() {
        ItemServiceImpl service = mock(ItemServiceImpl.class);
        HttpServletRequest httpRequest = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        Item item = mock(Item.class);
        when(httpRequest.getSession()).thenReturn(session);
        when(session.getAttribute("itemForReserve")).thenReturn(true);

        HomeController homeController = new HomeController(service);
        homeController.get(model,item,httpRequest);

        verify(model).addAttribute(Matchers.anyString(),Matchers.anyObject());
    }
}