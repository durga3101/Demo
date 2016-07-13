package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReserveControllerTest {
    @Test
    public void shouldAddItemFromSessionToReservedItemsModel() {
        Item item = mock(Item.class);
        HttpSession mockSession = mock(HttpSession.class);
        when(mockSession.getAttribute("itemOnConfirm")).thenReturn(item);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getSession()).thenReturn(mockSession);

        ReserveController reserveController = new ReserveController();

        Model model = new ExtendedModelMap();
        reserveController.get(mockRequest, model);

        assertTrue(model.containsAttribute("item"));
    }

}