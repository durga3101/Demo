package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.model.PurchasedItem;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.PurchasedItemService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.trailblazers.freewheelers.FeatureToggles.ORDER_ID_CONNECT_FEATURE;
import static java.lang.Long.valueOf;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    private PurchasedItemService purchasedItemService;
    private ItemService itemService;
    private AccountService accountService;
    private AdminController adminController;
    private Model model;

    @Before
    public void setUp() throws Exception {
        ORDER_ID_CONNECT_FEATURE = false;
        purchasedItemService = mock(PurchasedItemService.class);
        itemService = mock(ItemService.class);
        accountService = mock(AccountService.class);
        model = mock(Model.class);
        adminController = new AdminController(purchasedItemService, itemService, accountService);
    }

    @Test
    public void getShouldInvokeAddAttributeMethodOfModel() throws Exception {
        adminController.get(model);
        verify(model).addAttribute((String) any(), any());
    }

    @Test
    public void getShouldInvokeAllDependencies() throws Exception {
        List<PurchasedItem> purchasedItems = new ArrayList<>();
        purchasedItems.add(mock(PurchasedItem.class));
        purchasedItems.add(mock(PurchasedItem.class));
        purchasedItems.add(mock(PurchasedItem.class));
        when(purchasedItemService.getAllPurchasedItemsSortedByAccount()).thenReturn(purchasedItems);

        adminController.get(model);

        verify(purchasedItemService).getAllPurchasedItemsSortedByAccount();
        verify(accountService, atLeastOnce()).get((Long) any());
        verify(itemService, atLeastOnce()).get((Long) any());
    }

    @Test
    public void updateOrderShouldCallUpdateOrderDetails() {
        String note = "note";
        Long orderId = 1l;

        adminController.updateOrder(model, OrderStatus.NEW.toString(), orderId.toString(), note);

        verify(purchasedItemService).updatePurchasedItemDetails(valueOf(orderId), OrderStatus.NEW, note);

    }

    @Test
    public void shouldGetAllItemsByOrderAndAddToModelWhenGetIsCalled(){
        ORDER_ID_CONNECT_FEATURE = true;


        adminController.get(model);


        ORDER_ID_CONNECT_FEATURE = false;
    }

}