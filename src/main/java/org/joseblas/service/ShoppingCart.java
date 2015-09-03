package org.joseblas.service;

import org.joseblas.model.Item;

/**
 * Created by joseblas on 2/9/15.
 */
public interface ShoppingCart {
    //adds an item
    void add(Item item);
    //removes an item (no exception if doesn't exist)
    void remove(Item item);
    //retrieves the total amount to pay
    long checkout();
    //retrives number of elements in the cart
    long getTotalItems();
}
