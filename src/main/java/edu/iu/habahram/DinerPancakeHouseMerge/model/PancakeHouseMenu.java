package edu.iu.habahram.DinerPancakeHouseMerge.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PancakeHouseMenu extends CompositeMenu {
    List<MenuItem> menuItems;

    public PancakeHouseMenu(String name, String description) {
        super(name, description);
        menuItems = new ArrayList<>();

        addItem("K&B's Pancake Breakfast", "Pancakes with scrambled eggs and toast", true, 2.99);

        addItem("Regular Pancake Breakfast", "Pancakes with fried eggs, sausage", false, 2.99);

        addItem("Blueberry Pancakes", "Pancakes made with fresh blueberries", true, 3.49);

        addItem("Waffles", "Waffles with your choice of blueberries or strawberries", true, 3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        add(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Iterator<MenuItem> getIterator() {
        return menuItems.iterator();
    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (MenuItem item : getMenuItems()) {
            stringBuilder.append(item.toString());
        }
        return stringBuilder.toString();
    }

    // other menu methods here
}
