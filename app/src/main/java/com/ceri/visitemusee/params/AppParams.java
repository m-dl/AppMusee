package com.ceri.visitemusee.params;

import com.ceri.visitemusee.entities.musee.Location;
import com.ceri.visitemusee.entities.musee.Visit;

/**
 * Created by Maxime
 */
public class AppParams {
    public static AppParams instance;
    public static final int THUMB_SIZE = 200;

    // singleton
    public static AppParams getInstance() {
        if (instance == null)
            instance = new AppParams();
        return instance;
    }

    // language
    private boolean m_french;
    // current floor we are on
    private int currentFloor;
    // current visit we are on
    private Visit currentVisit;

    private AppParams() {
        m_french = true;
        currentFloor = Location.FLOOR_ONE;
        currentVisit = null;
    }
    public boolean getM_french() {
        return m_french;
    }

    public void setM_french(boolean m_french) {
        this.m_french = m_french;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Visit getCurrentVisit() {
        return currentVisit;
    }

    public void setCurrentVisit(Visit currentVisit) {
        this.currentVisit = currentVisit;
    }
}
