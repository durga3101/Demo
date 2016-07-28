package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.*;
import com.trailblazers.freewheelers.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import static com.trailblazers.freewheelers.config.FeatureToggles.ORDER_ID_CONNECT_FEATURE;
import static java.lang.Long.valueOf;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    private PurchasedItemService purchasedItemService;
    private ItemService itemService;
    private AccountService accountService;
    private OrderService orderService;
    private OrderedItemService orderedItemService;

    @Autowired
    public AdminController(PurchasedItemService purchasedItemService, ItemService itemService, AccountService accountService, OrderService orderService, OrderedItemService orderedItemService) {
        this.purchasedItemService = purchasedItemService;
        this.itemService = itemService;
        this.accountService = accountService;
        this.orderService = orderService;
        this.orderedItemService = orderedItemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void get(Model model) {
        if(ORDER_ID_CONNECT_FEATURE){

            List<Order> allOrders = orderService.getAllOrders();

            for(Order order : allOrders){
                List<OrderedItem> orderedItems = orderedItemService.getAllOrderedItemsByOrderId(order.getOrder_id());
                //get Item from OrderedItem
                for(OrderedItem orderedItem : orderedItems){
                    Item item = itemService.get(orderedItem.getItem_id());
                    if(item != null){
                        item.setQuantity(orderedItem.getQuantity());
                        order.addToOrderedItems(item);
                    }
                }
                //get accountName from account_id
                Long account_id = order.getAccount_id();
                String accountName = accountService.get(account_id).getAccount_name();
                //put both in Order
                order.setAccountName(accountName);
            }
            //add allOrders to attribute
            model.addAttribute("allOrders", allOrders );
        }else{
            List<PurchasedItemDetail> allPurchasedItems = getAllPurchasedItems();
            model.addAttribute("purchasedItems", allPurchasedItems);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrder(Model model, String state, String orderId, String note) {
        Long order_id = valueOf(orderId);
        OrderStatus status = OrderStatus.valueOf(state);
        purchasedItemService.updatePurchasedItemDetails(order_id, status, note);
        get(model);
    }

    protected List<PurchasedItemDetail> getAllPurchasedItems() {
        List<PurchasedItem> purchasedItems = purchasedItemService.getAllPurchasedItemsSortedByAccount();

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