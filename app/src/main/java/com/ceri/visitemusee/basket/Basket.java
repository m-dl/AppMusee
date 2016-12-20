package com.ceri.visitemusee.basket;

import android.graphics.Bitmap;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.params.AppParams;

import net.glxn.qrgen.android.QRCode;

import java.util.ArrayList;

/**
 * Created by Maxime
 */

public class Basket {

    private static Basket INSTANCE;
    private ArrayList<BasketItem> items;

    // singleton
    public static Basket getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Basket();
        return INSTANCE;
    }

    public Basket() {
        this.items = new ArrayList<>();
    }

    public int getBasketSize() {
        return this.items.size();
    }

    public void emptyBasket() {
        this.items.clear();
    }

    public double getBasketAmmount() {
        double t = 0;
        if(this.items.isEmpty())
            return t;
        for(BasketItem item : this.items) {
            t += item.getPrice();
        }
        return t;
    }

    public String getBasketObjectsText() {
        String plural = " - ";
        if(this.items.size() > 1)
            plural = "s - ";
        if(AppParams.getInstance().getM_french())
            return Basket.getInstance().getBasketSize() + " " + MainActivity.resources.getString(R.string.object_fr) + plural +
                    Basket.getInstance().getBasketAmmount() + MainActivity.resources.getString(R.string.euro);
        else
            return Basket.getInstance().getBasketSize() + " " + MainActivity.resources.getString(R.string.object_en) + plural +
                    Basket.getInstance().getBasketAmmount() + MainActivity.resources.getString(R.string.euro);
    }

    public String getBasketItemsText() {
        String plural = " - ";
        if(this.items.size() > 1)
            plural = "s - ";
        if(AppParams.getInstance().getM_french())
            return Basket.getInstance().getBasketSize() + " " + MainActivity.resources.getString(R.string.article_fr) + plural +
                    Basket.getInstance().getBasketAmmount() + MainActivity.resources.getString(R.string.euro);
        else
            return Basket.getInstance().getBasketSize() + " " + MainActivity.resources.getString(R.string.article_en) + plural +
                    Basket.getInstance().getBasketAmmount() + MainActivity.resources.getString(R.string.euro);
    }

    public String getBasketValidateButtonText() {
        if(AppParams.getInstance().getM_french())
            return MainActivity.resources.getString(R.string.validate_basket_fr);
        else
            return MainActivity.resources.getString(R.string.validate_basket_en);
    }

    public String getBasketEmptyButtonText() {
        if(AppParams.getInstance().getM_french())
            return MainActivity.resources.getString(R.string.empty_basket_fr);
        else
            return MainActivity.resources.getString(R.string.empty_basket_en);
    }

    public String getQRCodeBasketText() {
        if(AppParams.getInstance().getM_french())
            return MainActivity.resources.getString(R.string.generate_basket_message_fr);
        else
            return MainActivity.resources.getString(R.string.generate_basket_message_en);
    }

    public Bitmap generateQRCode() {
        return QRCode.from("Génération du panier en QRCode").bitmap();
    }
}
