package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.utilities.Calculator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class PaymentControllerTest {
    private HttpSession httpSession;
    private HttpServletRequest request;
    private Model model;
    private PaymentController controller;
    private ItemService service;
    private String expected;
    private Calculator calculator;
    private Session session;


    @Before
    public void setUp() throws Exception {
        service = mock(ItemServiceImpl.class);
        calculator = mock(Calculator.class);
        session = mock(Session.class);
        controller = new PaymentController(service, calculator, session);
        request = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        model = mock(Model.class);
        when(request.getSession()).thenReturn(httpSession);

    }

    @Test
    public void shouldRedirectToHomeWhenTryToAccessPaymentPageFromURL() throws Exception {
        when(request.getHeader("Referer")).thenReturn(null);

        String actual = controller.get(model,request);
        expected = "redirect:/";
        assertEquals(expected, actual);

    }

    @Test
    public void shouldReturnPaymentAfterCreatingItemModelWhenItemInCart() throws Exception {
        when(request.getHeader("Referer")).thenReturn(anyString());
        expected = "payment";
        String actual = controller.get(model,request);

        assertEquals(expected, actual);

    }

    @Test
    public void shouldAddSubtotalToModel() {
        String grandTotal = "200.00";
        when(request.getHeader("Referer")).thenReturn(anyString());
        when(httpSession.getAttribute("GRAND_TOTAL")).thenReturn(grandTotal);

        controller.get(model, request);

        verify(model).addAttribute("grandTotal", grandTotal);
    }

}