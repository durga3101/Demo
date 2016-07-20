package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static com.trailblazers.freewheelers.web.Session.SHOPPING_CART;


@Controller
@RequestMapping("/payment")
public class PaymentController {

    ItemService itemService;
    Calculator calculator;
    Session session;

    @Autowired
    public PaymentController(ItemService itemService, Calculator calculator, Session session) {
        this.itemService = itemService;
        this.calculator = calculator;
        this.session = session;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        HashMap<Item, Long> cart = session.getItemHashMap(SHOPPING_CART, httpSession);
        if(cart.isEmpty()){
            return "redirect:/";
        }
        String grandTotal = (String) request.getSession().getAttribute("GRAND_TOTAL");
        model.addAttribute("grandTotal", grandTotal);
        return "payment";
    }

}
