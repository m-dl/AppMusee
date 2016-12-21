package com.ceri.visitemusee.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.basket.Basket;
import com.ceri.visitemusee.basket.BasketActivity;
import com.ceri.visitemusee.basket.BasketItem;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.params.AppParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Maxime
 */

public class ItemAdapter extends ArrayAdapter<BasketItem> {

    // action true = add item to basket / false = remove item from basket
    private boolean action;

    public ItemAdapter(Context context, List<BasketItem> itemList, boolean action) {
        super(context, 0, itemList);
        this.action = action;
    }

    // adapter to display item list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.basket_list_item, parent, false);
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.basketlistitemname);
            viewHolder.price = (TextView) convertView.findViewById(R.id.basketlistitemprice);
            viewHolder.action = (Button) convertView.findViewById(R.id.basketlistitemaction);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.basketlistitemimage);
            convertView.setTag(viewHolder);
        }

        final BasketItem basketItem = getItem(position);
        viewHolder.price.setText(basketItem.getPrice() + getContext().getString(R.string.euro));

        // get image loader (cache images - no lag)
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(basketItem.getPicture(), viewHolder.picture);

        if(AppParams.getInstance().getM_french()) {
            viewHolder.name.setText(basketItem.getName_FR());
            if(!action)
                viewHolder.action.setText(getContext().getString(R.string.remove_from_basket_fr));
            else
                viewHolder.action.setText(getContext().getString(R.string.add_to_basket_fr));
        }
        else {
            viewHolder.name.setText(basketItem.getName_EN());
            if(!action)
                viewHolder.action.setText(getContext().getString(R.string.remove_from_basket_en));
            else
                viewHolder.action.setText(getContext().getString(R.string.add_to_basket_en));
        }

        viewHolder.action.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // delete item
                if(!action) {
                    Basket.getInstance().removeItem(basketItem);
                    BasketActivity.validateBasketText.setText(Basket.getInstance().getBasketObjectsText());
                    notifyDataSetChanged();
                    if(AppParams.getInstance().getM_french())
                        Tools.notifBar(v, MainActivity.getContext().getString(R.string.confirmation_remove_basket_fr));
                    else
                        Tools.notifBar(v, MainActivity.getContext().getString(R.string.confirmation_remove_basket_en));
                }
                // add item
                else {
                    Basket.getInstance().addItem(basketItem);
                    if(AppParams.getInstance().getM_french())
                        Tools.notifBar(v, MainActivity.getContext().getString(R.string.confirmation_add_basket_fr));
                    else
                        Tools.notifBar(v, MainActivity.getContext().getString(R.string.confirmation_add_basket_en));
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {
        public TextView name, price;
        public Button action;
        public ImageView picture;
    }
}
