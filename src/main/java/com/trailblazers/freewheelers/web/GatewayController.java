package com.trailblazers.freewheelers.web;


import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.impl.PaymentRequestBuilderServiceImpl;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.web.forms.SurveyEntryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/gateway")
public class GatewayController {

    private String url = "http://ops.freewheelers.bike:5000/authorise";
    static final String SHOPPING_CART = "shoppingCart";
    static final String PURCHASED_ITEMS = "purchasedItems";


    private final ReserveOrderService reserveOrderService;
    private final AccountService accountService;
    private RestTemplate restTemplate;
    private ItemService itemService;
    private PaymentRequestBuilderServiceImpl paymentBuilder;


    @Autowired
    public GatewayController(ReserveOrderService reserveOrderService, AccountService accountService, RestTemplate restTemplate, ItemServiceImpl itemService, PaymentRequestBuilderServiceImpl paymentBuilder) {
        this.reserveOrderService = reserveOrderService;
        this.accountService = accountService;
        this.restTemplate = restTemplate;
        this.itemService = itemService;
        this.paymentBuilder = paymentBuilder;
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

        HttpEntity<String> request = createRequest(cc_number,csc,expiry_month,expiry_year, amount);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        String responseString = response + "";

        if (!responseString.contains("SUCCESS")) return "redirect:/gateway/reserve-error";
        HttpSession session = servletRequest.getSession();

        HashMap<Long, Long> purchasedItemsFromShoppingCart = (HashMap) session.getAttribute(SHOPPING_CART);

        HashMap<Item, Long> items = itemService.getItemHashMap(servletRequest);
        session.setAttribute(PURCHASED_ITEMS, purchasedItemsFromShoppingCart);
        session.setAttribute(SHOPPING_CART, null);


        for (Map.Entry<Item, Long> entry : items.entrySet()) {
            Item item = entry.getKey();
            for(int quantity = 0; quantity < entry.getValue(); quantity++){
                saveReservedOrderToDatabase(principal, item);
                decreasePurchasedItemQuantityByOne(item);
            }
        }

        return "redirect:/reserve";
    }

    @RequestMapping(value = "reserve-error", method = RequestMethod.GET)
    public String get() {
        return "reserve-error";
    }

    private void saveReservedOrderToDatabase(Principal principal, Item itemToReserve) {
        String userName = principal.getName();
        Account account =  accountService.getAccountIdByName(userName);
        Date rightNow = new Date();
        ReserveOrder reserveOrder = new ReserveOrder(account.getAccount_id(), itemToReserve.getItemId(), rightNow );
        reserveOrderService.save(reserveOrder);
    }

    private void decreasePurchasedItemQuantityByOne( Item itemToReserve) {

        itemService.decreaseQuantityByOne(itemToReserve);
    }

    private HttpEntity<String> createRequest(String cc_number, String csc, String expiry_month, String expiry_year, String amount) {
        String body = paymentBuilder.buildXMLRequestBody(cc_number, csc, expiry_month, expiry_year, amount);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        return new HttpEntity<>(body, headers);
    }

}
