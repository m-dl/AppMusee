package com.ceri.visitemusee.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ceri.visitemusee.main.MainActivity;

/**
 * Created by Maxime
 */
public class ConnectionManager {

    // check if network is available
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
