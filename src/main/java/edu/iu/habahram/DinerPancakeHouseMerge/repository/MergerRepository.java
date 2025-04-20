package edu.iu.habahram.DinerPancakeHouseMerge.repository;

import edu.iu.habahram.DinerPancakeHouseMerge.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Repository
public class MergerRepository {

    public MergerRepository() {

    }

    private static Iterator<CompositeMenu> getMenus() {
        return Arrays.asList(new DinerMenu("Diner Menu", "Lunch"),
                new PancakeHouseMenu("Pancake House Menu", "Breakfast"),
                new CafeMenu("Cafe Menu", "Dinner")).iterator();
    }

    public List<MenuItemRecord> getMergedMenu() {
        Iterator<CompositeMenu> menus = getMenus();
        CompositeMenu allMenus = new CompositeMenu("All Menus", "All Menus Combined");
        while (menus.hasNext()) {
            allMenus.add(menus.next());
        }
        ArrayList<MenuItem> menuItems = allMenus.getItems();
        return menuItems.stream()
                .map(item -> new MenuItemRecord(item.getName(), item.getDescription(),
                        item.isVegetarian(), item.getPrice())).toList();
    }
}
