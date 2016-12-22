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

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.tool.VideoThumbnailsAdapter;
import com.ceri.visitemusee.tool.WrappingGridView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Maxime
 */

public class InterestPointVideosFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    InterestPoint IP;

    WrappingGridView gridViewVideo;

    private String[] pos;
    private ArrayList<Bitmap> myBitmap;
    private File tmpFile;
    private Bitmap tmpBitmap;

    public static InterestPointVideosFragment newInstance(int page, InterestPoint IP) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putSerializable(FileManager.IP, IP);
        InterestPointVideosFragment fragment = new InterestPointVideosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ip_videos, container, false);
        IP = (InterestPoint) getArguments().getSerializable(FileManager.IP);

        gridViewVideo = (WrappingGridView) v.findViewById(R.id.grid_view_video);

        if(IP.getVideos().isEmpty()) {
            gridViewVideo.setVisibility(View.GONE);
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