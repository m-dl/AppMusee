package com.ceri.visitemusee.interestpoint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tool.Tools;

/**
 * Created by Maxime
 */

public class InterestPointShopFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    InterestPoint IP;

    ListView itemList;

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

        // display basket items
        Tools.displayItemList(itemList, AppParams.getInstance().getCurrentVisit().getBI(), true);

        return v;
    }
}