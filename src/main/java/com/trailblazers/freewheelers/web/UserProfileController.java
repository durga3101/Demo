package com.trailblazers.freewheelers.web;


import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.model.ShippingAddress;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.trailblazers.freewheelers.service.impl.AccountServiceImpl.ADMIN;

@Controller
@RequestMapping("/userProfile")
public class UserProfileController {

    AccountService accountService;
    ReserveOrderService reserveOrderService;
    ItemService itemService;
    ShippingAddressService shippingAddressService;

    @Autowired
    public UserProfileController(AccountService accountService, ReserveOrderService reserveOrderService, ItemService itemService, ShippingAddressService shippingAddressService) {
        this.accountService = accountService;
        this.reserveOrderService = reserveOrderService;
        this.itemService = itemService;
        this.shippingAddressService = shippingAddressService;
    }

    @RequestMapping(value = "/{nameFromURL:.*}", method = RequestMethod.GET)
    public String get(@PathVariable String nameFromURL, Model model, Principal principal, HttpServletRequest request) {

        nameFromURL = getUserNameIfNull(nameFromURL, principal);
        String loggedInUser = decode(principal.getName());
        String role = accountService.getRole(loggedInUser);

        if (role.equals(ADMIN) && !nameFromURL.equals(loggedInUser)) {
            model = setModel(model, nameFromURL);
            return "userProfile";
        }

        if (!role.equals(ADMIN) && !nameFromURL.equals(loggedInUser)) {
            return "accessDenied";
        }

        setModel(model, loggedInUser);
        return "userProfile";

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String get(Model model, Principal principal, HttpServletRequest request) {
        return get(null, model, principal, request);
    }

    private Model setModel(Model model, String userName) {
        Account account = accountService.getAccountIdByName(userName);
        List<Item> items = getItemsOrderByUser(account);
        model.addAttribute("items", items);
        model.addAttribute("userDetail", account);

        ShippingAddress address = shippingAddressService.getAddress(account.getAccount_id());
        if (address == null) {
            model.addAttribute("addressAvailable", false);
        }
        else {
            model.addAttribute("addressAvailable", true);
            model.addAttribute("address", address);
        }
        return model;
    }

    private String getUserNameIfNull(String userName, Principal principal) {
        if (userName == null) {
            userName = principal.getName();
        }
        return decode(userName);
    }

    private String decode(String userName) {
        try {
            return URLDecoder.decode(userName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return userName;
        }
    }


    private List<Item> getItemsOrderByUser(Account account) {
        List<ReserveOrder> reserveOrders = reserveOrderService.findAllOrdersByAccountId(account.getAccount_id());
        List<Item> items = new ArrayList<Item>();
        for (ReserveOrder reserveOrder : reserveOrders) {
            items.add(itemService.get(reserveOrder.getItem_id()));
        }
        return items;
    }


}
