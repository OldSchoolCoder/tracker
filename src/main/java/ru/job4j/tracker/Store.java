package ru.job4j.tracker;

import ru.job4j.react.Observe;

import java.util.List;

public interface Store {

    Item add(Item item);

    boolean replace(String id, Item item);

    boolean delete(String id);

    void findAll(Observe observe);

    List<Item> findByName(String key);

    Item findById(String id);
}
