package com.trailblazers.freewheelers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(ReserveController.URL)
public class ReserveController {

    static final String URL = "/reserve";


    @RequestMapping(method = RequestMethod.GET)
    public void get(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("item", httpServletRequest.getSession().getAttribute("purchasedItem"));
        httpServletRequest.getSession().setAttribute("purchasedItem", null);
    }

}
