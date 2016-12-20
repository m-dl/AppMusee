package com.ceri.visitemusee.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.basket.Basket;
import com.ceri.visitemusee.basket.BasketItem;

import java.util.List;

/**
 * Created by Maxime
 */

public class ItemAdapter extends ArrayAdapter<BasketItem> {

    public ItemAdapter(Context context, List<BasketItem> C) {
        super(context, 0, C);
    }

    // adapter to display item list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.basket_list_item, parent, false);
        }

        CViewHolder viewHolder = (CViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new CViewHolder();
            viewHolder.city = (TextView) convertView.findViewById(R.id.basketlistitemname);
            viewHolder.country = (TextView) convertView.findViewById(R.id.basketlistitemprice);
            convertView.setTag(viewHolder);
        }

        BasketItem C = getItem(position);
        viewHolder.city.setText(C.getName());
        viewHolder.country.setText(C.getPrice() + "");

        return convertView;
    }

    private class CViewHolder {
        public TextView city;
        public TextView country;
    }
}
