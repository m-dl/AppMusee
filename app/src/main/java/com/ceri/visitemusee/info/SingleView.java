package com.ceri.visitemusee.info;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.entities.musee.Info;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.ScreenParam;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Maxime
 */
public class SingleView extends AppCompatActivity {

    private Info info;
    private Intent intent;
    private int position;
    private ActionBarDrawerToggle m_DrawerToggle;
    private ScreenParam param;

    @Bind(R.id.drawer_layout)
    DrawerLayout m_DrawerLayout;

    @Bind(R.id.toolbar)
    Toolbar m_Toolbar;

    @Bind(R.id.single_view_item)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(AppParams.getInstance().getCurrentVisit() == null) {
            finish();
            return;
        }

        initObjects();

        ArrayList<File> photos = info.getPhotos();
        File tmpFile = photos.get(position);
        Bitmap tmpBitmap = BitmapFactory.decodeFile(tmpFile.getAbsolutePath());
        imageView.setImageBitmap(tmpBitmap);
    }

    // initiate the objects and design
    private void initObjects() {
        setContentView(R.layout.activity_single_view);
        ButterKnife.bind(this);
        param = new ScreenParam();
        param.paramWindowFullScreen(getWindow());
        param.paramSetSupportActionBar(m_Toolbar, this);
        m_DrawerToggle = new ActionBarDrawerToggle(this, m_DrawerLayout, 0, 0);

        intent = getIntent();
        position = intent.getExtras().getInt("id");
        info = (Info) intent.getSerializableExtra("Info");

        // set en or fr text
        if (AppParams.getInstance().getM_french()) {
            nameActionBar(AppParams.getInstance().getCurrentVisit().getName());
        }
        else {
            nameActionBar(AppParams.getInstance().getCurrentVisit().getNameEN());
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

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
