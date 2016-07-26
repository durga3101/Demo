package com.trailblazers.freewheelers.web;


import com.trailblazers.freewheelers.config.FeatureToggles;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.PurchasedItem;
import com.trailblazers.freewheelers.model.ShippingAddress;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.PurchasedItemService;
import com.trailblazers.freewheelers.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    PurchasedItemService purchasedItemService;
    ItemService itemService;
    ShippingAddressService shippingAddressService;
    HttpSession httpSession;

    @Autowired
    public UserProfileController(AccountService accountService, PurchasedItemService purchasedItemService, ItemService itemService, ShippingAddressService shippingAddressService) {
        this.accountService = accountService;
        this.purchasedItemService = purchasedItemService;
        this.itemService = itemService;
        this.shippingAddressService = shippingAddressService;
    }

    @RequestMapping(value = "/{emailFromURL:.*}", method = RequestMethod.GET)
    public String get(@PathVariable String emailFromURL, Model model, Principal principal, HttpServletRequest request) {
        httpSession = request.getSession();

        emailFromURL = getUserNameIfNull(emailFromURL,principal);

        String nameFromURL = accountService.getAccountFromEmail(emailFromURL).getAccount_name();
        String loggedInUser = decode(accountService.getAccountFromEmail(principal.getName()).getAccount_name());

        String role = accountService.getRole(principal.getName());

        if (role.equals(ADMIN) && !nameFromURL.equals(loggedInUser)) {
            model = setModel(model, emailFromURL);
            return "userProfile";
        }

        if (!role.equals(ADMIN) && !emailFromURL.equals(principal.getName())) {
            System.out.println("access denied page");
            return "accessDenied";
        }

        setModel(model, emailFromURL);
        return "userProfile";

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String get(Model model, Principal principal, HttpServletRequest request) {
        return get(null, model, principal, request);
    }

    private Model setModel(Model model, String userName) {
        Account account = accountService.getAccountFromEmail(userName);
        List<Item> items = getItemsOrderByUser(account);
        model.addAttribute("items", items);
        model.addAttribute("userDetail", account);

        if (FeatureToggles.DISPLAY_ADDRESS_ON_USER_PROFILE) {
            ShippingAddress address = shippingAddressService.getLatestAddress(account.getAccount_id());
            if (address == null) {
                model.addAttribute("addressAvailable", false);
            } else {
                model.addAttribute("addressAvailable", true);
                model.addAttribute("address", address);
            }
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
        List<PurchasedItem> purchasedItems = purchasedItemService.findAllPurchasedItemsByAccountId(account.getAccount_id());
        List<Item> items = new ArrayList<Item>();
        for (PurchasedItem purchasedItem : purchasedItems) {
            items.add(itemService.get(purchasedItem.getItem_id()));
        }
        return items;
    }


}
