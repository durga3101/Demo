package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SessionTest {

    private Session session;
    private HttpSession httpSession;
    private String attribute;

    @Before
    public void setUp() throws Exception {
        httpSession = mock(HttpSession.class);
        session = new Session(httpSession);
    }

    @Test
    public void ShouldNotReturnNull() throws Exception {
        assertNotNull(session.getItemHashMap(attribute));
    }

    @Test
    public void shouldCallGetAttributeOnHttpSession() {
        HashMap<Item, Long> itemLongHashMap = new HashMap<>();
        itemLongHashMap.put(new Item(),1l);
        when(httpSession.getAttribute(anyString())).thenReturn(itemLongHashMap);
        attribute = "Items";
        assertEquals(itemLongHashMap,session.getItemHashMap(attribute));

        verify(httpSession).getAttribute(attribute);
    }

    @Test
    public void shouldReturnEmptyHashMapIfReturnedAttributeIsNotHashMap() {
        when(httpSession.getAttribute(anyString())).thenReturn(new Object());

        assertEquals(new HashMap<Item, Long>(), session.getItemHashMap(attribute));
    }
}