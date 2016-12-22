package com.ceri.visitemusee.interestpoint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.ImageAdapter;
import com.ceri.visitemusee.tool.VideoThumbnailsAdapter;
import com.ceri.visitemusee.tool.WrappingGridView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Maxime
 */

public class InterestPoint360Fragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    InterestPoint IP;

    WrappingGridView gridViewPhoto;
    WrappingGridView gridView360;
    WrappingGridView gridViewVideo;
    ImageView interestPointPicture;
    TextView interestPointTitle;
    TextView interestPointContent;
    TextView interestPointPhotoTitle;
    TextView interestPoint360Title;
    TextView interestPointVideoTitle;

    private String[] pos;
    private ArrayList<Bitmap> myBitmap;
    private File tmpFile;
    private Bitmap tmpBitmap;

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
        View v = inflater.inflate(R.layout.fragment_ip_page, container, false);
        IP = (InterestPoint) getArguments().getSerializable(FileManager.IP);

        gridViewPhoto = (WrappingGridView) v.findViewById(R.id.grid_view_photo);
        gridView360 = (WrappingGridView) v.findViewById(R.id.grid_view_360);
        gridViewVideo = (WrappingGridView) v.findViewById(R.id.grid_view_video);
        interestPointPicture = (ImageView) v.findViewById(R.id.interest_point_picture);
        interestPointTitle = (TextView) v.findViewById(R.id.interest_point_title);
        interestPointContent = (TextView) v.findViewById(R.id.interest_point_content);
        interestPointPhotoTitle = (TextView) v.findViewById(R.id.interest_point_photo_title);
        interestPoint360Title = (TextView) v.findViewById(R.id.interest_point_360_title);
        interestPointVideoTitle = (TextView) v.findViewById(R.id.interest_point_video_title);

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
            interestPointPhotoTitle.setText(R.string.images);
            interestPoint360Title.setText(R.string.images360);
            interestPointVideoTitle.setText(R.string.videos);
        }
        else {
            interestPointTitle.setText(IP.getName_EN());
            interestPointContent.setText(IP.getPresentation_EN());
            interestPointPhotoTitle.setText(R.string.pictures);
            interestPoint360Title.setText(R.string.pictures360);
            interestPointVideoTitle.setText(R.string.videosen);
        }

        // if some media elements are empty, do not dislay titles and gridviews
        if(IP.getPhotos().isEmpty()) {
            gridViewPhoto.setVisibility(View.GONE);
            interestPointPhotoTitle.setVisibility(View.GONE);
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

        if(IP.get_360().isEmpty()) {
            gridView360.setVisibility(View.GONE);
            interestPoint360Title.setVisibility(View.GONE);
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

        if(IP.getVideos().isEmpty()) {
            gridViewVideo.setVisibility(View.GONE);
            interestPointVideoTitle.setVisibility(View.GONE);
        }
        else {
            myBitmap = new ArrayList<>();
            // Load all the file from the arrayList then convert them into bitmap
            pos = new String[IP.getVideos().size()];
            for(int i=0; i<IP.getVideos().size(); i++){
                pos[i]="media"+i;
                this.tmpFile = IP.getVideos().get(i);
                if(tmpFile != null){
                    //Decode the file into a bitmap
                    Uri myVideoUri = Uri.parse(FileManager.RESSOURCES + MainActivity.getContext().getPackageName() +
                            "/" + FileManager.RAW + IP.getVideos().get(i));
                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                    retriever.setDataSource(getContext(), myVideoUri);
                    tmpBitmap = retriever.getFrameAtTime(10, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
                    //Put the created bitmap into an array to be pass to the ImageAdapter
                    if(tmpBitmap != null){
                        this.myBitmap.add(tmpBitmap);
                    }
                }
            }
            //Inflate the grid view with the photos
            gridViewVideo.setAdapter(new VideoThumbnailsAdapter(getContext(), myBitmap.size(), myBitmap)); //Pass the Bitmap array

            // set click listener to open full video
            gridViewVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myI = new Intent(MainActivity.getContext(), PlayerActivity.class);
                    myI.putExtra("id", position);
                    myI.putExtra("InterestPoint", IP);
                    startActivity(myI);
                }
            });
        }

        return v;
    }
}