package com.ceri.visitemusee.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ceri.visitemusee.R;

import java.util.ArrayList;

/**
 * Created by Clément
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    private final String[] itemGridValues;
    private ArrayList<Bitmap> myBitmaps;

    // image adapter for gridview
    public ImageAdapter(Context context, String[] itemGridValues, ArrayList<Bitmap> x) {
        this.context = context;
        this.itemGridValues = itemGridValues;
        this.myBitmaps = x;
    }

    // create item view for gridview
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if(convertView == null) {
            gridView = inflater.inflate(R.layout.grid_item_image, null);
            //Set image based on selected image
            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            imageView.setImageBitmap(this.myBitmaps.get(position));
        }
        else {
            gridView = convertView;
        }
        return gridView;
    }


    @Override
    public int getCount() {
        if(itemGridValues == null)
            return 0;
        return itemGridValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
