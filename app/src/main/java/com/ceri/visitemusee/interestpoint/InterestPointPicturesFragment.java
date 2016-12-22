package com.ceri.visitemusee.interestpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.tool.ImageAdapter;
import com.ceri.visitemusee.tool.WrappingGridView;

/**
 * Created by Maxime
 */

public class InterestPointPicturesFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    InterestPoint IP;

    WrappingGridView gridViewPhoto;

    public static InterestPointPicturesFragment newInstance(int page, InterestPoint IP) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putSerializable(FileManager.IP, IP);
        InterestPointPicturesFragment fragment = new InterestPointPicturesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ip_pictures, container, false);
        IP = (InterestPoint) getArguments().getSerializable(FileManager.IP);

        gridViewPhoto = (WrappingGridView) v.findViewById(R.id.grid_view_photo);

        // if some media elements are empty, do not dislay titles and gridviews
        if(IP.getPhotos().isEmpty()) {
            gridViewPhoto.setVisibility(View.GONE);
        }
        else {
            //Inflate the grid view with the photos
            gridViewPhoto.setAdapter(new ImageAdapter(getContext(), IP.getPhotos().size(), IP.getPhotos())); //Pass the Bitmap string array

            // set click listener to open full image
            gridViewPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myI = new Intent(MainActivity.getContext(), SingleViewPhotos.class);
                    myI.putExtra("id", position);
                    myI.putExtra("InterestPoint", IP);
                    startActivity(myI);
                }
            });
        }
        return v;
    }
}