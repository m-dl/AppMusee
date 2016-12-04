package com.ceri.visitemusee.entities.musee;

import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.files.FileTools;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Maxime
 */
public class Visit implements Serializable {

    private ArrayList<InterestPoint> IP;
    private File name_EN;
    private String name, nameEN;

    public Visit(String pathFrom, String name) {
    	if(!FileTools.Exist(new File(pathFrom)))
            FileTools.CreateDirectory(pathFrom);
		this.name_EN = new File(pathFrom + "/" + FileManager.NAME_EN);
    	
        initVisit(pathFrom);

        this.setIP(new ArrayList<InterestPoint>());
        this.name = name;
        this.nameEN = readName_EN();
    }

    // check and create files if not exist
    private void initVisit(String pathFrom) {
		if(!FileTools.Exist(this.name_EN))
			FileTools.CreateFile(pathFrom + "/" + FileManager.NAME_EN);
    }

    public ArrayList<InterestPoint> getIP() {
        return IP;
    }

    public void setIP(ArrayList<InterestPoint> IP) {
        this.IP = IP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public File getName_EN() {
        return name_EN;
    }

    public void setName_EN(File name_EN) {
        this.name_EN = name_EN;
    }

    public String readName_EN() {
		return FileTools.Read(this.name_EN);
	}

    // Add an interest point to a visit
    public void addInterestPoint(InterestPoint IP, ArrayList<InterestPoint> IPArray) {
        IPArray.add(IP);
    }
	
}
