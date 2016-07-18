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
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final String OUT_OF_STOCK = "isItemOutOfStock";
    private static final String ADDED_ITEM = "addedItemName";
    private static final String HAS_ITEM_BEEN_ADDED = "hasItemBeenAdded";
    private static final String SHOPPING_CART = "shoppingCart";
    private static final String ITEMS = "items";
    private static final String ITEM = "item";
    private static final String HOME = "home";
    private static final String REDIRECT_LOGIN = "redirect:/login";
    private static final String REDIRECT_HOME = "redirect:/";
    private static final String CAME_FROM_POST = "cameFromPost";
    private ItemService itemService;
    private HttpSession session;

    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, @ModelAttribute(ITEM) Item item, HttpServletRequest request) {
        session = request.getSession();

        if (session.getAttribute(CAME_FROM_POST) == null) hideItemMessages(session);

        setModel(model, session);
        session.setAttribute(CAME_FROM_POST, false);

        return HOME;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@ModelAttribute(ITEM) Item item, HttpServletRequest request, Principal principal) {
        session = request.getSession();
        Long itemId = item.getItemId();
        String itemName = itemService.get(itemId).getName();
        Long quantityInStock = itemService.get(itemId).getQuantity();
        HashMap<Long, Long> shoppingCart = createCartIfNull(session);
        Long quantityInCart = getQuantityInCart(itemId, shoppingCart);

        session.setAttribute(CAME_FROM_POST, true);

        if (quantityInCart > quantityInStock) return cancelItem(session);

        updateCart(itemId, shoppingCart, quantityInCart);
        updateSession(session, itemName, shoppingCart);

        if (isPrincipalNull(principal)) return REDIRECT_LOGIN;

        return REDIRECT_HOME;
    }

    private static void hideItemMessages(HttpSession session) {
        session.setAttribute(OUT_OF_STOCK, false);
        session.setAttribute(ADDED_ITEM, null);
        session.setAttribute(HAS_ITEM_BEEN_ADDED, false);
    }

    private void setModel(Model model, HttpSession session) {
        model.addAttribute(ITEMS, itemService.getItemsWithNonZeroQuantity());
    }

    private void updateSession(HttpSession session, String itemName, HashMap<Long, Long> shoppingCart) {
        session.setAttribute(SHOPPING_CART, shoppingCart);
        session.setAttribute(ADDED_ITEM, itemName);
        session.setAttribute(HAS_ITEM_BEEN_ADDED, true);
        session.setAttribute(OUT_OF_STOCK, false);
    }

    private void updateCart(Long itemId, HashMap<Long, Long> shoppingCart, Long quantityInCart) {
        shoppingCart.remove(itemId);
        shoppingCart.put(itemId, quantityInCart);
    }

    private long getQuantityInCart(Long itemId, HashMap<Long, Long> shoppingCart) {
        return shoppingCart.containsKey(itemId)
                ?
                1L + shoppingCart.get(itemId) : 1L;
    }

    private HashMap<Long, Long> createCartIfNull(HttpSession session) {
        return getCart(this.session) == null
                ?
                new HashMap<Long, Long>() : getCart(this.session);
    }

    private static String cancelItem(HttpSession session) {
        session.setAttribute(OUT_OF_STOCK, true);
        session.setAttribute(HAS_ITEM_BEEN_ADDED, false);
        return REDIRECT_HOME;
    }

    private HashMap<Long, Long> getCart(HttpSession session) {
        return (HashMap<Long, Long>) session.getAttribute(SHOPPING_CART);
    }

    private boolean isPrincipalNull(Principal principal) {
        return principal == null;
    }


}

