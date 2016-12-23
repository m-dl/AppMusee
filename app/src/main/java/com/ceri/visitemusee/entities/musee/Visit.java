package com.ceri.visitemusee.entities.musee;

import com.ceri.visitemusee.basket.BasketItem;
import com.ceri.visitemusee.files.FileTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Maxime
 */
public class Visit implements Serializable {

    private ArrayList<BasketItem> BI;
    private ArrayList<InterestPoint> IP, customIP;
    private ArrayList<String> customItemsFR, customItemsEN, customsRooms, customArtists;
    private String name_FR, name_EN;

    public Visit(String name_FR, String name_EN) {
        this.name_FR = name_FR;
        this.name_EN = name_EN;
        // load all IP once
        this.setIP(FileTools.JSONToIP());
        // load all BI once
        this.setBI(FileTools.JSONToItem());
        // associate all BI to IP
        FileTools.ItemInIP(this.getIP(), this.getBI());
        // load customIP list in array
        customIP = new ArrayList<>();

        // get customs data list
        Set<String> setItemsEN = new LinkedHashSet<>();
        Set<String> setItemsFR = new LinkedHashSet<>();
        Set<String> setArtists = new LinkedHashSet<>();
        Set<String> setRooms = new LinkedHashSet<>();

        for(InterestPoint IP : this.getIP()) {
            setItemsEN.add(IP.getType_EN());
            setItemsFR.add(IP.getType_FR());
            setArtists.add(IP.getAuthor());
            setRooms.add(IP.getRoom());
        }
        this.customItemsEN = new ArrayList<>(setItemsEN);
        this.customItemsFR = new ArrayList<>(setItemsFR);
        this.customArtists = new ArrayList<>(setArtists);
        this.customsRooms = new ArrayList<>(setRooms);
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

    public ArrayList<BasketItem> getBI() {
        return BI;
    }

    public void setBI(ArrayList<BasketItem> BI) {
        this.BI = BI;
    }

    public ArrayList<InterestPoint> getCustomIP() {
        return customIP;
    }

    public void setCustomIP(ArrayList<InterestPoint> customIP) {
        this.customIP = customIP;
    }

    public ArrayList<String> getCustomArtists() {
        return customArtists;
    }

    public void setCustomArtists(ArrayList<String> customArtists) {
        this.customArtists = customArtists;
    }

    public ArrayList<String> getCustomsRooms() {
        return customsRooms;
    }

    public void setCustomsRooms(ArrayList<String> customsRooms) {
        this.customsRooms = customsRooms;
    }

    public ArrayList<String> getCustomItemsEN() {
        return customItemsEN;
    }

    public void setCustomItemsEN(ArrayList<String> customItemsEN) {
        this.customItemsEN = customItemsEN;
    }

    public ArrayList<String> getCustomItemsFR() {
        return customItemsFR;
    }

    public void setCustomItemsFR(ArrayList<String> customItemsFR) {
        this.customItemsFR = customItemsFR;
    }
}
