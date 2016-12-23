package com.ceri.visitemusee.basket;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.ScreenParam;
import com.ceri.visitemusee.tool.Tools;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Maxime
 */

public class BasketItemActivity extends AppCompatActivity {

    private BasketItem basketItem;

    @Bind(R.id.drawer_layout)
    DrawerLayout m_DrawerLayout;

    @Bind(R.id.toolbar)
    Toolbar m_Toolbar;

    @Bind(R.id.item_picture)
    ImageView itemPicture;

    @Bind(R.id.item_title)
    TextView itemTitle;

    @Bind(R.id.item_price)
    TextView itemPrice;

    @Bind(R.id.item_content)
    TextView itemContent;

    @Bind(R.id.addbasket)
    Button addBasket;

    private ActionBarDrawerToggle m_DrawerToggle;
    private ScreenParam param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObjects();
    }

    // initiate the objects and design
    private void initObjects() {
        setContentView(R.layout.activity_basket_item);
        ButterKnife.bind(this);

        //Getting the basket item
        basketItem = (BasketItem) getIntent().getSerializableExtra("Item");

        param = new ScreenParam();
        param.paramWindowFullScreen(getWindow());
        param.paramSetSupportActionBar(m_Toolbar, this);
        m_DrawerToggle = new ActionBarDrawerToggle(this, m_DrawerLayout, 0, 0);
        if(AppParams.getInstance().getM_french()) {
            // set text depending language
            renameActionBar(basketItem.getName_FR());
            itemTitle.setText(basketItem.getName_FR());
            itemContent.setText(basketItem.getPresentation_FR());
            addBasket.setText(MainActivity.getContext().getString(R.string.add_to_basket_fr));
        }
        else {
            // set text depending language
            itemTitle.setText(basketItem.getName_EN());
            itemContent.setText(basketItem.getPresentation_EN());
            renameActionBar(basketItem.getName_EN());
            addBasket.setText(MainActivity.getContext().getString(R.string.add_to_basket_en));
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(basketItem.getPicture(), itemPicture);
        itemPrice.setText(basketItem.getPrice() + getString(R.string.euro));

        addBasket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Basket.getInstance().addItem(basketItem);
                if(AppParams.getInstance().getM_french()) {
                    Tools.notifBar(v, getString(R.string.confirmation_add_basket_fr));
                }
                else {
                    Tools.notifBar(v, getString(R.string.confirmation_add_basket_en));
                }
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
