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

public class InterestPoint360Fragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    InterestPoint IP;

    WrappingGridView gridView360;

    public static InterestPoint360Fragment newInstance(int page, InterestPoint IP) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putSerializable(FileManager.IP, IP);
        InterestPoint360Fragment fragment = new InterestPoint360Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ip_360, container, false);
        IP = (InterestPoint) getArguments().getSerializable(FileManager.IP);

        gridView360 = (WrappingGridView) v.findViewById(R.id.grid_view_360);

        if(IP.get_360().isEmpty()) {
            gridView360.setVisibility(View.GONE);
        }
        else {
            //Inflate the grid view with the photos
            gridView360.setAdapter(new ImageAdapter(getContext(), IP.get_360().size(), IP.get_360())); //Pass the Bitmap string array

            // set click listener to open full image
            gridView360.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myI = new Intent(MainActivity.getContext(), SingleView360.class);
                    myI.putExtra("id", position);
                    myI.putExtra("InterestPoint", IP);
                    startActivity(myI);
                }
            });
        }

        return v;
    }
}