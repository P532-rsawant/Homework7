package edu.iu.habahram.DinerPancakeHouseMerge.repository;

import edu.iu.habahram.DinerPancakeHouseMerge.model.*;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public List<MenuItemRecord> getVegetarianItems() {
        Iterator<CompositeMenu> menus = getMenus();
        CompositeMenu allMenus = new CompositeMenu("All Menus", "All Menus Combined");
        while (menus.hasNext()) {
            allMenus.add(menus.next());
        }

        ArrayList<MenuItem> menuItems = allMenus.getItems();
        return menuItems.stream()
                .filter(MenuItem::isVegetarian)
                .map(item -> new MenuItemRecord(item.getName(), item.getDescription(),
                        true, item.getPrice()))
                .toList();
    }
    public List<MenuItemRecord> getBreakfastItems() {
        // Get just the PancakeHouseMenu which is our breakfast menu
        PancakeHouseMenu breakfastMenu = new PancakeHouseMenu("Pancake House Menu", "Breakfast");
        ArrayList<MenuItem> breakfastItems = breakfastMenu.getItems();

        return breakfastItems.stream()
                .map(item -> new MenuItemRecord(item.getName(), item.getDescription(),
                        item.isVegetarian(), item.getPrice()))
                .toList();
    }
    public List<MenuItemRecord> getLunchItems() {
        // Get just the DinerMenu which is our lunch menu
        DinerMenu lunchMenu = new DinerMenu("Diner Menu", "Lunch");
        ArrayList<MenuItem> lunchItems = lunchMenu.getItems();

        return lunchItems.stream()
                .map(item -> new MenuItemRecord(item.getName(), item.getDescription(),
                        item.isVegetarian(), item.getPrice()))
                .toList();
    }
    public List<MenuItemRecord> getSupperItems() {
        // Get just the CafeMenu which is our supper/dinner menu
        CafeMenu supperMenu = new CafeMenu("Cafe Menu", "Dinner");
        ArrayList<MenuItem> supperItems = supperMenu.getItems();

        return supperItems.stream()
                .map(item -> new MenuItemRecord(
                        item.getName(),
                        item.getDescription(),
                        item.isVegetarian(),
                        item.getPrice()
                ))
                .toList();
    }
    public boolean signup(String username, String password, String email) {
        Path path = Paths.get("data/customers.txt");
        try {
            // Create data directory if it doesn't exist
            Files.createDirectories(path.getParent());

            // Append the new user to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString(), true));
            writer.write(username + "," + password + "," + email + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error saving customer data: " + e.getMessage());
            return false;
        }
    }
}
