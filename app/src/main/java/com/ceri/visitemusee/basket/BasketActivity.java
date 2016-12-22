package com.ceri.visitemusee.basket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.ScreenParam;
import com.ceri.visitemusee.tool.Tools;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Maxime
 */

public class BasketActivity extends AppCompatActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout m_DrawerLayout;

    @Bind(R.id.toolbar)
    Toolbar m_Toolbar;

    public static TextView validateBasketText;

    @Bind(R.id.validatebasketbutton)
    Button validateBasketButton;

    @Bind(R.id.emptybasketbutton)
    Button emptyBasketButton;

    @Bind(R.id.item_list)
    ListView itemList;

    private ActionBarDrawerToggle m_DrawerToggle;
    private ScreenParam param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObjects();

        // set text depending language
        validateBasketText.setText(Basket.getInstance().getBasketObjectsText());
        validateBasketButton.setText(Basket.getInstance().getBasketValidateButtonText());
        emptyBasketButton.setText(Basket.getInstance().getBasketEmptyButtonText());

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.m_Activity, BasketItemActivity.class);
                intent.putExtra("Item", (BasketItem) itemList.getItemAtPosition(position));
                ActivityCompat.startActivity(MainActivity.m_Activity, intent, null);
            }
        });

        // display basket items
        Tools.displayItemList(itemList, Basket.getInstance().getItems(), false);

        validateBasketButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.m_Activity, GenerateBasketActivity.class);
                ActivityCompat.startActivity(MainActivity.m_Activity, intent, null);
            }
        });

        emptyBasketButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Basket.getInstance().emptyBasket();
                validateBasketText.setText(Basket.getInstance().getBasketObjectsText());
                Tools.displayItemList(itemList, Basket.getInstance().getItems(), false);
                if(AppParams.getInstance().getM_french())
                    Tools.notifBar(v, getString(R.string.confirmation_empty_basket_fr));
                else
                    Tools.notifBar(v, getString(R.string.confirmation_empty_basket_en));
            }
        });
    }

    // initiate the objects and design
    private void initObjects() {
        setContentView(R.layout.activity_basket);
        ButterKnife.bind(this);
        validateBasketText = (TextView) findViewById(R.id.validatebaskettext);
        param = new ScreenParam();
        param.paramWindowFullScreen(getWindow());
        param.paramSetSupportActionBar(m_Toolbar, this);
        m_DrawerToggle = new ActionBarDrawerToggle(this, m_DrawerLayout, 0, 0);
        if(AppParams.getInstance().getM_french())
            renameActionBar(getString(R.string.action_section_3));
        else
            renameActionBar(getString(R.string.action_section_en_3));
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
