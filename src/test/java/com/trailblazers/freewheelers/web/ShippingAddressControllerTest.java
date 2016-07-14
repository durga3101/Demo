package com.trailblazers.freewheelers.web;

import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShippingAddressControllerTest {

    private HttpServletRequest request;
    private HttpSession httpSession;

    @Test
    public void shouldReturnShippingAddressPage() throws Exception {
        request = mock(HttpServletRequest.class);
        httpSession =mock(HttpSession.class);
        when(request.getSession()).thenReturn(httpSession);
        Model model = mock(Model.class);
        assertEquals("shippingAddress",new ShippingAddressController().get(model, request));
    }

    @Test
    public void shouldReturnSuccess() throws Exception {
        request = mock(HttpServletRequest.class);
        ShippingAddressController shippingAddressController = new ShippingAddressController();
        assertEquals("payment",shippingAddressController.getShippingAddress(request));

    }
}