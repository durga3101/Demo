package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.service.impl.ReserveOrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    ItemService itemService = new ItemServiceImpl();
    AccountService accountService = new AccountServiceImpl();
    ReserveOrderService reserveOrderService = new ReserveOrderServiceImpl();

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

    @RequestMapping(value="/fromHome", method = RequestMethod.GET)
    public String getFromHome(Model model, HttpServletRequest request, @ModelAttribute Item item) {
        if(request.getSession().getAttribute("itemOnConfirm") == null){
            return "payment";
        }
        Item itemOnConfirm = itemService.get(item.getItemId());
        model.addAttribute("totalAmount", itemOnConfirm.getPrice());
        return "payment";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void payForItem(Model model, Principal principal, @ModelAttribute Item item){
        model.addAttribute("totalAmount", "10");
//        model.addAttribute("total",item.getPrice().toString());
    }

}
