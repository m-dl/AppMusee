package com.ceri.visitemusee.interestpoint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.params.AppParams;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Maxime
 */

public class InterestPointFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    InterestPoint IP;

    ImageView interestPointPicture;
    TextView interestPointTitle;
    TextView interestPointContent;

    public static InterestPointFragment newInstance(int page, InterestPoint IP) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putSerializable(FileManager.IP, IP);
        InterestPointFragment fragment = new InterestPointFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ip_page, container, false);
        IP = (InterestPoint) getArguments().getSerializable(FileManager.IP);

        interestPointPicture = (ImageView) v.findViewById(R.id.interest_point_picture);
        interestPointTitle = (TextView) v.findViewById(R.id.interest_point_title);
        interestPointContent = (TextView) v.findViewById(R.id.interest_point_content);

        // load header picture
        if(IP.getPhotos() != null) {
            if (!IP.getPhotos().isEmpty()) {
                ImageLoader imageLoader = ImageLoader.getInstance();
                imageLoader.displayImage(IP.getPhotos().get(0), interestPointPicture);
            }
        }

        // set en or fr text
        if (AppParams.getInstance().getM_french()) {
            interestPointTitle.setText(IP.getName_FR());
            interestPointContent.setText(IP.getPresentation_FR());
        }
        else {
            interestPointTitle.setText(IP.getName_EN());
            interestPointContent.setText(IP.getPresentation_EN());
        }

        return v;
    }
}