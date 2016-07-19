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
    private HttpSession httpSession;
    private Session session;

    @Autowired
    public HomeController(ItemService itemService, Session session) {
        this.itemService = itemService;
        this.session = session;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, @ModelAttribute(ITEM) Item item, HttpServletRequest request) {
        httpSession = request.getSession();
        if ( httpSession.getAttribute(CAME_FROM_POST) != null && (boolean) httpSession.getAttribute(CAME_FROM_POST) ==false) hideItemMessages(httpSession);

        setModel(model, httpSession);
        httpSession.setAttribute(CAME_FROM_POST, false);

        return HOME;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@ModelAttribute(ITEM) Item item, HttpServletRequest request, Principal principal) {
        httpSession = request.getSession();
        Long itemId = item.getItemId();
        HashMap<Item, Long> shoppingCart = createCartIfNull(session);

        httpSession.setAttribute(CAME_FROM_POST, true);
        if (quantityInCartExceedsStock(item, shoppingCart)) return cancelItem(httpSession);

        updateCart(item, shoppingCart);
        updateSession(httpSession, item, shoppingCart);

        if (isPrincipalNull(principal)) return REDIRECT_LOGIN;

        return REDIRECT_HOME;
    }

    private boolean quantityInCartExceedsStock(Item item, HashMap<Item, Long> shoppingCart){
        Long quantityInStock = itemService.get(item.getItemId()).getQuantity();
        Long quantityInCart = incrementQuantityInCart(item, shoppingCart);
        if(quantityInCart > quantityInStock) return true;
        else return false;
    }

    private static void hideItemMessages(HttpSession httpSession) {
        httpSession.setAttribute(OUT_OF_STOCK, false);
        httpSession.setAttribute(ADDED_ITEM, null);
        httpSession.setAttribute(HAS_ITEM_BEEN_ADDED, false);
    }

    private void setModel(Model model, HttpSession httpSession) {
        model.addAttribute(ITEMS, itemService.getItemsWithNonZeroQuantity());
    }

    private void updateSession(HttpSession session, Item item, HashMap<Item, Long> shoppingCart) {
        session.setAttribute(SHOPPING_CART, shoppingCart);
        session.setAttribute(ADDED_ITEM, item.getName());
        session.setAttribute(HAS_ITEM_BEEN_ADDED, true);
        session.setAttribute(OUT_OF_STOCK, false);
    }

    private void updateCart(Item item, HashMap<Item, Long> shoppingCart) {
        Long updatedQuantity = incrementQuantityInCart(item, shoppingCart);
        shoppingCart.remove(item);
        shoppingCart.put(item, updatedQuantity);
    }

    private long incrementQuantityInCart(Item item, HashMap<Item, Long> shoppingCart) {
        return shoppingCart.containsKey(item)
                ?
                1L + shoppingCart.get(item) : 1L;
    }

    private HashMap<Item, Long> createCartIfNull(Session session) {
        HashMap<Item, Long> itemHashMap = session.getItemHashMap(SHOPPING_CART, httpSession);
        return itemHashMap == null
                ?
                new HashMap<Item, Long>() : session.getItemHashMap(SHOPPING_CART, httpSession);
    }

    private static String cancelItem(HttpSession httpSession) {
        httpSession.setAttribute(OUT_OF_STOCK, true);
        httpSession.setAttribute(HAS_ITEM_BEEN_ADDED, false);
        return REDIRECT_HOME;
    }

    private boolean isPrincipalNull(Principal principal) {
        return principal == null;
    }


}

