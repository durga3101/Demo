package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.CountryService;
import com.trailblazers.freewheelers.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/cart")
public class CartController {

    private static final String REDIRECT_LOGIN = "redirect:/login";
    private static final String ITEMS = "items";
    private static final String CART = "cart";
    private static final String EMPTY_CART = "isCartEmpty";
    private static final String ITEM = "item";
    private static final String SHOPPING_CART = "shoppingCart";
    private static final String REDIRECT_CART = "redirect:/cart";

    private ItemService itemService;
    private Calculator calculator;
    private AccountService accountService;
    private CountryService countryService;
    private HttpSession httpSession;
    private Session session;


    @Autowired
    public CartController(ItemService itemService, Calculator calculator, AccountService accountService, CountryService countryService, Session session) {
        this.itemService = itemService;
        this.calculator = calculator;
        this.accountService = accountService;
        this.countryService = countryService;
        this.session = session;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(@ModelAttribute(ITEM) Item item, HttpServletRequest request, Model model, Principal principal) {
        httpSession = request.getSession();

        if (isPrincipalNull(principal)) return REDIRECT_LOGIN;

        HashMap<Item, Long> items = session.getItemHashMap(SHOPPING_CART, httpSession);



        if (items.isEmpty()) {
            model.addAttribute(EMPTY_CART, true);
        } else {
            model.addAttribute(EMPTY_CART, false);
            model.addAttribute(ITEMS, items);
        }

        Account account = accountService.getAccountFromEmail(principal.getName());

        String countryName = account.getCountry();
        if(countryName == null){
            countryName = "UK";
        }
        Country country = countryService.getByName(countryName);
        if(countryName.equals("CANADA")){
            if(calculator.noOfItemsInCart(items)>2) {
                country.setDuty_rate(7.5);
            }
        }
        httpSession.setAttribute("country",countryName);
        setTax(model,country,items);
        return CART;
    }

    private void setTax(Model model, Country country, HashMap<Item, Long> items) {
        BigDecimal subtotal = calculator.getSubtotalFromCart(items);
        BigDecimal vat = calculator.calculateVat(subtotal,country);
        BigDecimal duty = calculator.calculateDuty(subtotal,country);
        BigDecimal grandTotal = calculator.getGrandTotal(items,country);
        model.addAttribute("vatRate",country.getVat_rate());
        model.addAttribute("dutyRate",country.getDuty_rate());
        model.addAttribute("vat",vat);
        model.addAttribute("duty",duty);
        model.addAttribute("subTotal",subtotal.toString());
        model.addAttribute("grandTotal",grandTotal.toString());

        httpSession.setAttribute("GRAND_TOTAL",grandTotal.toString());

    }

    private boolean isPrincipalNull(Principal principal) {
        return principal == null;
    }


    private String decode(String userName) {
        try {
            return URLDecoder.decode(userName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("CATCH CATCH CATCH me if you can");
            return userName;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String delete(@ModelAttribute(ITEM) Item item, HttpServletRequest request) {
        httpSession = request.getSession();
        Long itemId = item.getItemId();
        item = itemService.get(itemId);
        HashMap<Item, Long> shoppingCart = session.getItemHashMap(SHOPPING_CART, httpSession);

        shoppingCart.remove(item);

        return REDIRECT_CART;
    }
}
