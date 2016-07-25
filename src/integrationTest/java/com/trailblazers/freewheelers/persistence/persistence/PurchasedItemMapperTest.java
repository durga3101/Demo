package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.PurchasedItemMapper;
import com.trailblazers.freewheelers.model.PurchasedItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PurchasedItemMapperTest extends MapperTestBase {

    private PurchasedItemMapper purchasedItemMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        purchasedItemMapper = getSqlSession().getMapper(PurchasedItemMapper.class);
    }

    private PurchasedItem someOrder() {
        return new PurchasedItem()
                .setAccount_id((long) 1)
                .setItem_id((long) 1)
                .setReservation_timestamp(new Date());
    }

    @Test
    public void shouldInsertAndGetAnOrder() throws Exception {
        PurchasedItem tobeInserted = someOrder();

        purchasedItemMapper.insert(tobeInserted);
        PurchasedItem fetched = purchasedItemMapper.getOrderByOrderId(tobeInserted.getOrder_id());

        assertThat(fetched, is(not(nullValue())));
    }

    @Test
    public void shouldDeleteAnOrder() throws Exception {
        PurchasedItem tobeDeleted = someOrder();
        purchasedItemMapper.insert(tobeDeleted);

        purchasedItemMapper.delete(tobeDeleted);

        PurchasedItem fetched = purchasedItemMapper.getOrderByOrderId(tobeDeleted.getOrder_id());
        assertThat(fetched, is(nullValue()));
    }

    @Test
    public void shouldUpdateAnOrder() throws Exception {
        PurchasedItem toBeUpdated = someOrder().setNote("");
        purchasedItemMapper.insert(toBeUpdated);

        toBeUpdated.setNote("A very important note.");
        purchasedItemMapper.update(toBeUpdated);

        PurchasedItem fetched = purchasedItemMapper.getOrderByOrderId(toBeUpdated.getOrder_id());
        assertThat(fetched.getNote(), is("A very important note."));
    }

    @Test
    public void shouldFindAllOrders() throws Exception {
        int before = purchasedItemMapper.getAllPurchasedItems().size();
        purchasedItemMapper.insert(someOrder());

        List<PurchasedItem> all = purchasedItemMapper.getAllPurchasedItems();

        assertThat(all.size(), is(before + 1));
    }

    @Test
    public void shouldFindAllOrdersForAnAccount() throws Exception {
        long someAccount = (long) 42;
        long anotherAccount = (long) 43;
        int before = purchasedItemMapper.getOrderByAccountId(someAccount).size();

        purchasedItemMapper.insert(someOrder().setAccount_id(someAccount));
        purchasedItemMapper.insert(someOrder().setAccount_id(someAccount));
        purchasedItemMapper.insert(someOrder().setAccount_id(anotherAccount));

        List<PurchasedItem> all = purchasedItemMapper.getOrderByAccountId(someAccount);

        assertThat(all.size(), is(before + 2));
    }

}
