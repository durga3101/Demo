package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashMap;

import static com.trailblazers.freewheelers.web.Session.PURCHASED_ITEMS;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class InvoiceControllerTest {

    private InvoiceController invoiceController;
    private String expected;
    private String actual;
    private HttpServletRequest request;
    private HttpSession httpSession;
    private Model model;
    private Principal principal;
    private Session session;
    private HashMap<Item, Long> items;
    private CountryService countryService;
    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        countryService = mock(CountryService.class);
        calculator = mock(Calculator.class);
        invoiceController = new InvoiceController(session, countryService,calculator);
        model = mock(Model.class);
        httpSession = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        principal = mock(Principal.class);
        Item first =  mock(Item.class);
        Item second = mock(Item.class);
        items = new HashMap<>();
        items.put(first, 1l);
        items.put(second, 2l);
    }

    @Test
    public void getShouldReturnInvoicePage() throws Exception {
        expected = "invoice";

        when(session.getItemHashMap(PURCHASED_ITEMS, httpSession)).thenReturn(items);
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("country")).thenReturn("UK");
        when(countryService.getByName("UK")).thenReturn(mock(Country.class));
        when(calculator.calculateDuty((BigDecimal)any(),(Country)any())).thenReturn(new BigDecimal(10.0));
        when(calculator.calculateVat((BigDecimal)any(),(Country)any())).thenReturn(new BigDecimal(10.0));
        when(calculator.getGrandTotal((HashMap<Item, Long>) any(),(Country)any())).thenReturn(new BigDecimal(10.0));
        when(calculator.getSubtotalFromCart((HashMap<Item, Long>) any())).thenReturn(new BigDecimal(10.0));


        actual = invoiceController.get(request, model, principal);

        verify(session).getItemHashMap(PURCHASED_ITEMS, httpSession);
        verify(model).addAttribute("items", items);
        verify(model).addAttribute("vat_rate",0.0);
        verify(model).addAttribute("duty_rate",0.0);
        verify(httpSession).getAttribute("country");
        verify(countryService).getByName("UK");
        assertEquals(expected, actual);
    }
}
