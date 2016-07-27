package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.*;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.OrderService;
import com.trailblazers.freewheelers.service.PurchasedItemService;
import com.trailblazers.freewheelers.service.*;
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
import java.util.Map;

import static com.trailblazers.freewheelers.FeatureToggles.ORDER_ID_CONNECT_FEATURE;
import static com.trailblazers.freewheelers.web.Session.ORDER;
import static com.trailblazers.freewheelers.web.Session.RESERVATION_TIMESTAMP;


@Controller
@RequestMapping("/gateway")
public class GatewayController {

    static final String SHOPPING_CART = "shoppingCart";
    static final String PURCHASED_ITEMS = "purchasedItems";



    private OrderService orderService;
    private final PurchasedItemService purchasedItemService;
    private final AccountService accountService;
    private ItemService itemService;
    private Session session;
    private GatewayClient client;
    private ShippingAddressService shippingAddressService;
    private OrderedItemService orderedItemService;
    private Date rightNow;

    @Autowired

    public GatewayController(OrderService orderService,
                             PurchasedItemService purchasedItemService,
                             AccountService accountService,
                             ItemServiceImpl itemService,
                             GatewayClient client,
                             Session session,
                             ShippingAddressService shippingAddressService,
                             OrderedItemService orderedItemService) {
        this.orderService = orderService;
        this.purchasedItemService = purchasedItemService;
        this.accountService = accountService;
        this.itemService = itemService;
        this.session = session;
        this.client = client;
        this.shippingAddressService = shippingAddressService;
        this.orderedItemService = orderedItemService;
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

        String userEmail = principal.getName();
        Account account =  accountService.getAccountFromEmail(userEmail);
        Order order = orderService.createOrder(account);
        Long orderId = order.getOrder_id();
        Date date =  order.getReservation_timestamp();

        httpSession.setAttribute(RESERVATION_TIMESTAMP,date.toString());
        httpSession.setAttribute(ORDER, orderId);

        saveAddressToDatabase(httpSession);

        for (Map.Entry<Item, Long> entry : purchasedItems.entrySet()) {
            Item item = entry.getKey();
            if (ORDER_ID_CONNECT_FEATURE) {
                Long quantity = entry.getValue();
                saveReservedOrderToDatabase(order, item, quantity);
            }

            for(int quantity = 0; quantity < entry.getValue(); quantity++){
                if(!ORDER_ID_CONNECT_FEATURE) {
                    saveReservedOrderToDatabase(principal, item);
                }
                    decreasePurchasedItemQuantityByOne(item);
            }
        }

        return "redirect:/reserve";
    }

    private void saveAddressToDatabase(HttpSession httpSession) {
        ShippingAddress shippingAddress = (ShippingAddress) httpSession.getAttribute("shippingAddress");
        shippingAddressService.createShippingAddress(shippingAddress);
    }

    private void saveReservedOrderToDatabase(Principal principal, Item itemToReserve) {
        Account account = accountService.getAccountFromEmail(principal.getName());
        PurchasedItem purchasedItem = new PurchasedItem(account.getAccount_id(), itemToReserve.getItemId(), rightNow);
        purchasedItemService.save(purchasedItem);
    }

    private void saveReservedOrderToDatabase(Order order, Item itemToReserve, Long quantity) {
        OrderedItem orderedItem = new OrderedItem(order.getOrder_id(), itemToReserve.getItemId(), quantity);
        orderedItemService.save(orderedItem);
    }

    private void decreasePurchasedItemQuantityByOne(Item itemToReserve) {
        itemService.decreaseQuantityByOne(itemToReserve);
    }

}
