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

    private ItemService itemService;

    @Autowired
    public CartController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(@ModelAttribute("item") Item item, HttpServletRequest request, Model model, Principal principal) {

        if (isPrincipalNull(principal)) return "redirect:/login";

        HashMap<Item, Long> items = getItemsFromCart(request);
        model.addAttribute("items", items);

        return "cart";
    }

    private boolean isPrincipalNull(Principal principal) {
        return principal == null;
    }

    private HashMap<Item, Long> getItemsFromCart(HttpServletRequest request) {
        HashMap<Long, Long> cart = (HashMap<Long, Long>) request.getSession().getAttribute("shoppingCart");
        return itemService.getItemHashMap(cart);
    }
}
