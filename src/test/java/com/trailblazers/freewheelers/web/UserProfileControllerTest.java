package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.config.FeatureToggles;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.PurchasedItem;
import com.trailblazers.freewheelers.model.ShippingAddress;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.PurchasedItemService;
import com.trailblazers.freewheelers.service.ShippingAddressService;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.service.impl.PurchasedItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.trailblazers.freewheelers.service.impl.AccountServiceImpl.ADMIN;
import static com.trailblazers.freewheelers.service.impl.AccountServiceImpl.USER;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class UserProfileControllerTest {
    private Model model;
    private Principal principal;
    private HttpServletRequest request;
    private UserProfileController userProfileController;
    private HttpSession httpSession;
    private Account account;

    private AccountService accountService;
    private PurchasedItemService purchasedItemService;
    private ItemService itemService;
    private ShippingAddressService addressService;

    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
        principal = mock(Principal.class);
        request = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        accountService = mock(AccountServiceImpl.class);
        addressService = mock(ShippingAddressService.class);
        purchasedItemService = mock(PurchasedItemServiceImpl.class);
        itemService = mock(ItemServiceImpl.class);
        account = mock(Account.class);

        userProfileController = new UserProfileController(accountService, purchasedItemService, itemService,addressService );

        when(request.getSession()).thenReturn(httpSession);
        when(principal.getName()).thenReturn("rufus@gmail.com");
        when(accountService.getAccountFromEmail(principal.getName())).thenReturn(account);
        when(accountService.getAccountIdByName(anyString())).thenReturn(account);
        when(account.getAccount_id()).thenReturn(1l);
        when(account.getAccount_name()).thenReturn("rufus");


        List<PurchasedItem> emptyList = new ArrayList<>();
        when(purchasedItemService.findAllPurchasedItemsByAccountId(anyLong())).thenReturn(emptyList);
        emptyList = new ArrayList<>();
    }



    @Test
    public void shouldReturnUserProfileWhenUserIsAdmin() {
        when(accountService.getRole(anyString())).thenReturn(ADMIN);
        assertEquals("userProfile", userProfileController.get(null, model, principal, request));
    }

    @Test
    public void shouldReturnUsersProfileWhenUserIsLoggedIn() {
        when(accountService.getRole(anyString())).thenReturn(USER);
        assertEquals("userProfile", userProfileController.get(null, model, principal, request));
    }

    @Test
    public void shouldReturnAccessDeniedWhenUnauthorizedUserAndNotAdmin(){
        Account userAccount =  mock(Account.class);
        when(accountService.getRole(anyString())).thenReturn(USER);
        when(accountService.getAccountFromEmail("Ella@gmail.com")).thenReturn(userAccount);
        when(accountService.getAccountFromEmail(principal.getName()).getAccount_name()).thenReturn("admin");
        when(userAccount.getAccount_name()).thenReturn("Ella");
        assertEquals("accessDenied", userProfileController.get("Ella@gmail.com", model, principal, request));
    }

    @Test
    public void shouldGetAddressFromServiceWhenAddressIsSaved() throws Exception {
        if (FeatureToggles.DISPLAY_ADDRESS_ON_USER_PROFILE) {
            when(accountService.getRole(anyString())).thenReturn(USER);
            userProfileController.get(null, model, principal, request);
            verify(addressService).getLatestAddress(anyLong());
        }
    }

    @Test
    public void shouldAddItemsAndUserDetailToModelAttributesWhenGetIsCalledForAdmin() {
        when(accountService.getRole(anyString())).thenReturn(ADMIN);
        when(accountService.getAccountFromEmail("luke@gmail.com")).thenReturn(account);
        model = mock(ExtendedModelMap.class);

        userProfileController.get("luke@gmail.com", model, principal, request);

        verify(model).addAttribute(eq("items"), any(List.class));
        verify(model).addAttribute("userDetail", account);
    }

    @Test
    public void shouldGetAddressOfRightAccountProfileWhenProfileHasAddress() {
        if (FeatureToggles.DISPLAY_ADDRESS_ON_USER_PROFILE) {
            when(accountService.getRole(anyString())).thenReturn(USER);
            when(accountService.getAccountIdByName(anyString())).thenReturn(account);
            when(account.getAccount_id()).thenReturn(1l);

            userProfileController.get(null, model, principal, request);

            verify(addressService).getLatestAddress(1l);
        }
    }

    @Test
    public void shouldShowMessageIfAddressNotFound() {

        if (FeatureToggles.DISPLAY_ADDRESS_ON_USER_PROFILE) {
            Model model = mock(Model.class);
            when(accountService.getRole(anyString())).thenReturn(USER);
            when(accountService.getAccountIdByName(anyString())).thenReturn(account);
            when(account.getAccount_id()).thenReturn(1l);
            when(addressService.getLatestAddress(anyLong())).thenReturn(null);

            userProfileController.get(model, principal, request);

            verify(model, atLeastOnce()).addAttribute("addressAvailable", false);
        }

    }

    @Test
    public void shouldShowAddressIfAddressIsFound() {

        if (FeatureToggles.DISPLAY_ADDRESS_ON_USER_PROFILE) {
            Model model = mock(Model.class);
            ShippingAddress address = mock(ShippingAddress.class);
            when(accountService.getRole(anyString())).thenReturn(USER);
            when(accountService.getAccountIdByName(anyString())).thenReturn(account);
            when(account.getAccount_id()).thenReturn(1l);
            when(addressService.getLatestAddress(anyLong())).thenReturn(address);

            userProfileController.get(model, principal, request);

            verify(model, atLeastOnce()).addAttribute("addressAvailable", true);
        }

    }


}