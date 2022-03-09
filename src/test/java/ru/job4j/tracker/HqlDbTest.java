package ru.job4j.tracker;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HqlDbTest {
    private Item item;
    private SessionFactory sessionFactory;
    private StandardServiceRegistry registry;
    private TestStore store;
    private ArrayList<Item> itemsFromStore;

    @Before
    public void setUp() throws Exception {
        this.store = new TestStore();
        this.itemsFromStore = new ArrayList<>();
        this.registry = new StandardServiceRegistryBuilder().configure().build();
        this.sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.item = new Item("TestName", "TestDescription", new Timestamp(436746464L));
    }

    @After
    public void tearDown() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Test
    public void addTest() {
        store.add(item);
        store.findAll(i -> itemsFromStore.add((Item) i));
        Assert.assertEquals(List.of(item), itemsFromStore);
    }

    @Test
    public void replaceTest() {
        store.add(item);
        store.replace("2", item);
        Item replacedItem = store.findById("2");
        Assert.assertEquals(item, replacedItem);
    }

    @Test
    public void delete() {
        store.add(item);
        store.delete(item.getId().toString());
        Item itemById = store.findById(item.getId().toString());
        Assert.assertNull(itemById);
    }

    @Test
    public void findAllTest() {
        store.add(item);
        store.findAll(i -> itemsFromStore.add((Item) i));
        Assert.assertEquals(List.of(item), itemsFromStore);
    }

    @Test
    public void findByNameTest() {
        store.add(item);
        List<Item> itemList = store.findByName("TestName");
        Assert.assertEquals(item, itemList.get(0));
    }

    @Test
    public void findByIdTest() {
        store.add(item);
        Item itemById = store.findById(item.getId().toString());
        Assert.assertEquals(item, itemById);
    }
}