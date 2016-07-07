package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {

    ItemService itemService = new ItemServiceImpl();

    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "cart";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addToCart(Model model, Principal principal, @ModelAttribute Item item) {
        Item itemToReserve = itemService.get(item.getItemId());

        model.addAttribute("item", itemToReserve);
    }

//    public static final String CART_PAGE = /cart;

    //make a model and view
    //should show shopping cart
}
