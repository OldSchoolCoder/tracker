package ru.job4j.tracker;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class HibernateRunTest {

    private Item item;
    private SessionFactory sessionFactory;
    private StandardServiceRegistry registry;

    @Before
    public void setUp() throws Exception {
        this.registry = new StandardServiceRegistryBuilder().configure().build();
        this.sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.item = new Item("TestName", "TestDescription", new Timestamp(436746464L));
    }

    @After
    public void tearDown() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Test
    public void create() {
        try {
            HibernateRun.create(item, sessionFactory);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}