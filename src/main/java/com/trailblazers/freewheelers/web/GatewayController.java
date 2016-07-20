package com.trailblazers.freewheelers.web;


import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.trailblazers.freewheelers.web.Session.SHOPPING_CART;

@Controller
@RequestMapping("/gateway")
public class GatewayController {

    static final String SHOPPING_CART = "shoppingCart";
    static final String PURCHASED_ITEMS = "purchasedItems";
    private final ReserveOrderService reserveOrderService;
    private final AccountService accountService;
    private ItemService itemService;
    private Session session;
    private LiveGatewayClient client;

    @Autowired
    public GatewayController(ReserveOrderService reserveOrderService, AccountService accountService, ItemServiceImpl itemService, LiveGatewayClient client, Session session) {
        this.reserveOrderService = reserveOrderService;
        this.accountService = accountService;
        this.itemService = itemService;
        this.session = session;
        this.client = client;
    }

    @RequestMapping(value = "reserve-error", method = RequestMethod.GET)
    public String get() {
        return "reserve-error";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String post(HttpServletRequest servletRequest,
                       Principal principal,
                       @RequestParam(value = "card_number", required = true) String cc_number,
                       @RequestParam(value = "card_ccv", required = true) String csc,
                       @RequestParam(value = "expiry_month", required = true) String expiry_month,
                       @RequestParam(value = "expiry_year", required = true) String expiry_year,
                       @RequestParam(value = "amount", required = true) String amount
    ) {

        String response = client.paymentRequest(cc_number, csc, expiry_month, expiry_year, amount) + "";

        if (!response.contains("SUCCESS")) return "redirect:/gateway/reserve-error";

        HttpSession httpSession = servletRequest.getSession();
        HashMap<Item, Long> purchasedItems = session.getItemHashMap(SHOPPING_CART, httpSession);
        httpSession.setAttribute(PURCHASED_ITEMS, purchasedItems);
        httpSession.setAttribute(SHOPPING_CART, null);

        for (Map.Entry<Item, Long> entry : purchasedItems.entrySet()) {
            Item item = entry.getKey();
            for (int quantity = 0; quantity < entry.getValue(); quantity++) {
                saveReservedOrderToDatabase(principal, item);
                decreasePurchasedItemQuantityByOne(item);
            }
        }

        return "redirect:/reserve";
    }

    private void saveReservedOrderToDatabase(Principal principal, Item itemToReserve) {
        String userName = principal.getName();
        Account account = accountService.getAccountIdByName(userName);
        Date rightNow = new Date();
        ReserveOrder reserveOrder = new ReserveOrder(account.getAccount_id(), itemToReserve.getItemId(), rightNow);
        reserveOrderService.save(reserveOrder);
    }

    private void decreasePurchasedItemQuantityByOne(Item itemToReserve) {
        itemService.decreaseQuantityByOne(itemToReserve);
    }

}
