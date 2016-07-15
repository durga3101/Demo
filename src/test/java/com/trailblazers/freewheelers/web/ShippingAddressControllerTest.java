package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.ShippingAddress;
import com.trailblazers.freewheelers.service.ShippingAddressService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ShippingAddressControllerTest {

    private HttpServletRequest request;
    private HttpSession httpSession;
    private ShippingAddressService shippingAddressService;
    private ShippingAddress shippingAddress;

    @Before
    public void setUp() throws Exception {
        shippingAddressService=mock(ShippingAddressService.class);
        shippingAddress = mock(ShippingAddress.class);
    }

    @Test
    public void shouldReturnShippingAddressPage() throws Exception {
        request = mock(HttpServletRequest.class);
        httpSession =mock(HttpSession.class);
        when(request.getSession()).thenReturn(httpSession);
        Model model = mock(Model.class);
        assertEquals("shippingAddress",new ShippingAddressController(shippingAddressService).get(model, request));
    }

    @Test
    public void shouldRedirectToPaymentPage() throws Exception {
        request = getHttpServletRequest();
        ShippingAddressController shippingAddressController = new ShippingAddressController(shippingAddressService);
        assertEquals("payment",shippingAddressController.getShippingAddress(request));
        ArgumentCaptor<ShippingAddress> captor = ArgumentCaptor.forClass(ShippingAddress.class);
        verify(shippingAddressService).createShippingAddress(captor.capture());
        verify(shippingAddressService).createShippingAddress(any(ShippingAddress.class));

    }
    private HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("street1")).thenReturn("street1");
        when(request.getParameter("street2")).thenReturn("street2");
        when(request.getParameter("city")).thenReturn("city");
        when(request.getParameter("state")).thenReturn("state");
        when(request.getParameter("postcode")).thenReturn("123456789");

        return request;
    }
}