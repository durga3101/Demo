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

    private ItemService itemService;
    private TaxCalculator taxCalculator;
    private AccountService accountService;
    private CountryService countryService;


    @Autowired
    public CartController(ItemService itemService, TaxCalculator taxCalculator, AccountService accountService, CountryService countryService) {
        this.itemService = itemService;
        this.taxCalculator = taxCalculator;
        this.accountService = accountService;
        this.countryService = countryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(@ModelAttribute(ITEM) Item item, HttpServletRequest request, Model model, Principal principal) {

        if (isPrincipalNull(principal)) return REDIRECT_LOGIN;

        HashMap<Item, Long> items = getItemsFromCart(request);
        Account account = accountService.getAccountIdByName(decode(principal.getName()));

        Country country = countryService.getByName(account.getCountry());

        //temporary country.
        Country country1 = new Country();
        country1.setCountry_name("UK");
        country1.setVat_rate(20.0);


        BigDecimal vat = taxCalculator.calculateVat(new BigDecimal(50),country1 );

        setVat(model,vat.toString());

        if (items.isEmpty()) {
            model.addAttribute(EMPTY_CART, true);
        } else {
            model.addAttribute(EMPTY_CART, false);
            model.addAttribute(ITEMS, items);
        }

        return CART;
    }

    private void setVat(Model model, @ModelAttribute  String attributeValue) {
        model.addAttribute("vat",attributeValue);

    }

    private boolean isPrincipalNull(Principal principal) {
        return principal == null;
    }

    private HashMap<Item, Long> getItemsFromCart(HttpServletRequest request) {
        HashMap<Long, Long> cart = (HashMap<Long, Long>) request.getSession().getAttribute(SHOPPING_CART);
        return itemService.getItemHashMap(cart);
    }

    private String decode(String userName) {
        try {
            return URLDecoder.decode(userName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("CATCH CATCH CATCH me if you can");
            return userName;
        }
    }
}
