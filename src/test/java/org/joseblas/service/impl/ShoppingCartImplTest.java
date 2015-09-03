package org.joseblas.service.impl;

import junit.framework.TestCase;
import org.joseblas.model.Items;
import org.joseblas.service.ShoppingCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by joseblas on 2/9/15.
 */
public class ShoppingCartImplTest extends TestCase {

    ShoppingCart cart;

    @Before
    public void setUp(){
        cart = new ShoppingCartImpl();
    }

    @Test
    public void testAdd() throws Exception {
        cart.add(Items.Apple);
        Assert.assertThat(cart.checkout(), is(35L));
        Assert.assertThat(cart.getTotalItems(),is(1L));
    }

    @Test
    public void testRemove() throws Exception {
        cart.add(Items.Apple);
        cart.remove(Items.Apple);
        cart.add(Items.Apple);
        Assert.assertThat(cart.checkout(), is(35L));
        Assert.assertThat(cart.getTotalItems(),is(1L));
    }


}