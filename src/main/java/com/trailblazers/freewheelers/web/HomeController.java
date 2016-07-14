package com.trailblazers.freewheelers.web;

//import com.trailblazers.freewheelers.UpdateDatabasePassword;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class HomeController {

    public static final String SHOPPING_CART = "shoppingCart";
    private ItemService itemService;
    private HttpSession session;
    private HashMap<Long, Long> emptyShoppingCart;

    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, @ModelAttribute("item") Item item, HttpServletRequest request) {
        session = request.getSession();
        if (session.getAttribute("itemForReserve") != null) {
            session.setAttribute("itemForReserve", null);
        }
        model.addAttribute("items", itemService.getItemsWithNonZeroQuantity());
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@ModelAttribute("item") Item item, HttpServletRequest request, Principal principal) {
        session = request.getSession();

        if (session.getAttribute(SHOPPING_CART) == null) {
            emptyShoppingCart = new HashMap<>();
            session.setAttribute(SHOPPING_CART, emptyShoppingCart);
        }

        HashMap<Long, Long> shoppingCart = (HashMap<Long, Long>) session.getAttribute(SHOPPING_CART);

        Long quantity = 1L;

        if (shoppingCart.containsKey(item.getItemId())) {
            quantity += shoppingCart.remove(item.getItemId());
        }

        shoppingCart.put(item.getItemId(), quantity);
        session.setAttribute(SHOPPING_CART, shoppingCart);

        if (isPrincipalNull(principal)) { return "redirect:/login"; }

        return "redirect:/";
    }

    private boolean isPrincipalNull(Principal principal) {
        return principal == null;
    }


}

