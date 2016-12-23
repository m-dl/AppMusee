package com.ceri.visitemusee.custom;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.ScreenParam;

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

    @Bind(R.id.cutom_paintings_text)
    TextView visitPaintings;

    @Bind(R.id.cutom_statues_text)
    TextView visitStatues;

    @Bind(R.id.cutom_items_text)
    TextView visitItems;

    @Bind(R.id.cutom_text)
    TextView customText;

    @Bind(R.id.cutom_paintings_checkbox)
    CheckBox visitPaintingsCheckbox;

    @Bind(R.id.cutom_statues_checkbox)
    CheckBox visitStatuesCheckbox;

    @Bind(R.id.cutom_items_checkbox)
    CheckBox visitItemsCheckbox;

    @Bind(R.id.startcustom)
    Button startCustom;

    private ActionBarDrawerToggle m_DrawerToggle;
    private ScreenParam param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObjects();
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
            startCustom.setText(getString(R.string.démarrer));
        }
        else {
            renameActionBar(getString(R.string.action_section_en_2));
            customText.setText(getString(R.string.custom_message_en));
            startCustom.setText(getString(R.string.start));
        }
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
