package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.ShippingAddress;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/shippingAddress")
public class ShippingAddressController {
    ShippingAddressService shippingAddressService;
    private AccountService accountService;
    private ShippingAddress shippingAddress;
    private ItemService itemService;

    @Autowired
    public ShippingAddressController(ShippingAddressService shippingAddressService, AccountService accountService, ItemService itemService) {
        this.shippingAddressService = shippingAddressService;
        this.accountService = accountService;
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)

    public String get(Model model, HttpServletRequest request, Principal principal) {

        if(request.getSession().getAttribute("shoppingCart") == null){
            return "shippingAddress";
        }

        Account userAccount = accountService.getAccountIdByName(decode(principal.getName()));

        model.addAttribute("country",userAccount.getCountry());
        return "shippingAddress";
    }

    @RequestMapping(value = {"/addShippingAddress"},method = RequestMethod.POST)
    public String getShippingAddress(HttpServletRequest request, Principal principal){
        String street1 = request.getParameter("street1");
        String street2 = request.getParameter("street2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");

        Account userAccount = accountService.getAccountIdByName(decode(principal.getName()));

        shippingAddress = new ShippingAddress(userAccount.getAccount_id(), street1,street2,city,state,postcode);
        shippingAddressService.createShippingAddress(shippingAddress);
        return "redirect:/payment";
    }
    private String decode(String userName) {
        try {
            return URLDecoder.decode(userName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return userName;
        }
    }
}
