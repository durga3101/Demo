package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ShippingAddress;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ShippingAddressService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shippingAddress")
public class ShippingAddressController {
    ShippingAddressService shippingAddressService;
    private ShippingAddress shippingAddress;
    private ItemService itemService;

    @Autowired
    public ShippingAddressController(ShippingAddressService shippingAddressService, ItemService itemService) {
        this.shippingAddressService = shippingAddressService;
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, HttpServletRequest request) {
        if(request.getSession().getAttribute("itemOnConfirm") == null){
            return "shippingAddress";
        }
        Item item = (Item) request.getSession().getAttribute("itemOnConfirm");
        Item itemOnConfirm = itemService.get(item.getItemId());
        model.addAttribute("totalAmount", itemOnConfirm.getPrice());
        return "shippingAddress";
    }

    @RequestMapping(value = {"/addShippingAddress"},method = RequestMethod.POST)
    public String getShippingAddress(HttpServletRequest request){
        String street1 = request.getParameter("street1");
        System.out.println(street1);
        String street2 = request.getParameter("street2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postcode = request.getParameter("postcode");
        //ShippingAddress shippingAddress = null;
        shippingAddress = new ShippingAddress(street1,street2,city,state,postcode);
        shippingAddressService.createShippingAddress(shippingAddress);
        return "redirect:/payment";
    }
}
