package com.ceri.visitemusee.overview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.entities.musee.Overview;
import com.ceri.visitemusee.entities.musee.Visit;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.ScreenParam;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Maxime
 */
public class OverviewActivity extends AppCompatActivity {
    private Visit visit;
    private Overview overview;
    private ActionBarDrawerToggle m_DrawerToggle;
    private ScreenParam param;

    @Bind(R.id.drawer_layout)
    DrawerLayout m_DrawerLayout;

    @Bind(R.id.image_container)
    LinearLayout imageContainer;

    @Bind(R.id.toolbar)
    Toolbar m_Toolbar;

    @Bind(R.id.overview_picture1)
    ImageView overviewPicture1;

    @Bind(R.id.overview_picture2)
    ImageView overviewPicture2;

    @Bind(R.id.overview_picture3)
    ImageView overviewPicture3;

    @Bind(R.id.overview_title)
    TextView overviewTitle;

    @Bind(R.id.overview_length)
    TextView overviewLength;

    @Bind(R.id.overview_content)
    TextView overviewContent;

    @Bind(R.id.cancel_visit)
    Button cancelVisit;

    @Bind(R.id.validate_visit)
    Button validateVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObjects();

        // if users clicks it, he cancels the visit launch
        cancelVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(false);
            }
        });

        // if users clicks it, he starts the visit launch
        validateVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(true);
            }
        });
    }

    // initiate the objects and design
    private void initObjects() {
        setContentView(R.layout.activity_overview);
        ButterKnife.bind(this);
        param = new ScreenParam();
        param.paramWindowFullScreen(getWindow());
        param.paramSetSupportActionBar(m_Toolbar, this);
        m_DrawerToggle = new ActionBarDrawerToggle(this, m_DrawerLayout, 0, 0);

        //Getting the visit
        visit = (Visit) getIntent().getSerializableExtra("Overview");
        //Getting the visit's overview
        overview = visit.getOverview();

        // show the images, 3 needed, but not required
        if(overview.getImagesContent().isEmpty())
            imageContainer.setVisibility(View.GONE);
        else
            overviewPicture1.setImageBitmap(BitmapFactory.decodeFile(overview.getImagesContent().get(0).getAbsolutePath()));
        if(overview.getImagesContent().size() > 1)
            overviewPicture2.setImageBitmap(BitmapFactory.decodeFile(overview.getImagesContent().get(1).getAbsolutePath()));
        else
            overviewPicture2.setVisibility(View.GONE);
        if(overview.getImagesContent().size() > 2)
            overviewPicture3.setImageBitmap(BitmapFactory.decodeFile(overview.getImagesContent().get(2).getAbsolutePath()));
        else
            overviewPicture3.setVisibility(View.GONE);

        // set en or fr text
        if (AppParams.getInstance().getM_french()) {
            overviewTitle.setText(visit.getName());
            overviewLength.setText(overview.readLength_FR());
            overviewContent.setText(overview.readPresentation_FR());
            cancelVisit.setText(R.string.annuler);
            validateVisit.setText(R.string.lancer);
            nameActionBar(visit.getName());
        }
        else {
            overviewTitle.setText(visit.getNameEN());
            overviewLength.setText(overview.readLength_EN());
            overviewContent.setText(overview.readPresentation_EN());
            cancelVisit.setText(R.string.cancel);
            validateVisit.setText(R.string.start);
            nameActionBar(visit.getNameEN());
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
            // if users return, he cancels the visit launch
            return finishActivity(false);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    // finish and close the activity and return bool response
    private boolean finishActivity(boolean b) {
        Intent _result = new Intent();
        // return if we cancel or launch the visit
        _result.putExtra("LaunchFlag", b);
        _result.putExtra("Visit", visit);
        setResult(Activity.RESULT_OK, _result);
        finish();
        return true;
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
