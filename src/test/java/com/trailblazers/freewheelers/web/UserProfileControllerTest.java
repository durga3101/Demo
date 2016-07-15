package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.service.impl.ReserveOrderServiceImpl;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProfileControllerTest {
    private Model model;
    private Principal principal;
    private HttpServletRequest request;
    private UserProfileController userProfileController;
    private HttpSession httpSession;
    private Account account;

    private AccountService accountService;
    private ReserveOrderService reserveOrderService;
    private ItemService itemService;

    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
        principal = mock(Principal.class);
        request = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        accountService = mock(AccountServiceImpl.class);
        reserveOrderService = mock(ReserveOrderServiceImpl.class);
        itemService = mock(ItemServiceImpl.class);
        account = mock(Account.class);

        userProfileController = new UserProfileController(accountService, reserveOrderService, itemService);

        when(request.getSession()).thenReturn(httpSession);
        when(principal.getName()).thenReturn("rufus");
        when(accountService.getAccountIdByName(anyString())).thenReturn(account);
        when(account.getAccount_id()).thenReturn(1l);

        List<ReserveOrder> emptyList = new ArrayList<>();
        when(reserveOrderService.findAllOrdersByAccountId(anyLong())).thenReturn(emptyList);
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
        when(accountService.getRole(anyString())).thenReturn(USER);
        assertEquals("accessDenied", userProfileController.get("Ella", model, principal, request));
    }

}