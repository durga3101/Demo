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

@Controller
@RequestMapping("/cart")
public class CartController {

    static boolean cameFromHome = false;

    private static final String ITEM_FOR_RESERVE = "itemForReserve";
    private static final String ITEM_ON_CONFIRM = "itemOnConfirm";
    ItemService itemService;

    @Autowired
    public CartController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(HttpServletRequest request, Model model, Principal principal) {

        if(cameFromHome) return "redirect:/payment";
        else return "cart";

        // Uncomment code below when ready to use cart user flow

//        if (isPrincipalNull(principal)) return "redirect:/login";
//
//        Item item = (Item) request.getSession().getAttribute(ITEM_FOR_RESERVE);
//        setModel(request, model, item);
//
//        setItemAttribute(request, null, ITEM_FOR_RESERVE);
//        setItemAttribute(request, item, ITEM_ON_CONFIRM);
//
//        return "cart";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(HttpServletRequest request, Model model, Principal principal, @ModelAttribute Item item) {
        if (isPrincipalNull(principal)) {
            setItemAttribute(request, item, ITEM_FOR_RESERVE);
            return "redirect:/login";
        }

        item = getItemFromSession(request, item);
        setModel(request, model, item);
        setItemAttribute(request, item, ITEM_ON_CONFIRM);

        return "cart";
    }

    private Item getItemFromSession(HttpServletRequest request, @ModelAttribute Item item) {
        if (item == null) {
            item = (Item) request.getSession().getAttribute(ITEM_FOR_RESERVE);
        }
        return item;
    }

    private boolean isPrincipalNull(Principal principal) {
        return principal == null;
    }

    private void setModel(HttpServletRequest request, Model model, @ModelAttribute Item item) {
        Item itemToReserve = itemService.get(item.getItemId());
        model.addAttribute("item", itemToReserve);
    }

    private void setItemAttribute(HttpServletRequest request, @ModelAttribute Item item, String itemAttribute) {
        request.getSession().setAttribute(itemAttribute, item);
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
