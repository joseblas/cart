package org.joseblas.service.impl;

import org.joseblas.model.Item;
import org.joseblas.service.ShoppingCart;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by joseblas on 2/9/15.
 */
public class ShoppingCartImpl implements ShoppingCart {

    //lock to avoid conccurent problem
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    // Cart item list
    Map<Item,AtomicInteger> items = Collections.synchronizedMap(new HashMap<Item, AtomicInteger>());
    //total
    AtomicLong total = new AtomicLong(0);

    /*
     Adds an item to the cart
     */
    public void add(Item item) {
        lock.writeLock().lock();
        if(!items.containsKey(item)){
            items.put(item, new AtomicInteger(1));
        }else{
            items.get(item).incrementAndGet();
        }
        total.accumulateAndGet(item.getPrice(), (n, m) -> n + m);
        lock.writeLock().unlock();
    }

    public void remove(Item item) {
        lock.writeLock().lock();
        if(items.containsKey(item)){
            items.get(item).decrementAndGet();
            total.accumulateAndGet(item.getPrice(), (n, m) -> n - m);
        }
        lock.writeLock().unlock();
    }

    public long getTotalItems(){
        AtomicLong total = new AtomicLong();
        for(AtomicInteger integer: items.values()){
            total.accumulateAndGet(integer.get(), (a,b) -> a+b);
        }
        return total.get();
    }

    public long checkout() {
        return total.get();
    }

}
