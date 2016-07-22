package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.ItemMapper;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ItemMapperTest extends MapperTestBase {

    public static final long AVAILABLE = 1L;
    public static final long UNAVAILABLE = 0L;
    private ItemMapper itemMapper;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        itemMapper = getSqlSession().getMapper(ItemMapper.class);
    }

    @Test
    public void shouldInsertANewItem() throws Exception {
        Item item = someItem().setItemId(null);

        itemMapper.insert(item);

        assertThat(item.getItemId(), is(not(nullValue())));
    }

    @Test
    public void shouldFetchAnItemById() throws Exception {
        Item item = someItem().setName("Awesome Item");

        itemMapper.insert(item);
        Item fetched = itemMapper.getByItemId(item.getItemId());

        assertThat(fetched.getName(), is("Awesome Item"));
    }

    @Test
    public void shouldFetchAnItemByName() throws Exception {
        Item item = someItem().setName("Some Item");

        itemMapper.insert(item);
        Item fetched = itemMapper.getByName("Some Item");

        assertThat(fetched.getName(), is("Some Item"));
    }

    @Test
    public void shouldDeleteItem() throws Exception {
        Item item = someItem();
        itemMapper.insert(item);

        itemMapper.delete(item);
        Item fetched = itemMapper.getByItemId(item.getItemId());

        assertThat(fetched, is(nullValue()));
    }

    @Test
    public void shouldUpdateAnItem() throws Exception {
        Item item = someItem().setPrice(valueOf(100.00));
        itemMapper.insert(item);

        item.setPrice(valueOf(99.99));
        itemMapper.update(item);
        Item fetched = itemMapper.getByItemId(item.getItemId());

        assertThat(fetched.getPrice(), is(valueOf(99.99)));
    }

    @Test
    public void shouldReturnItemDetailsWhenWeInsertedAnItem() throws Exception {
        List<Item> allItems = itemMapper.getAllItems();
        Item someItem = someItem();
        itemMapper.insert(someItem);

        List<Item> items = itemMapper.getAllItems();

        assertEquals(items.size(), allItems.size() + 1);
        assertTrue(isItemPresentInList(items, someItem));
    }

    @Test
    public void shouldShouldFindAllAvailableItems() throws Exception {
        int before = itemMapper.getAvailableItems().size();

        itemMapper.insert(someItem().setQuantity(AVAILABLE));
        itemMapper.insert(someItem().setQuantity(UNAVAILABLE));
        itemMapper.insert(someItem().setQuantity(UNAVAILABLE));

        assertThat(itemMapper.getAvailableItems().size(), is(before + 1));
    }

    @Test
    public void shouldUpdateImageURLForItem() {
        Item item = someItem();
        String itemName = item.getName();

        itemMapper.insert(item);
        assertEquals(item.getImageURL(), itemMapper.getByName(itemName).getImageURL());

        String URL = "aURL";

        item.setImageURL(URL);
        itemMapper.update(item);

        Item actualItem = itemMapper.getByName(itemName);
        String imageURL = actualItem.getImageURL();

        assertEquals(URL, imageURL);
    }

    private Item someItem() {
        return new Item()
                .setName("Some Item")
                .setDescription("... with a very nice description")
                .setPrice(valueOf(9.99))
                .setQuantity(100L)
                .setType(ItemType.ACCESSORIES)
                .setImageURL("some link");
    }

    private boolean isItemPresentInList(List<Item> items, Item someItem) {
        for (Item item : items) {
            if (item.getDescription().equals(someItem.getDescription())
                    && item.getImageURL().equals(someItem.getImageURL())
                    && item.getItemId().equals(someItem.getItemId())
                    && item.getName().equals(someItem.getName())
                    && item.getPrice().equals(someItem.getPrice())
                    && item.getQuantity().equals(someItem.getQuantity())
                    && item.getType().equals(someItem.getType())) {
                return true;
            }
        }
        return false;
    }


}
