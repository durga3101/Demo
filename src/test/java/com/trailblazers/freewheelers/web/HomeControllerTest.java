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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HomeControllerTest {

    public static final String SHOPPING_CART = "shoppingCart";
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

    @Before
    public void setUp() throws Exception {
        itemService = mock(ItemServiceImpl.class);
        homeController = new HomeController(itemService);
        principal = mock(Principal.class);
        model = mock(Model.class);
        request = mock(HttpServletRequest.class);
        httpSession = mock(HttpSession.class);
        item = mock(Item.class);
        emptyCart = mock(HashMap.class);

        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute(SHOPPING_CART)).thenReturn(emptyCart);
        when(item.getItemId()).thenReturn(123L);
    }

    @Test
    public void shouldReturnHomePageWhenUserAccessHomeWithItemInCart() throws Exception {
        when(httpSession.getAttribute("itemForReserve")).thenReturn(item);

        actual = homeController.get(model, item, request);

        assertEquals("home", actual);
    }

    @Test
    public void shouldReturnHomePageWhenUserAccessHomeWithOutItem() throws Exception {
        when(httpSession.getAttribute("itemForReserve")).thenReturn(null);

        actual = homeController.get(model, item, request);

        assertEquals("home", actual);
    }

    @Test
    public void postShouldRedirectToGet() throws Exception {
        actual = homeController.post(item, request, principal);
        expected = "redirect:/";

        assertEquals(expected, actual);
    }

    @Test
    public void postShouldCreateShoppingCartInSessionIfItDoesNotExist() throws Exception {
        when(httpSession.getAttribute(SHOPPING_CART)).thenReturn(null, emptyCart);

        homeController.post(item, request, principal);

        emptyCart = new HashMap<>();

        verify(httpSession, times(2)).setAttribute(SHOPPING_CART, emptyCart);
    }

    @Test
    public void postShouldNotCreateShoppingCartInSessionIfItAlreadyExist() throws Exception {
        homeController.post(item, request, principal);

        verify(httpSession, times(1)).setAttribute(SHOPPING_CART, emptyCart);
    }

    @Test
    public void postShouldAddItemToCartIfItemIsNotYetInCart() throws Exception {
        homeController.post(item, request, principal);

        verify(emptyCart).put(123L, 1L);
        verify(httpSession).setAttribute(SHOPPING_CART, emptyCart);
    }

    @Test
    public void postShouldIncreaseTheQuantityOfItemWhenTheItemIsAlreadyInCart() {
        HashMap<Long, Long> shoppingCart = new HashMap<>();
        shoppingCart.put(123L, 1L);
        HashMap<Long, Long> expectedCart = new HashMap<>();
        expectedCart.put(123L, 2L);

        when(httpSession.getAttribute(SHOPPING_CART)).thenReturn(shoppingCart);

        homeController.post(item, request, principal);

        assertEquals(expectedCart, shoppingCart);

    }

    @Test
    public void postShouldRedirectToLoginIfUserIsNotLoggedIn() throws Exception {
        expected = "redirect:/login";

        actual = homeController.post(item, request, null);

        assertEquals(expected, actual);
    }
}