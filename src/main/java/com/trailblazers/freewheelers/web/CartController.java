package com.trailblazers.freewheelers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cart")
public class CartController {

    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "shoppingCart";
    }
//    public static final String CART_PAGE = /cart;

    //make a model and view
    //should show shopping cart
}
