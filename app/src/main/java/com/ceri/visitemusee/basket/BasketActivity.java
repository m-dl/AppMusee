package com.ceri.visitemusee.basket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.custom.CustomVisitActivity;
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

    @Bind(R.id.navigationView)
    NavigationView m_NavigationView;

    @Bind(R.id.toolbar)
    Toolbar m_Toolbar;

    @Bind(R.id.validatebaskettext)
    TextView validateBasketText;

    @Bind(R.id.validatebasketbutton)
    Button validateBasketButton;

    @Bind(R.id.emptybasketbutton)
    Button emptyBasketButton;

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

        validateBasketButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.m_Activity, GenerateBasketActivity.class);
                ActivityCompat.startActivity(MainActivity.m_Activity, intent, null);
            }
        });

        emptyBasketButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Basket.getInstance().emptyBasket();
                if(AppParams.getInstance().getM_french())
                    Tools.notifBar(v, getString(R.string.confirmation_empty_basket_fr));
                else
                    Tools.notifBar(v, getString(R.string.confirmation_empty_basket_en));
                // TODO: vider la vue du panier et tout le reste (prix)
                // TODO: bug du nenu dans le panier
            }
        });
    }

    // initiate the objects and design
    private void initObjects() {
        setContentView(R.layout.activity_basket);
        ButterKnife.bind(this);
        param = new ScreenParam();
        param.paramWindowFullScreen(getWindow());
        param.paramSetSupportActionBar(m_Toolbar, this);
        m_DrawerToggle = new ActionBarDrawerToggle(this, m_DrawerLayout, 0, 0);
        setDrawer();
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

    // initiate the drawer design for the menu
    public void setDrawer() {
        m_DrawerLayout.setDrawerListener(m_DrawerToggle);
        m_NavigationView.getMenu().findItem(R.id.all_visits_item).setChecked(true);
        m_NavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    MenuItem m_menuItem;

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        m_DrawerLayout.closeDrawers();
                        m_menuItem = menuItem;
                        if(!menuItem.isChecked()) {
                            menuItem.setChecked(true);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    navigationDrawerItemSelected(m_menuItem);
                                }
                            }, 250);
                        }
                        return false;
                    }
                });
    }

    // get item clicked in the menu
    public void navigationDrawerItemSelected(MenuItem menuItem) {
        // full visit item
        if (menuItem.getItemId() == R.id.all_visits_item) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        // custom visit item
        else if (menuItem.getItemId() == R.id.custom_visits_item) {
            Intent intent = new Intent(MainActivity.m_Activity, CustomVisitActivity.class);
            ActivityCompat.startActivity(MainActivity.m_Activity, intent, null);
        }
        // basket item
        else if (menuItem.getItemId() == R.id.basket_item) {
            Intent intent = new Intent(MainActivity.m_Activity, BasketActivity.class);
            ActivityCompat.startActivity(MainActivity.m_Activity, intent, null);
        }
    }
}
