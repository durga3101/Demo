package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Component
public class Session {


    private Object items;
    private HttpSession httpSession;
    public static final String ORDER = "order";
    public static final String SHOPPING_CART = "shoppingCart";
    public static final String PURCHASED_ITEMS = "purchasedItems";

    public HashMap getItemHashMap(String attribute, HttpSession httpSession) {
        HashMap<Item, Long> itemHashMap = new HashMap<>();
        try{
            HashMap<Item, Long> itemAttribute = (HashMap<Item, Long>) httpSession.getAttribute(attribute);
            return itemAttribute == null ? itemHashMap : itemAttribute;
        }catch(ClassCastException e){
            return itemHashMap;
        }

    }
}
