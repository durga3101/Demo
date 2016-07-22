package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashMap;

import static com.trailblazers.freewheelers.web.Session.PURCHASED_ITEMS;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    private static final String INVOICE = "invoice";
    private Session session;
    private CountryService countryService;
    private Calculator calculator;

    @Autowired
    public InvoiceController(Session session, CountryService countryService, Calculator calculator) {
        this.session = session;
        this.countryService = countryService;
        this.calculator = calculator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(HttpServletRequest request, Model model, Principal principal) {
        HttpSession httpSession = request.getSession();
        HashMap<Item, Long> items = session.getItemHashMap(PURCHASED_ITEMS, httpSession);
        String countryName = (String) httpSession.getAttribute("country");
        Country country = countryService.getByName(countryName);
        setModels(model, items, country);
        return INVOICE;
    }

    private void setModels(Model model, HashMap<Item, Long> items, Country country) {
        BigDecimal subtotal = calculator.getSubtotalFromCart(items);
        BigDecimal vat = calculator.calculateVat(subtotal,country);
        BigDecimal duty = calculator.calculateDuty(subtotal,country);
        BigDecimal grandTotal = calculator.getGrandTotal(items,country);

        model.addAttribute("totalVat",vat);
        model.addAttribute("totalDuty",duty);
        model.addAttribute("subTotal",subtotal.toString());
        model.addAttribute("grandTotal",grandTotal.toString());

        model.addAttribute("vat_rate",country.getVat_rate());
        model.addAttribute("duty_rate",country.getDuty_rate());
        model.addAttribute("items", items);
    }
}
