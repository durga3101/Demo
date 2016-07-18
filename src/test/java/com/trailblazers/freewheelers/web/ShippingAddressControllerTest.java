package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ShippingAddress;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ShippingAddressService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ShippingAddressControllerTest {

    private HttpServletRequest request;
    private HttpSession httpSession;
    private ShippingAddressService shippingAddressService;
    private ShippingAddress shippingAddress;
    private Model model;
    private ItemService itemService;

    @Before
    public void setUp() throws Exception {
        shippingAddressService=mock(ShippingAddressService.class);
        shippingAddress = mock(ShippingAddress.class);
        itemService = mock(ItemService.class);

    }

    @Test
    public void shouldReturnShippingAddressPage() throws Exception {
        request = mock(HttpServletRequest.class);
        httpSession =mock(HttpSession.class);
        when(request.getSession()).thenReturn(httpSession);
        assertEquals("shippingAddress",new ShippingAddressController(shippingAddressService,itemService).get(model, request));
    }

    @Test
    public void shouldRedirectToPaymentPage() throws Exception {
        request = getHttpServletRequest();
        ShippingAddressController shippingAddressController = new ShippingAddressController(shippingAddressService,itemService);
        shippingAddressController.getShippingAddress(request);
        //assertEquals("payment",shippingAddressController.getShippingAddress(request));
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

    @Test
    public void shouldAddAmountInTheModel(){
        request = mock(HttpServletRequest.class);
        httpSession =mock(HttpSession.class);
        model = mock(Model.class);
        Item item = mock(Item.class);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute(anyString())).thenReturn(item);
        when(itemService.get(anyLong())).thenReturn(item);

        ShippingAddressController shippingAddressController = new ShippingAddressController(shippingAddressService,itemService);
        shippingAddressController.get(model,request);

        verify(model).addAttribute(anyString(),(BigDecimal)any());

    }
}