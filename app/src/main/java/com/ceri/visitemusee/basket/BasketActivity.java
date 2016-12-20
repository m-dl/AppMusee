package com.ceri.visitemusee.basket;

import android.app.LoaderManager;
import android.content.Intent;
import android.database.Cursor;
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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.custom.CustomVisitActivity;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.ItemAdapter;
import com.ceri.visitemusee.tool.ScreenParam;
import com.ceri.visitemusee.tool.Tools;

import java.util.List;

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

    @Bind(R.id.validatebaskettext)
    TextView validateBasketText;

    @Bind(R.id.validatebasketbutton)
    Button validateBasketButton;

    @Bind(R.id.emptybasketbutton)
    Button emptyBasketButton;

    private ActionBarDrawerToggle m_DrawerToggle;
    private ScreenParam param;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObjects();

        // set text depending language
        validateBasketText.setText(Basket.getInstance().getBasketObjectsText());
        validateBasketButton.setText(Basket.getInstance().getBasketValidateButtonText());
        emptyBasketButton.setText(Basket.getInstance().getBasketEmptyButtonText());

        list = (ListView) findViewById(R.id.item_list);
        displayCitiesList(list, Basket.getInstance().getItems());

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

    // display list of element
    public void displayCitiesList(ListView CListView, List<BasketItem> C) {
        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), C);
        CListView.setAdapter(adapter);
        int numberOfItems = adapter.getCount();

        // Get total height of all items.
        int totalItemsHeight = 0;
        // order true to display most recent items first
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = adapter.getView(itemPos, null, CListView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }
        // Get total height of all item dividers.
        int totalDividersHeight = CListView.getDividerHeight() *
                (numberOfItems - 1);

        // Set list height.
        ViewGroup.LayoutParams params = CListView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        CListView.setLayoutParams(params);
        CListView.requestLayout();
    }

    // initiate the objects and design
    private void initObjects() {
        setContentView(R.layout.activity_basket);
        ButterKnife.bind(this);
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
