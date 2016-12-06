package com.ceri.visitemusee.entities.musee;

import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.files.FileTools;
import com.ceri.visitemusee.params.AppParams;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Maxime
 */
public class Visit implements Serializable {

    private ArrayList<InterestPoint> IP;
    private String name_FR, name_EN;

    public Visit(String name_FR, String name_EN) {
        this.name_FR = name_FR;
        this.name_EN = name_EN;
        this.setIP(AppParams.getInstance().getAppIPList());
    }

    public String getName_EN() {
        return name_EN;
    }

    public void setName_EN(String name_EN) {
        this.name_EN = name_EN;
    }

    public String getName_FR() {
        return name_FR;
    }

    public void setName_FR(String name_FR) {
        this.name_FR = name_FR;
    }

    public ArrayList<InterestPoint> getIP() {
        return IP;
    }

    public void setIP(ArrayList<InterestPoint> IP) {
        this.IP = IP;
    }
}
