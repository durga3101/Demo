package com.trailblazers.freewheelers.web;


import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.impl.PaymentRequestBuilderServiceImpl;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/gateway")
public class GatewayController {

    private String url = "http://ops.freewheelers.bike:5000/authorise";

    private RestTemplate restTemplate;
    private ItemService itemService;
    private PaymentRequestBuilderServiceImpl paymentBuilder;

    @Autowired
    public GatewayController(RestTemplate restTemplate, ItemServiceImpl itemService, PaymentRequestBuilderServiceImpl paymentBuilder) {
        this.restTemplate = restTemplate;
        this.itemService = itemService;
        this.paymentBuilder = paymentBuilder;
    }
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String post(HttpServletRequest servletRequest,
                       @RequestParam(value = "card_number", required = true) String cc_number,
                       @RequestParam(value = "card_ccv", required = true) String csc,
                       @RequestParam(value = "expiry_month", required = true) String expiry_month,
                       @RequestParam(value = "expiry_year", required = true) String expiry_year,
                       @RequestParam(value = "amount", required = true) String amount
    ) {

        HttpEntity<String> request = createRequest(cc_number,csc,expiry_month,expiry_year, amount);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        String responseString = response + "";

        if (responseString.contains("SUCCESS")) {
            decreasePurchasedItemQuantityByOne(servletRequest);
            return "redirect:/reserve";
        }

        return "redirect:/gateway/reserve-error";
    }

    @RequestMapping(value = "reserve-error", method = RequestMethod.GET)
    public String get() {
        return "reserve-error";
    }

    private void decreasePurchasedItemQuantityByOne(HttpServletRequest servletRequest) {
        Item item = (Item) servletRequest.getSession().getAttribute("itemOnConfirm");
        Item itemToReserve = itemService.get(item.getItemId());
        itemService.decreaseQuantityByOne(itemToReserve);
        servletRequest.getSession().setAttribute("itemOnConfirm", null);
    }

    private HttpEntity<String> createRequest(String cc_number, String csc, String expiry_month, String expiry_year, String amount) {
        String body = paymentBuilder.buildXMLRequestBody(cc_number, csc, expiry_month, expiry_year, amount);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        return new HttpEntity<>(body, headers);
    }

}
