package com.ceri.visitemusee.params;

import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.entities.musee.Visit;
import com.ceri.visitemusee.files.FileTools;

import java.util.ArrayList;

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
    // current visit we are on
    private Visit currentVisit;

    private ArrayList<InterestPoint> appIPList;

    private AppParams() {
        m_french = true;
        currentVisit = null;
        // load all IP once
        this.appIPList = FileTools.JSONToIP();
    }
    public boolean getM_french() {
        return m_french;
    }

    public void setM_french(boolean m_french) {
        this.m_french = m_french;
    }

    public Visit getCurrentVisit() {
        return currentVisit;
    }

    public void setCurrentVisit(Visit currentVisit) {
        this.currentVisit = currentVisit;
    }

    public ArrayList<InterestPoint> getAppIPList() {
        return appIPList;
    }

    public void setAppIPList(ArrayList<InterestPoint> appIPList) {
        this.appIPList = appIPList;
    }
}
