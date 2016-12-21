package com.ceri.visitemusee.tool;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.basket.Basket;
import com.ceri.visitemusee.basket.BasketItem;
import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.params.AppParams;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Maxime
 */

public class ItemAdapter extends ArrayAdapter<BasketItem> {

    public ItemAdapter(Context context, List<BasketItem> itemList) {
        super(context, 0, itemList);
    }

    // adapter to display item list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.basket_list_item, parent, false);
        }

        ItemViewHolder viewHolder = (ItemViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ItemViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.basketlistitemname);
            viewHolder.price = (TextView) convertView.findViewById(R.id.basketlistitemprice);
            viewHolder.action = (Button) convertView.findViewById(R.id.basketlistitemaction);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.basketlistitemimage);
            convertView.setTag(viewHolder);
        }

        final BasketItem basketItem = getItem(position);
        viewHolder.price.setText(basketItem.getPrice() + getContext().getString(R.string.euro));
        viewHolder.picture.setImageBitmap(basketItem.getPicture());

        if(AppParams.getInstance().getM_french()) {
            viewHolder.name.setText(basketItem.getName_FR());
            viewHolder.action.setText(getContext().getString(R.string.remove_from_basket_fr));
        }
        else {
            viewHolder.name.setText(basketItem.getName_EN());
            viewHolder.action.setText(getContext().getString(R.string.remove_from_basket_en));
        }

        viewHolder.action.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Basket.getInstance().removeItem(basketItem);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private class ItemViewHolder {
        public TextView name, price;
        public Button action;
        public ImageView picture;
    }
}
