package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.utilities.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        if(request.getHeader("Referer") == null){
            return "redirect:/";
        }
        HttpSession httpSession = request.getSession();
        String grandTotal = (String) httpSession.getAttribute("GRAND_TOTAL");
        model.addAttribute("grandTotal", grandTotal);
        return "payment";
    }

}
