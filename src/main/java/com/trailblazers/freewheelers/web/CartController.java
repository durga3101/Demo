package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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


    @Autowired
    public CartController(ItemService itemService, TaxCalculator taxCalculator) {
        this.itemService = itemService;
        this.taxCalculator = taxCalculator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(@ModelAttribute(ITEM) Item item, HttpServletRequest request, Model model, Principal principal) {

        if (isPrincipalNull(principal)) return REDIRECT_LOGIN;

        HashMap<Item, Long> items = getItemsFromCart(request);

        if (items.isEmpty()) {
            model.addAttribute(EMPTY_CART, true);
        } else {
            model.addAttribute(EMPTY_CART, false);
            model.addAttribute(ITEMS, items);
        }

        return CART;
    }

    private boolean isPrincipalNull(Principal principal) {
        return principal == null;
    }

    private HashMap<Item, Long> getItemsFromCart(HttpServletRequest request) {
        HashMap<Long, Long> cart = (HashMap<Long, Long>) request.getSession().getAttribute(SHOPPING_CART);
        return itemService.getItemHashMap(cart);
    }
}
