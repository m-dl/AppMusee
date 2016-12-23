package com.ceri.visitemusee.interestpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.basket.BasketItem;
import com.ceri.visitemusee.basket.BasketItemActivity;
import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.Tools;

import java.util.ArrayList;

/**
 * Created by Maxime
 */

public class InterestPointShopFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    InterestPoint IP;

    ListView itemList;
    TextView shopTabText;
    Spinner priceSpinner;

    public static InterestPointShopFragment newInstance(int page, InterestPoint IP) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putSerializable(FileManager.IP, IP);
        InterestPointShopFragment fragment = new InterestPointShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ip_shop, container, false);
        IP = (InterestPoint) getArguments().getSerializable(FileManager.IP);

        itemList = (ListView) v.findViewById(R.id.item_list);
        shopTabText = (TextView) v.findViewById(R.id.shoptabtext);
        priceSpinner = (Spinner) v.findViewById(R.id.sortitems);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.m_Activity, BasketItemActivity.class);
                intent.putExtra("Item", (BasketItem) itemList.getItemAtPosition(position));
                ActivityCompat.startActivity(MainActivity.m_Activity, intent, null);
            }
        });

        // display basket items
        Tools.displayItemList(itemList, IP.getBasketItemList(), true);
        shopTabText.setText(Tools.getBasketNumberText(IP.getBasketItemList()));

        // price sort spinner
        ArrayAdapter<CharSequence> spinnerArrayAdapter;
        if(AppParams.getInstance().getM_french())
            spinnerArrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.price_sort_fr, R.layout.spinner_custom_item);
        else
            spinnerArrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.price_sort_en, R.layout.spinner_custom_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceSpinner.setAdapter(spinnerArrayAdapter);

        priceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1) {
                    ArrayList<BasketItem> basketItems = (ArrayList<BasketItem>) IP.getBasketItemList().clone();
                    Tools.sortPriceUp(basketItems);
                    Tools.displayItemList(itemList, basketItems, true);
                }
                else if(position == 2) {
                    ArrayList<BasketItem> basketItems = (ArrayList<BasketItem>) IP.getBasketItemList().clone();
                    Tools.sortPriceDown(basketItems);
                    Tools.displayItemList(itemList, basketItems, true);
                }
                else {
                    Tools.displayItemList(itemList, IP.getBasketItemList(), true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }
}