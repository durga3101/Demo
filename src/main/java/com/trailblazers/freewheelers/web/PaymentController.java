package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/payment")
public class PaymentController {
    private static final String SHOPPING_CART = "shoppingCart";

    ItemService itemService;
    Calculator calculator;

    @Autowired
    public PaymentController(ItemService itemService, Calculator calculator) {
        this.itemService = itemService;
        this.calculator = calculator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, HttpServletRequest request) {
        HashMap<Item, Long> cart = itemService.getItemHashMap(request);
        if(cart.isEmpty()){
            return "redirect:/";
        }
        BigDecimal subtotal = calculator.getSubtotalFromCart(cart);

        model.addAttribute("subtotal", subtotal);
        return "payment";
    }

}
