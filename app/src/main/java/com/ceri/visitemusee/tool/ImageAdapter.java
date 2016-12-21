package com.ceri.visitemusee.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ceri.visitemusee.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Maxime
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    private final int itemGridValues;
    private ArrayList<String> myBitmaps;

    // image adapter for gridview
    public ImageAdapter(Context context, int itemGridValues, ArrayList<String> x) {
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
            // get image loader (cache images - no lag)
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(this.myBitmaps.get(position), imageView);
        }
        else {
            gridView = convertView;
        }
        return gridView;
    }


    @Override
    public int getCount() {
        return itemGridValues;
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
