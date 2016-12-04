package com.ceri.visitemusee.tool;

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
}
