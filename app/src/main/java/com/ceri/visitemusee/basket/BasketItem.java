package com.ceri.visitemusee.basket;

/**
 * Created by Maxime
 */

public class BasketItem {

    private double price;
    private String name;

    public BasketItem(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
