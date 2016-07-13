package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {

    static boolean cameFromHome = false;

    ItemService itemService = new ItemServiceImpl();
    @RequestMapping(method = RequestMethod.GET)
    public String get(HttpServletRequest request,Model model, Principal principal)
    {
        if(principal == null){
            return "redirect:/login";
        }
        Item item = (Item)request.getSession().getAttribute("itemForReserve");
        Item itemToReserve = itemService.get(item.getItemId());

        model.addAttribute("item", itemToReserve);
        request.getSession().setAttribute("itemForReserve", null);
        request.getSession().setAttribute("itemOnConfirm", item);
        if(cameFromHome) return "redirect:/payment";
        else return "cart";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToCart(HttpServletRequest request,Model model, Principal principal, @ModelAttribute Item item) {
        if(principal == null){
            request.getSession().setAttribute("itemForReserve", item);
            return "redirect:/login";
        }
        Item itemToReserve;

        if(item == null){
            item = (Item)request.getSession().getAttribute("itemForReserve");
        }

        itemToReserve = itemService.get(item.getItemId());
        model.addAttribute("item", itemToReserve);
        request.getSession().setAttribute("itemOnConfirm", item);

        return "cart";
    }

    @RequestMapping(value="/skipCart", method = RequestMethod.POST)
    public String addToCartFromHome(HttpServletRequest request,Model model, Principal principal, @ModelAttribute Item item) {
        cameFromHome = true;
        if(principal == null){
            request.getSession().setAttribute("itemForReserve", item);
            return "redirect:/login";
        }
        Item itemToReserve;

        if(item == null){
            item = (Item)request.getSession().getAttribute("itemForReserve");
        }

        itemToReserve = itemService.get(item.getItemId());
        model.addAttribute("item", itemToReserve);
        request.getSession().setAttribute("itemForReserve", null);
        request.getSession().setAttribute("itemOnConfirm", item);

        return "redirect:/payment";
    }
}
