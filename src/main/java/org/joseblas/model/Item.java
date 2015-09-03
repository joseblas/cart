package org.joseblas.model;

/**
 * Created by joseblas on 2/9/15.
 *
 */
public class Item {
    //price in pennies
    private long price;
    private String name;

    protected Item(String name, long price){
        this.name = name;
        this.price = price;
    }



    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(getPrice(), item.getPrice())
                .append(getName(), item.getName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(getPrice())
                .append(getName())
                .toHashCode();
    }
}
