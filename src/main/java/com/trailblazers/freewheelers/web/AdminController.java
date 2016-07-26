package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.model.PurchasedItem;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.OrderService;
import com.trailblazers.freewheelers.service.PurchasedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.valueOf;

@Controller
@RequestMapping("/admin")
public class AdminController {

    static final String URL = "/admin";

    private PurchasedItemService purchasedItemService;
    private ItemService itemService;
    private AccountService accountService;
    private OrderService orderService;

    @Autowired
    public AdminController(PurchasedItemService purchasedItemService, ItemService itemService, AccountService accountService, OrderService orderService) {
        this.purchasedItemService = purchasedItemService;
        this.itemService = itemService;
        this.accountService = accountService;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void get(Model model) {
        List<PurchasedItemDetail> allPurchasedItemsFromAccount = getAllPurchasedItems();
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("purchasedItems", allPurchasedItemsFromAccount);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrder(Model model, String state, String orderId, String note) {
        Long order_id = valueOf(orderId);
        OrderStatus status = OrderStatus.valueOf(state);

        purchasedItemService.updatePurchasedItemDetails(order_id, status, note);
        orderService.updateOrder(order_id, status, note);

        get(model);
    }

    protected List<PurchasedItemDetail> getAllPurchasedItems() {
        List<PurchasedItem> purchasedItems = purchasedItemService.getAllPurchasedItemsByAccount();

        List<PurchasedItemDetail> purchasedItemDetails = new ArrayList<>();

        for (PurchasedItem purchasedItem : purchasedItems) {
            Account account = accountService.get(purchasedItem.getAccount_id());
            Item item = itemService.get(purchasedItem.getItem_id());

            purchasedItemDetails.add(new PurchasedItemDetail(
                    purchasedItem.getOrder_id(),
                    account,
                    item,
                    purchasedItem.getReservation_timestamp(),
                    purchasedItem.getStatus(),
                    purchasedItem.getNote()));

        }
        return purchasedItemDetails;
    }

}