package com.ceri.visitemusee.tool;

import android.content.ClipData;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.basket.Basket;
import com.ceri.visitemusee.basket.BasketItem;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.params.AppParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Maxime
 */

public class Tools {

    // 8 museum maps
    public static final int MAP_ONE = 1;
    public static final int MAP_TWO = 2;
    public static final int MAP_THREE = 3;
    public static final int MAP_FOUR = 4;
    public static final int MAP_FIVE = 5;
    public static final int MAP_SIX = 6;
    public static final int MAP_SEVEN = 7;
    public static final int MAP_HEIGHT = 8;
    public static final String ROOM = "Room";

    public static int distanceToRange(double distance) {
        if(distance < 1)
            return MAP_TWO;
        else if(distance < 3)
            return MAP_THREE;
        else if(distance < 7)
            return MAP_FOUR;
        else if(distance < 12)
            return MAP_FIVE;
        else if(distance < 16)
            return MAP_SIX;
        else if(distance < 20)
            return MAP_SEVEN;
        else if(distance < 25)
            return MAP_HEIGHT;
        return MAP_ONE;
    }

    // display toast message
    public static void notifToast(String s) {
        Toast.makeText(MainActivity.getContext(), s, Toast.LENGTH_SHORT).show();
    }

    // display snackbar message
    public static void notifBar(View view, String s) {
        Snackbar.make(view, s, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    // display list of element
    public static void displayItemList(ListView ItemListView, List<BasketItem> itemList, boolean action) {
        ItemAdapter adapter = new ItemAdapter(MainActivity.getContext(), itemList, action);
        ItemListView.setAdapter(adapter);
        int numberOfItems = adapter.getCount();

        // Get total height of all items.
        int totalItemsHeight = 0;
        // order true to display most recent items first
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = adapter.getView(itemPos, null, ItemListView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }
        // Get total height of all item dividers.
        int totalDividersHeight = ItemListView.getDividerHeight() * (numberOfItems - 1);

        // Set list height.
        ViewGroup.LayoutParams params = ItemListView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        ItemListView.setLayoutParams(params);
        ItemListView.requestLayout();
    }

    // sort items asc
    public static void sortPriceUp(ArrayList<BasketItem> basketItemArrayList) {
        Collections.sort(basketItemArrayList, new Comparator<BasketItem>() {
            @Override
            public int compare(BasketItem o1, BasketItem o2) {
                if(o1.getPrice() > o2.getPrice())
                    return 1;
                else if(o1.getPrice() < o2.getPrice())
                    return -1;
                return 0;
            }
        });
    }

    // sort items desc
    public static void sortPriceDown(ArrayList<BasketItem> basketItemArrayList) {
        Collections.sort(basketItemArrayList, new Comparator<BasketItem>() {
            @Override
            public int compare(BasketItem o1, BasketItem o2) {
                if(o1.getPrice() < o2.getPrice())
                    return 1;
                else if(o1.getPrice() > o2.getPrice())
                    return -1;
                return 0;
            }
        });
    }

    public static String getBasketNumberText(ArrayList<BasketItem> basketItemArrayList) {
        String plural = "";
        if(basketItemArrayList.size() > 1)
            plural = "s";
        if(AppParams.getInstance().getM_french())
            return basketItemArrayList.size() + " " + MainActivity.resources.getString(R.string.article_fr) + plural;
        else
            return basketItemArrayList.size() + " " + MainActivity.resources.getString(R.string.article_en) + plural;
    }
}
