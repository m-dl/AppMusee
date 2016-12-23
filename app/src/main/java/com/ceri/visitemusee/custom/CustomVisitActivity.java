package com.ceri.visitemusee.custom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.MultiSelectionSpinner;
import com.ceri.visitemusee.tool.ScreenParam;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Maxime
 */

public class CustomVisitActivity extends AppCompatActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout m_DrawerLayout;

    @Bind(R.id.toolbar)
    Toolbar m_Toolbar;

    @Bind(R.id.cutom_artists_text)
    TextView visitArtists;

    @Bind(R.id.cutom_rooms_text)
    TextView visitRooms;

    @Bind(R.id.cutom_items_text)
    TextView visitItems;

    @Bind(R.id.cutom_text)
    TextView customText;

    @Bind(R.id.cutom_rooms_spinner)
    MultiSelectionSpinner visitRoomsSpinner;

    @Bind(R.id.cutom_artists_spinner)
    MultiSelectionSpinner visitArtistsSpinner;

    @Bind(R.id.cutom_items_spinner)
    MultiSelectionSpinner visitItemsSpinner;

    @Bind(R.id.startcustom)
    Button startCustom;

    private ActionBarDrawerToggle m_DrawerToggle;
    private ScreenParam param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObjects();

        visitRoomsSpinner.setItems(AppParams.getInstance().getCurrentVisit().getCustomsRooms());
        visitArtistsSpinner.setItems(AppParams.getInstance().getCurrentVisit().getCustomArtists());
        if(AppParams.getInstance().getM_french())
            visitItemsSpinner.setItems(AppParams.getInstance().getCurrentVisit().getCustomItemsFR());
        else
            visitItemsSpinner.setItems(AppParams.getInstance().getCurrentVisit().getCustomItemsEN());
    }


    // initiate the objects and design
    private void initObjects() {
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);
        param = new ScreenParam();
        param.paramWindowFullScreen(getWindow());
        param.paramSetSupportActionBar(m_Toolbar, this);
        m_DrawerToggle = new ActionBarDrawerToggle(this, m_DrawerLayout, 0, 0);
        if(AppParams.getInstance().getM_french()) {
            renameActionBar(getString(R.string.action_section_2));
            customText.setText(getString(R.string.custom_message_fr));
            visitItems.setText(getString(R.string.items_fr));
            visitRooms.setText(getString(R.string.rooms_fr));
            visitArtists.setText(getString(R.string.artists_fr));
            visitItemsSpinner.setTitle(getString(R.string.items_fr));
            visitRoomsSpinner.setTitle(getString(R.string.rooms_fr));
            visitArtistsSpinner.setTitle(getString(R.string.artists_fr));
            startCustom.setText(getString(R.string.démarrer));
        }
        else {
            renameActionBar(getString(R.string.action_section_en_2));
            customText.setText(getString(R.string.custom_message_en));
            visitItems.setText(getString(R.string.items_en));
            visitRooms.setText(getString(R.string.rooms_en));
            visitArtists.setText(getString(R.string.artists_en));
            visitItemsSpinner.setTitle(getString(R.string.items_en));
            visitRoomsSpinner.setTitle(getString(R.string.rooms_en));
            visitArtistsSpinner.setTitle(getString(R.string.artists_en));
            startCustom.setText(getString(R.string.start));
        }

        startCustom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishActivity(true);
            }
        });
    }

    // set action bar text
    private void renameActionBar(String s) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(s);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finishActivity(false);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    // finish and close the activity and return bool response
    private boolean finishActivity(boolean b) {
        Intent _result = new Intent();
        // return if we cancel or launch the visit
        _result.putExtra("launchFlag", b);
        _result.putExtra("visitRoomsSpinner", (Serializable) visitRoomsSpinner.getSelectedStrings());
        _result.putExtra("visitArtistsSpinner", (Serializable) visitArtistsSpinner.getSelectedStrings());
        _result.putExtra("visitItemsSpinner", (Serializable) visitItemsSpinner.getSelectedStrings());
        setResult(Activity.RESULT_OK, _result);
        finish();
        return true;
    }
}
