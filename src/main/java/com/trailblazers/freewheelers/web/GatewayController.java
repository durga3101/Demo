package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.OrderService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
//import com.trailblazers.freewheelers.service.impl.PaymentRequestBuilderServiceImpl;
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
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/gateway")
public class GatewayController {

    static final String SHOPPING_CART = "shoppingCart";
    static final String PURCHASED_ITEMS = "purchasedItems";
    public static final String ORDER_ID = "order_id";


    private OrderService orderService;
    private final ReserveOrderService reserveOrderService;
    private final AccountService accountService;
    private ItemService itemService;
    private Session session;
    private GatewayClient client;
    private Date rightNow;

    @Autowired
    public GatewayController(OrderService orderService, ReserveOrderService reserveOrderService, AccountService accountService, RestTemplate restTemplate, ItemServiceImpl itemService, PaymentRequestBuilderServiceImpl paymentBuilder, Session session) {
        this.orderService = orderService;
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

        rightNow = new Date();

        String userName = principal.getName();
        Account account =  accountService.getAccountIdByName(userName);

        orderService.createOrder(account);

        //save in db table
        for (Map.Entry<Item, Long> entry : purchasedItems.entrySet()) {
            Item item = entry.getKey();
            for(int quantity = 0; quantity < entry.getValue(); quantity++){
                    saveReservedOrderToDatabase(principal, item, account);
                    decreasePurchasedItemQuantityByOne(item);
            }
        }

        return "redirect:/reserve";
    }

    private void saveReservedOrderToDatabase(Principal principal, Item itemToReserve, Account account) {
        ReserveOrder reserveOrder = new ReserveOrder(account.getAccount_id(), itemToReserve.getItemId(), rightNow);
        reserveOrderService.save(reserveOrder);
    }

    private void decreasePurchasedItemQuantityByOne(Item itemToReserve) {
        itemService.decreaseQuantityByOne(itemToReserve);
    }

}
