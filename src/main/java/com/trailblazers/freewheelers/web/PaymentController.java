package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    ItemService itemService;

    @Autowired
    public PaymentController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, HttpServletRequest request) {
        if(request.getSession().getAttribute("itemOnConfirm") == null){
            return "payment";
        }
        Item item = (Item) request.getSession().getAttribute("itemOnConfirm");
        Item itemOnConfirm = itemService.get(item.getItemId());
        model.addAttribute("totalAmount", itemOnConfirm.getPrice());
        return "payment";
    }

}
