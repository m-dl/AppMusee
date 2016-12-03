package com.ceri.visitemusee.tool;

import com.ceri.visitemusee.entities.musee.Location;

/**
 * Created by Maxime
 */

public class Tools {

    public static int distanceToRange(double distance) {
        if(distance < 1)
            return Location.MAP_TWO;
        else if(distance < 3)
            return Location.MAP_THREE;
        else if(distance < 7)
            return Location.MAP_FOUR;
        else if(distance < 12)
            return Location.MAP_FIVE;
        else if(distance < 16)
            return Location.MAP_SIX;
        else if(distance < 20)
            return Location.MAP_SEVEN;
        else if(distance < 25)
            return Location.MAP_HEIGHT;
        return Location.MAP_ONE;
    }
}
