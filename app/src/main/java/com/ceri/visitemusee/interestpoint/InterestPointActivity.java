package com.ceri.visitemusee.interestpoint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.ImageAdapter;
import com.ceri.visitemusee.tool.ScreenParam;
import com.ceri.visitemusee.tool.WrappingGridView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Maxime
 */
public class InterestPointActivity extends AppCompatActivity {
    private InterestPoint IP;
    private ActionBarDrawerToggle m_DrawerToggle;
    private ScreenParam param;

    @Bind(R.id.drawer_layout)
    DrawerLayout m_DrawerLayout;

    @Bind(R.id.toolbar)
    Toolbar m_Toolbar;

    @Bind(R.id.grid_view_photo)
    WrappingGridView gridViewPhoto;

    @Bind(R.id.grid_view_360)
    WrappingGridView gridView360;

    @Bind(R.id.grid_view_video)
    WrappingGridView gridViewVideo;

    @Bind(R.id.interest_point_picture)
    ImageView interestPointPicture;

    @Bind(R.id.interest_point_title)
    TextView interestPointTitle;

    @Bind(R.id.interest_point_content)
    TextView interestPointContent;

    @Bind(R.id.interest_point_photo_title)
    TextView interestPointPhotoTitle;

    @Bind(R.id.interest_point_360_title)
    TextView interestPoint360Title;

    @Bind(R.id.interest_point_video_title)
    TextView interestPointVideoTitle;

    private String[] pos;
    private ArrayList<Bitmap> myBitmap;
    private File tmpFile;
    private Bitmap tmpBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObjects();
    }

    // initiate the objects and design
    private void initObjects() {
        setContentView(R.layout.activity_interest_point);
        ButterKnife.bind(this);
        param = new ScreenParam();
        param.paramWindowFullScreen(getWindow());
        param.paramSetSupportActionBar(m_Toolbar, this);
        m_DrawerToggle = new ActionBarDrawerToggle(this, m_DrawerLayout, 0, 0);

        //Getting the interest point
        IP = (InterestPoint) getIntent().getSerializableExtra("InterestPoint");

        if(IP.getPhotos() != null)
            if(!IP.getPhotos().isEmpty())
                try {
                    interestPointPicture.setImageBitmap(BitmapFactory.decodeStream(
                            MainActivity.getContext().getResources().getAssets().open(IP.getPhotos().get(0).getPath())));
                } catch (IOException e) {
                    e.printStackTrace();
                }

        // set en or fr text
        if (AppParams.getInstance().getM_french()) {
            interestPointTitle.setText(IP.getName_FR());
            interestPointContent.setText(IP.getPresentation_FR());
            interestPointPhotoTitle.setText(R.string.images);
            interestPoint360Title.setText(R.string.images360);
            interestPointVideoTitle.setText(R.string.videos);
            nameActionBar(IP.getName_FR());
        }
        else {
            interestPointTitle.setText(IP.getName_EN());
            interestPointContent.setText(IP.getPresentation_EN());
            interestPointPhotoTitle.setText(R.string.pictures);
            interestPoint360Title.setText(R.string.pictures360);
            interestPointVideoTitle.setText(R.string.videosen);
            nameActionBar(IP.getName_EN());
        }

        // if some media elements are empty, do not dislay titles and gridviews
        if(IP.getPhotos().isEmpty()) {
            gridViewPhoto.setVisibility(View.GONE);
            interestPointPhotoTitle.setVisibility(View.GONE);
        }
        else {
            myBitmap = new ArrayList<>();
            // Load all the file from the arrayList then convert them into bitmap
            pos = new String[IP.getPhotos().size()];
            for(int i=0; i<IP.getPhotos().size(); i++){
                pos[i]="media"+i;
                this.tmpFile = IP.getPhotos().get(i);
                if(tmpFile != null){
                    //Decode the file into a bitmap
                    try {
                        tmpBitmap = BitmapFactory.decodeStream(MainActivity.getContext().getResources().getAssets().open(tmpFile.getPath()));
                        tmpBitmap = ThumbnailUtils.extractThumbnail(tmpBitmap, AppParams.THUMB_SIZE, AppParams.THUMB_SIZE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Put the created bitmap into an array to be pass to the ImageAdapter
                    if(tmpBitmap != null){
                        this.myBitmap.add(tmpBitmap);
                    }
                }
            }
            //Inflate the grid view with the photos
            gridViewPhoto.setAdapter(new ImageAdapter(this, pos, myBitmap)); //Pass the Bitmap array

            // set click listener to open full image
            gridViewPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myI = new Intent(getApplicationContext(), SingleViewPhotos.class);
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
            myBitmap = new ArrayList<>();
            // Load all the file from the arrayList then convert them into bitmap
            pos = new String[IP.get_360().size()];
            for(int i=0; i<IP.get_360().size(); i++){
                pos[i]="media"+i;
                this.tmpFile = IP.get_360().get(i);
                if(tmpFile != null){
                    try {
                        tmpBitmap = BitmapFactory.decodeStream(MainActivity.getContext().getResources().getAssets().open(tmpFile.getPath()));
                        tmpBitmap = ThumbnailUtils.extractThumbnail(tmpBitmap, AppParams.THUMB_SIZE, AppParams.THUMB_SIZE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Put the created bitmap into an array to be pass to the ImageAdapter
                    if(tmpBitmap != null){
                        this.myBitmap.add(tmpBitmap);
                    }
                }
            }
            //Inflate the grid view with the photos
            gridView360.setAdapter(new ImageAdapter(this, pos, myBitmap)); //Pass the Bitmap array

            // set click listener to open full image
            gridView360.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myI = new Intent(getApplicationContext(), SingleView360.class);
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
                            "/" + FileManager.RAW + tmpFile.getPath());
                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                    retriever.setDataSource(this, myVideoUri);
                    tmpBitmap = retriever.getFrameAtTime(10, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
                    //Put the created bitmap into an array to be pass to the ImageAdapter
                    if(tmpBitmap != null){
                        this.myBitmap.add(tmpBitmap);
                    }
                }
            }
            //Inflate the grid view with the photos
            gridViewVideo.setAdapter(new ImageAdapter(this, pos, myBitmap)); //Pass the Bitmap array

            // set click listener to open full video
            gridViewVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myI = new Intent(getApplicationContext(), PlayerActivity.class);
                    myI.putExtra("id", position);
                    myI.putExtra("InterestPoint", IP);
                    startActivity(myI);
                }
            });
        }
    }

    // set action bar text
    private void nameActionBar(String s) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(s);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
