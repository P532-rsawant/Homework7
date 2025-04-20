package edu.iu.habahram.DinerPancakeHouseMerge.model;

import java.util.Iterator;

public interface Menu {
    void addItem(String name, String description, boolean vegetarian, double price);

    Iterator<MenuItem> getIterator();
}
