package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import static com.trailblazers.freewheelers.web.Session.SHOPPING_CART;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HomeControllerTest {

    private static final String OUT_OF_STOCK = "isItemOutOfStock";
    private static final String HAS_ITEM_BEEN_ADDED = "hasItemBeenAdded";
    private static final String ITEM_NAME = "a very nice item";
    private static final String ADDED_ITEM = "addedItemName";
    private static final String ITEMS_STRING = "items";
    private static final String CAME_FROM_POST = "cameFromPost";
    private List<Item> ITEMS;
    private HomeController homeController;
    private Model model;
    private HttpServletRequest request;
    private HttpSession httpSession;
    private Item item;
    private ItemService itemService;
    private String expected;
    private String actual;
    private HashMap<Long, Long> emptyCart;
    private Principal principal;
    private HashMap<Item, Long> singleItemCart;
    private Session session;

    @Before
    public void setUp() throws Exception {
        itemService = mock(ItemServiceImpl.class);
        session = mock(Session.class);
        homeController = new HomeController(itemService, session);
        principal = mock(Principal.class);
        model = mock(Model.class);
        request = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        item = mock(Item.class);
        emptyCart = new HashMap();
        singleItemCart = new HashMap<>();
        singleItemCart.put(item, 1L);
        ITEMS = asList(item);

        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute(SHOPPING_CART)).thenReturn(emptyCart);
        when(item.getItemId()).thenReturn(123L);
        when(item.getQuantity()).thenReturn(2L);
        when(itemService.get(anyLong())).thenReturn(item);
        when(httpSession.getAttribute(CAME_FROM_POST)).thenReturn(true);
        when(itemService.getItemsWithNonZeroQuantity()).thenReturn(ITEMS);
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(emptyCart);
    }

    @Test
    public void getShouldAddCorrectModelAttributes() throws Exception {
        expected = "home";
        actual = homeController.get(model, item, request);

        assertEquals(expected, actual);
    }

    @Test
    public void getShouldReturnHomePageWhenUserAccessHomeWithOutItem() throws Exception {
        when(httpSession.getAttribute(ADDED_ITEM)).thenReturn(ITEM_NAME);
        when(httpSession.getAttribute(HAS_ITEM_BEEN_ADDED)).thenReturn(true);

        homeController.get(model, item, request);

        verify(model).addAttribute(ITEMS_STRING, ITEMS);
        verify(httpSession, never()).setAttribute(OUT_OF_STOCK, false);
        verify(httpSession, never()).setAttribute(ADDED_ITEM, null);
        verify(httpSession, never()).setAttribute(HAS_ITEM_BEEN_ADDED, false);
        verify(httpSession).setAttribute(CAME_FROM_POST, false);
    }

    @Test
    public void getShouldNotSetMessagesIfRequestDidNotComeFromPost() throws Exception {
        when(httpSession.getAttribute(CAME_FROM_POST)).thenReturn(false);

        homeController.get(model, item, request);

        verify(model).addAttribute(ITEMS_STRING, ITEMS);
        verify(httpSession).setAttribute(OUT_OF_STOCK, false);
        verify(httpSession).setAttribute(ADDED_ITEM, null);
        verify(httpSession).setAttribute(HAS_ITEM_BEEN_ADDED, false);
        verify(httpSession).setAttribute(CAME_FROM_POST, false);
    }

    @Test
    public void postShouldRedirectToGet() throws Exception {
        actual = homeController.post(item, request, principal);
        expected = "redirect:/";

        assertEquals(expected, actual);
    }

    @Test
    public void postShouldCreateShoppingCartInSessionIfItDoesNotExist() throws Exception {
        when(httpSession.getAttribute(SHOPPING_CART)).thenReturn(null);

        homeController.post(item, request, principal);

        verify(httpSession, times(1)).setAttribute(SHOPPING_CART, singleItemCart);
    }

    @Test
    public void postShouldAddItemToCartIfItemIsNotYetInCart() throws Exception {
        HashMap mockCart = mock(HashMap.class);
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(mockCart);
        when(mockCart.containsKey(anyLong())).thenReturn(false);

        homeController.post(item, request, principal);

        verify(mockCart).put(item, 1L);
    }

    @Test
    public void postShouldIncreaseTheQuantityOfItemWhenTheItemIsAlreadyInCart() {
        HashMap shoppingCart = mock(HashMap.class);
        when(request.getSession()).thenReturn(httpSession);
        when(session.getItemHashMap(SHOPPING_CART, httpSession)).thenReturn(shoppingCart);
        when(shoppingCart.containsKey(item)).thenReturn(true);
        when(shoppingCart.get(item)).thenReturn(1L);

        homeController = new HomeController(itemService, session);
        homeController.post(item, request, principal);

        verify(shoppingCart).put(item, 2L);
    }

    @Test
    public void postShouldRedirectToLoginIfUserIsNotLoggedIn() throws Exception {
        expected = "redirect:/login";
        actual = homeController.post(item, request, null);

        assertEquals(expected, actual);
    }

    @Test
    public void postShouldCancelItemIfOutOfStock() throws Exception {
        when(item.getQuantity()).thenReturn(0L);

        expected = "redirect:/";
        actual = homeController.post(item, request, principal);

        verify(httpSession).setAttribute(OUT_OF_STOCK, true);
        verify(httpSession).setAttribute(HAS_ITEM_BEEN_ADDED, false);
        assertEquals(expected, actual);
    }

    @Test
    public void postShouldSetCorrectSessionAttributes() throws Exception {
        when(item.getName()).thenReturn(ITEM_NAME);

        homeController.post(item, request, principal);

        verify(httpSession).setAttribute(ADDED_ITEM, ITEM_NAME);
        verify(httpSession).setAttribute(OUT_OF_STOCK, false);
        verify(httpSession).setAttribute(HAS_ITEM_BEEN_ADDED, true);
        verify(httpSession).setAttribute(CAME_FROM_POST, true);
    }

}