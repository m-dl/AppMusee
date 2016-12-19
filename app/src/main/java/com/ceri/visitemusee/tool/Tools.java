package com.ceri.visitemusee.tool;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.ceri.visitemusee.main.MainActivity;

/**
 * Created by Maxime
 */

public class Tools {

    // 8 museum maps
    public static final int MAP_ONE = 1;
    public static final int MAP_TWO = 2;
    public static final int MAP_THREE = 3;
    public static final int MAP_FOUR = 4;
    public static final int MAP_FIVE = 5;
    public static final int MAP_SIX = 6;
    public static final int MAP_SEVEN = 7;
    public static final int MAP_HEIGHT = 8;
    public static final String ROOM = "Room";

    public static int distanceToRange(double distance) {
        if(distance < 1)
            return MAP_TWO;
        else if(distance < 3)
            return MAP_THREE;
        else if(distance < 7)
            return MAP_FOUR;
        else if(distance < 12)
            return MAP_FIVE;
        else if(distance < 16)
            return MAP_SIX;
        else if(distance < 20)
            return MAP_SEVEN;
        else if(distance < 25)
            return MAP_HEIGHT;
        return MAP_ONE;
    }

    // display toast message
    public static void notifToast(String s) {
        Toast.makeText(MainActivity.getContext(), s, Toast.LENGTH_SHORT).show();
    }

    // display snackbar message
    public static void notifBar(View view, String s) {
        Snackbar.make(view, s, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}
