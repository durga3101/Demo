package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class Session {

    private Object items;
    private HttpSession httpSession;

    public Session(HttpSession httpSession) {

        this.httpSession = httpSession;
    }

    public HashMap getItemHashMap(String attribute) {
        HashMap<Item, Long> itemHashMap = new HashMap<>();
        try{
            HashMap<Item, Long> itemAttribute = (HashMap<Item, Long>) httpSession.getAttribute(attribute);
            return itemAttribute == null ? itemHashMap : itemAttribute;
        }catch(ClassCastException e){
            return itemHashMap;
        }

    }
}
