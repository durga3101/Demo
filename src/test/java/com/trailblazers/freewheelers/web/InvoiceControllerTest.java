package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ShippingAddress;
import com.trailblazers.freewheelers.service.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;

import static com.trailblazers.freewheelers.web.Session.ORDER;
import static com.trailblazers.freewheelers.web.Session.PURCHASED_ITEMS;
import static com.trailblazers.freewheelers.web.Session.RESERVATION_TIMESTAMP;
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
    private Date date;

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
        date = mock(Date.class);
        Item first =  mock(Item.class);
        Item second = mock(Item.class);
        items = new HashMap<>();
        items.put(first, 1l);
        items.put(second, 2l);
    }

    @Test
    public void getShouldReturnInvoicePage() throws Exception {
        expected = "invoice";
        Country country= mock(Country.class);
        ShippingAddress shippingAddress = mock(ShippingAddress.class);
        String countryName = "UK";
        BigDecimal ten = createStubs(country,countryName, shippingAddress);
        when(country.getVat_rate()).thenReturn(20.0);
        when(httpSession.getAttribute(RESERVATION_TIMESTAMP)).thenReturn(date);
        when(httpSession.getAttribute(ORDER)).thenReturn(1);
        actual = invoiceController.get(request, model, principal);

        verify(session).getItemHashMap(PURCHASED_ITEMS, httpSession);
        verify(model).addAttribute("userDetails",shippingAddress);
        verify(model).addAttribute("totalVat", ten);
        verify(model).addAttribute("totalDuty",ten);
        verify(model).addAttribute("subTotal",ten.toString());
        verify(model).addAttribute("grossTotal",ten.toString());

        verify(model).addAttribute("items", items);
        verify(model).addAttribute("taxType","VAT");
        verify(model).addAttribute("tax_rate",20.0);
        verify(model).addAttribute(ORDER,1);
        verify(httpSession).getAttribute("country");
        verify(countryService).getByName(countryName);
        verify(model).addAttribute("country",countryName);
        assertEquals(expected, actual);
    }

    @Test
    public void getShouldDisplayDutyForCanada() throws Exception {
        expected = "invoice";
        Country country= mock(Country.class);
        ShippingAddress shippingAddress = mock(ShippingAddress.class);
        String countryName = "Canada";
        BigDecimal ten = createStubs(country,countryName, shippingAddress);
        when(country.getVat_rate()).thenReturn(0.0);
        when(country.getDuty_rate()).thenReturn(20.0);

        actual = invoiceController.get(request, model, principal);
        verify(model).addAttribute("taxType","Duty");
        verify(model).addAttribute("tax_rate",20.0);
        assertEquals(expected, actual);
    }

    private BigDecimal createStubs(Country country, String countryName, ShippingAddress shippingAddress) {
        when(session.getItemHashMap(PURCHASED_ITEMS, httpSession)).thenReturn(items);
        when(request.getSession()).thenReturn(httpSession);
        when(country.getName()).thenReturn(countryName);
        when(httpSession.getAttribute("country")).thenReturn(countryName);
        when(httpSession.getAttribute("shippingAddress")).thenReturn(shippingAddress);
        when(countryService.getByName(countryName)).thenReturn(country);
        BigDecimal ten = new BigDecimal(10.0);
        when(calculator.calculateDuty((BigDecimal)any(),(Country)any())).thenReturn(ten);
        when(calculator.calculateVat((BigDecimal)any(),(Country)any())).thenReturn(ten);
        when(calculator.getGrandTotal((HashMap<Item, Long>) any(),(Country)any())).thenReturn(ten);
        when(calculator.getSubtotalFromCart((HashMap<Item, Long>) any())).thenReturn(ten);
        return ten;
    }
}
