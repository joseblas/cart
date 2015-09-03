package org.joseblas.service.impl;

import junit.framework.TestCase;
import org.joseblas.model.Items;
import org.joseblas.service.ShoppingCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by joseblas on 2/9/15.
 */
public class ShoppingCartStressTest extends TestCase {

    ShoppingCart cart;
    int numberApples = 2000000;

    @Before
    public void setUp() {
        cart = new ShoppingCartImpl();
    }

    @Test
    public void testAdd() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(250);
        IntStream.range(0,numberApples)
                .forEach(i -> executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        cart.add(Items.Apple);
                    }
                }));

        executorService.shutdown();

        executorService.awaitTermination(10, TimeUnit.SECONDS);
        Assert.assertThat(cart.checkout(), is((long) Items.Apple.getPrice() * numberApples));
        Assert.assertThat(cart.getTotalItems(),is((long)numberApples));

    }
}
