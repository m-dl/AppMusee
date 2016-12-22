package com.ceri.visitemusee.tool;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.interestpoint.InterestPoint360Fragment;
import com.ceri.visitemusee.interestpoint.InterestPointFragment;
import com.ceri.visitemusee.interestpoint.InterestPointPicturesFragment;
import com.ceri.visitemusee.interestpoint.InterestPointShopFragment;
import com.ceri.visitemusee.interestpoint.InterestPointVideosFragment;
import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.params.AppParams;

/**
 * Created by Maxime
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 5;
    private String tabTitles_fr[] = new String[] { MainActivity.getContext().getString(R.string.info_fr),
            MainActivity.getContext().getString(R.string.images), MainActivity.getContext().getString(R.string.images360),
            MainActivity.getContext().getString(R.string.videos), MainActivity.getContext().getString(R.string.shop_fr)};
    private String tabTitles_en[] = new String[] { MainActivity.getContext().getString(R.string.info_en),
            MainActivity.getContext().getString(R.string.pictures), MainActivity.getContext().getString(R.string.pictures360),
            MainActivity.getContext().getString(R.string.videosen), MainActivity.getContext().getString(R.string.shop_en)};
    private Context context;
    private InterestPoint IP;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context, InterestPoint IP) {
        super(fm);
        this.context = context;
        this.IP = IP;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return InterestPointFragment.newInstance(position + 1, IP);
            case 1:
                return InterestPointPicturesFragment.newInstance(position + 1, IP);
            case 2:
                return InterestPoint360Fragment.newInstance(position + 1, IP);
            case 3:
                return InterestPointVideosFragment.newInstance(position + 1, IP);
            case 4:
                return InterestPointShopFragment.newInstance(position + 1, IP);
            default:
                return InterestPointFragment.newInstance(position + 1, IP);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        if(!AppParams.getInstance().getM_french())
            return tabTitles_en[position];
        return tabTitles_fr[position];
    }
}