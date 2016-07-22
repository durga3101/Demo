package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ReserveControllerTest {

    private Session session;
    private ReserveController reserveController;
    private HttpServletRequest httpServletRequest;
    private Model model;
    private Principal principal;
    private HttpSession httpSession;

    @Before
    public void setUp() throws Exception {
        session = new Session();
        httpServletRequest = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        model = mock(Model.class);
        principal = mock(Principal.class);

        reserveController = new ReserveController(session);

    }

    @Test
    public void shouldContainOrderIdInSessionWhenGetRequest() throws Exception {
        HashMap<Item, Long> itemLongHashMap = new HashMap<>();
        when(session.getItemHashMap(anyString(),httpSession)).thenReturn(itemLongHashMap);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("order_id")).thenReturn(1l);

        reserveController.get(httpServletRequest, model, principal, session);

        verify(httpSession).getAttribute("order_id");
    }

}