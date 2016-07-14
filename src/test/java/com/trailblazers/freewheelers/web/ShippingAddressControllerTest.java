package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.service.ShippingAddressService;
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
    private ShippingAddressService shippingAddressService;


    @Test
    public void shouldReturnShippingAddressPage() throws Exception {
        request = mock(HttpServletRequest.class);
        httpSession =mock(HttpSession.class);
        shippingAddressService=mock(ShippingAddressService.class);
        when(request.getSession()).thenReturn(httpSession);
        Model model = mock(Model.class);
        assertEquals("shippingAddress",new ShippingAddressController(shippingAddressService).get(model, request));
    }

    @Test
    public void shouldRedirectToPaymentPage() throws Exception {
        request = mock(HttpServletRequest.class);
        ShippingAddressController shippingAddressController = new ShippingAddressController(shippingAddressService);
        assertEquals("payment",shippingAddressController.getShippingAddress(request));

    }
}