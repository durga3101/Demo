package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.model.PurchasedItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchasedItemService {

    void save(PurchasedItem purchasedItem);

    List<PurchasedItem> findAllPurchasedItemsByAccountId(Long account_id);

    List<PurchasedItem> getAllPurchasedItemsSortedByAccount();


    void updatePurchasedItemDetails(Long order_id, OrderStatus status, String note);
}


