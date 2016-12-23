package com.ceri.visitemusee.params;

import com.ceri.visitemusee.entities.musee.Visit;

/**
 * Created by Maxime
 */
public class AppParams {
    public static AppParams instance;

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

    private AppParams() {
        m_french = true;
        currentVisit = null;
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
}
