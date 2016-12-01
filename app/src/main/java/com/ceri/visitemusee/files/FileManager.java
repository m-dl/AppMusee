package com.ceri.visitemusee.files;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.NavigationView;

import com.ceri.visitemusee.main.MainActivity;
import com.ceri.visitemusee.R;
import com.ceri.visitemusee.entities.musee.Location;
import com.ceri.visitemusee.entities.musee.Visit;

import java.io.File;

/**
 * Created by Maxime
 */
public class FileManager {
    final public static File SD_CARD = Environment.getExternalStorageDirectory();
    final public static String MEDIA_PATH = "/VisiteMusee";
    final public static String PHOTOS = "photo";
    final public static String INTERIEUR = "interieur";
    final public static String VIDEOS = "video";
    final public static String _360 = "360";
    final public static String OVERVIEW_FOLDER = "visite-overview";
    final public static String INFO_FOLDER = "visite-info";
    final public static String NAME_EN = "name_en.txt";
    final public static String PRESENTATION_FR = "content_fr.txt";
    final public static String PRESENTATION_EN = "content_en.txt";
    final public static String LENGTH_FR = "duree_fr.txt";
    final public static String LENGTH_EN = "duree_en.txt";
    final public static String MARKER = "marker.txt";
    private Location MuseeWorkspace;

    private static FileManager INSTANCE = new FileManager();

    // Singleton
    public static FileManager getInstance() {
        return INSTANCE;
    }

    public FileManager() {
        Init();
    }

    /**
     * @return the MuseeWorkspace
     */
    public Location getMuseeWorkspace() {
        return MuseeWorkspace;
    }

    /**
     * @param MuseeWorkspace the MuseeWorkspace to set
     */
    public void setMuseeWorkspace(Location MuseeWorkspace) {
        this.MuseeWorkspace = MuseeWorkspace;
    }

    // Init global media workspace
    public void Init() {
        this.setMuseeWorkspace(new Location());
        InitMusee();
    }

    // Init Musee media workspace
    public void InitMusee() {
        FileTools.ListVisitMusee(SD_CARD + MEDIA_PATH, MuseeWorkspace);
    }
    
    // Create menu and list visits
    public static void ListVisits(NavigationView m_NavigationView, boolean french) {
        String name;
        int groupId = 0, itemId = 1, order = 0;
        if (FileManager.getInstance().getMuseeWorkspace().getV() != null) {
            for (Visit v : FileManager.getInstance().getMuseeWorkspace().getV()) {
                if(french)
                    name = v.getName();
                else
                    name = v.getNameEN();
                m_NavigationView.getMenu().add(groupId, itemId, order, name);
                itemId++;
                order++;
            }
        }
//        if(!FileManager.getInstance().getMuseeWorkspace().getV().isEmpty())
//            order--;
//        if (french) {
//            m_NavigationView.getMenu().addSubMenu(groupId, itemId, order, R.string.option_section).add(R.string.action_section_1);
//        } else {
//            m_NavigationView.getMenu().addSubMenu(groupId, itemId, order, R.string.option_section).add(R.string.action_section_english_1);
//        }
    }
}
