package edu.iu.habahram.DinerPancakeHouseMerge.model;

public class DinerMenu extends CompositeMenu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu(String name, String description) {
        super(name, description);
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("Vegetarian BLT", "(Fakin') Bacon with lettuce & tomato on whole wheat", true,
                2.99);
        addItem("BLT", "Bacon with lettuce & tomato on whole wheat", false, 2.99);
        addItem("Soup of the day", "Soup of the day, with a side of potato salad", false, 3.29);
        addItem("Hotdog", "A hot dog, with sauerkraut, relish, onions, topped with cheese", false,
                3.05);
        addItem("Steamed Veggies and Brown Rice", "Steamed vegetables over brown rice", true, 3.99);
        addItem("Pasta", "Spaghetti with Marinara Sauce, and a slice of sourdough bread", true,
                3.89);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        add(menuItem);
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public DinerMenuIterator getIterator() {
        return new DinerMenuIterator(menuItems);
    }

//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (MenuItem item : getMenuItems()) {
//            stringBuilder.append(item.toString());
//        }
//        return stringBuilder.toString();
//    }

    // other menu methods here
}
