package com.ceri.visitemusee.basket;

import java.io.Serializable;

/**
 * Created by Maxime
 */

public class BasketItem implements Serializable {

    private double price;
    private String name_FR, name_EN, presentation_FR, presentation_EN, type, picture;

    public BasketItem(double price, String name_FR, String name_EN, String presentation_FR, String presentation_EN, String type, String picture) {
        this.price = price;
        this.name_FR = name_FR;
        this.name_EN = name_EN;
        this.presentation_FR = presentation_FR;
        this.presentation_EN = presentation_EN;
        this.type = type;
        this.picture =picture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName_FR() {
        return name_FR;
    }

    public void setName_FR(String name_FR) {
        this.name_FR = name_FR;
    }

    public String getName_EN() {
        return name_EN;
    }

    public void setName_EN(String name_EN) {
        this.name_EN = name_EN;
    }

    public String getPresentation_FR() {
        return presentation_FR;
    }

    public void setPresentation_FR(String presentation_FR) {
        this.presentation_FR = presentation_FR;
    }

    public String getPresentation_EN() {
        return presentation_EN;
    }

    public void setPresentation_EN(String presentation_EN) {
        this.presentation_EN = presentation_EN;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
