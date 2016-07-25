package com.trailblazers.freewheelers.apis;

import com.trailblazers.freewheelers.model.*;
import com.trailblazers.freewheelers.service.*;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.service.impl.OrderServiceImpl;
import com.trailblazers.freewheelers.service.impl.PurchasedItemServiceImpl;
import com.trailblazers.freewheelers.web.Order;

import java.util.Date;
import java.util.List;

import static com.trailblazers.freewheelers.helpers.SyntaxSugar.*;


public class AdminApi {

    private AccountService accountService;
    private ItemService itemService;
    private SurveyService surveyService;
    private PurchasedItemService purchasedItemService;
    private OrderService orderService;

    public AdminApi() {
        this.accountService = new AccountServiceImpl();
        this.itemService = new ItemServiceImpl();
        this.surveyService = new SurveyService();
        this.purchasedItemService = new PurchasedItemServiceImpl();
        this.orderService = new OrderServiceImpl();

    }

    public AdminApi there_are_no_survey_entries() {
        surveyService.deleteAll();
        return this;
    }

    public AdminApi there_is_no_account_for(String accountName) {
        Account account = accountService.getAccountIdByName(accountName);
        if (account != null) {
            accountService.delete(account);
        }
        return this;
    }

    public AdminApi there_is_a_user(String userName, String password) {
        there_is_no_account_for(userName);
        accountService.createAccount(account_for(userName, password));

        return this;
    }

    public AdminApi there_is_an_admin(String userName, String password) {
        there_is_no_account_for(userName);
        accountService.createAdmin(account_for(userName, password));

        return this;
    }

    public AdminApi there_is_no_item(String itemName) {
        Item toBeDeleted = itemService.getByName(itemName);

        while (toBeDeleted != null) {
            itemService.delete(toBeDeleted);
            toBeDeleted = itemService.getByName(itemName);
        }

        return this;
    }

    public AdminApi there_is_a_frame(String itemName, Long quantity) {
        there_is_no_item(itemName);
        itemService.saveItem(itemFor(itemName, quantity));

        return this;
    }

    private Account account_for(String userName, String password) {
        return new Account(password, true, emailFor(userName), SOME_PHONE_NUMBER, SOME_COUNTRY, userName);
    }

    public AdminApi there_is_a_survey_entry_for(long accountId, int feedbackType, String comment) {
        surveyService.submitSurvey(accountId, new SurveyEntry(feedbackType, comment));
        return this;
    }

    public AdminApi there_is_a_uesr_create_country(String userName, String password, String country) {
        there_is_no_account_for(userName);
        accountService.createAccount(new Account(password, true, emailFor(userName), SOME_PHONE_NUMBER, country, userName));
        return this;
    }

    public AdminApi there_is_an_order(String customer, String itemName) {
        Account userAccount = accountService.getAccountIdByName(customer);
        Long account_Id =  userAccount.getAccount_id();

        Item item = itemService.getByName(itemName);
        Long item_Id = item.getItemId();
        PurchasedItem order = reservedOrderFor(account_Id, item_Id);
        purchasedItemService.save(order);

        return this;

    }

    private Item itemFor(String itemName, Long quantity) {
        return new Item()
                .setName(itemName)
                .setQuantity(quantity)
                .setDescription("A very nice item.")
                .setPrice(SOME_PRICE)
                .setType(ItemType.FRAME);
    }

    private PurchasedItem reservedOrderFor(Long account_id, Long item_id) {
        return new PurchasedItem(account_id, item_id, new Date());
    }

    public List<Order> get_all_order_id_for_user(String userEmail) {
        List<Account> allAccounts = accountService.findAll();
        Long account_id = 0l;
        for(Account account : allAccounts){
            if(account.getEmail_address().equals(userEmail)){
                account_id = account.getAccount_id();
                break;
            }
        }
        return orderService.getOrders(account_id);

    }
}
