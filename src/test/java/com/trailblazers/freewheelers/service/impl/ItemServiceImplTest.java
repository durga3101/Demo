package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.ItemMapper;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemType;
import com.trailblazers.freewheelers.service.ItemService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemServiceImplTest {

    @Mock
    ItemMapper itemMapper;

    @Mock
    SqlSession sqlSession;

    ItemService itemService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(sqlSession.getMapper(ItemMapper.class)).thenReturn(itemMapper);
        itemService = new ItemServiceImpl(sqlSession);
    }

    @Test
    public void shouldGetItemByNameFromMapper(){
        String name = "name";
        Item expectedItem = new Item();
        when((itemMapper.getByName(name))).thenReturn(expectedItem);

        Item returnedItem = itemService.getByName(name);
        verify(itemMapper).getByName(name);
        assertThat(returnedItem, is(expectedItem));
    }
    @Test
    public void shouldCreateItemWhenThereAreNoValidationErrors(){
        Item item = getItemWithoutError();
        itemService.saveItem(item);

        verify(itemMapper, times(1)).insert(item);
        verify(sqlSession, times(1)).commit();
    }

    @Test
    public void shouldDecreaseQuantityByOne() throws Exception {
        Item item = getItemWithoutError();
        Long expectedQuantity = item.getQuantity() - 1 ;
        itemService.decreaseQuantityByOne(item);

        assertEquals(expectedQuantity, item.getQuantity());
    }
    @Test
    public void shouldNotUpdateItemWhenThereIsANegativeItemQuantity(){
        Item item = getItemWithoutError();
        Long negativeNumber = (long) -10;
        item.setQuantity(negativeNumber);

        Item savedItem = itemService.saveItem(item);

        assertNotEquals(savedItem.getQuantity(), negativeNumber);

    }

    @Test
    public void shouldReturnHashMapWithItemObjectsGivenHashMapWithItemIDs() throws Exception {
        Item item = getItemWithoutError();
        HashMap<Long,Long> map = new HashMap<>();
        map.put(1l, 2l);
        when(itemMapper.getByItemId(anyLong())).thenReturn(item);
        HashMap<Item, Long> expectedMap = new HashMap<>();
        expectedMap.put(item, 2l);

        HashMap<Item, Long> itemMap = itemService.getItemHashMap(map);

        verify(itemMapper).getByItemId(anyLong());
        assertEquals(expectedMap, itemMap);
    }

    private Item getItemWithoutError() {
        Item item = new Item();
        item.setName("item")
                .setPrice(valueOf(123.00))
                .setDescription("example")
                .setQuantity((long)123)
                .setType(ItemType.ACCESSORIES);
        return item;
    }
}
