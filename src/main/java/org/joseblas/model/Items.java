package org.joseblas.model;

/**
 * Created by joseblas on 2/9/15.
 * Helper class to create all the available items.
 * To extend this properly, Item should be an interface to be easier to extend,
 * for instance for fruit by weight (pricePerKg and kilos) or by item.
 */
public class Items {

    public static Item Banana = new Item("Banana",55);
    public static Item Orange = new Item("Orange",20);
    public static Item Apple = new Item("Apple",35);
    public static Item Lemon = new Item("Lemon",15);
    public static Item Peach = new Item("Peach",40);

}
