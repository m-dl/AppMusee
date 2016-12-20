package com.ceri.visitemusee.basket;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.ScreenParam;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GenerateBasketActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar m_Toolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout m_DrawerLayout;

    @Bind(R.id.generatebaskettext)
    TextView generateBasketText;

    @Bind(R.id.basketvaluetext)
    TextView basketValueText;

    @Bind(R.id.qrcode)
    ImageView qrcode;

    private ScreenParam param;
    private ActionBarDrawerToggle m_DrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObjects();

        // create qrcode bitmap
        qrcode.setImageBitmap(Basket.getInstance().generateQRCode());

        // set text depending language
        basketValueText.setText(Basket.getInstance().getBasketItemsText());
        generateBasketText.setText(Basket.getInstance().getQRCodeBasketText());
    }

    // initiate the objects and design
    private void initObjects() {
        setContentView(R.layout.activity_generate_basket);
        ButterKnife.bind(this);
        param = new ScreenParam();
        param.paramWindowFullScreen(getWindow());
        param.paramSetSupportActionBar(m_Toolbar, this);
        m_DrawerToggle = new ActionBarDrawerToggle(this, m_DrawerLayout, 0, 0);
        if(AppParams.getInstance().getM_french())
            renameActionBar(getString(R.string.validate_basket_fr));
        else
            renameActionBar(getString(R.string.validate_basket_en));
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
