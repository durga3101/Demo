package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/payment")
public class PaymentController {
    private static final String SHOPPING_CART = "shoppingCart";

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
//        model.addAttribute("totalAmount", ));
        return "payment";
    }

    public BigDecimal getTotalPriceFromCart(HttpServletRequest request) {
        HashMap<Item, Long> cart = getItemsFromCart(request);
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Item, Long> entry : cart.entrySet()) {
            BigDecimal itemPrice = entry.getKey().getPrice();

            for(int quantity = 0; quantity < entry.getValue(); quantity++){
                totalPrice = totalPrice.add(itemPrice);
            }

        }
        totalPrice = totalPrice.setScale(2, RoundingMode.CEILING);
        return totalPrice;
    }

    private HashMap<Item, Long> getItemsFromCart(HttpServletRequest request) {
        HashMap<Long, Long> cart = (HashMap<Long, Long>) request.getSession().getAttribute(SHOPPING_CART);
        return itemService.getItemHashMap(cart);
    }
}
