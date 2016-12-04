package com.ceri.visitemusee.files;

import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.ceri.visitemusee.R;

import java.io.File;

/**
 * Created by Maxime
 */
public class FileManager {
    final public static String PHOTOS = "photo";
    final public static String VIDEOS = "video";
    final public static String _360 = "360";
    final public static String NAME_EN = "name_en.txt";
    final public static String PRESENTATION_FR = "content_fr.txt";
    final public static String PRESENTATION_EN = "content_en.txt";
    final public static String MARKER = "marker.txt";

    private static FileManager INSTANCE = new FileManager();

    // Singleton
    public static FileManager getInstance() {
        return INSTANCE;
    }

    // Rename menu
    public static void renameMenuItems(NavigationView m_NavigationView, boolean french) {
        MenuItem titleVisitsItem = m_NavigationView.getMenu().findItem(R.id.title_visits_item);
        MenuItem allVisitsItem = m_NavigationView.getMenu().findItem(R.id.all_visits_item);
        MenuItem customVisitsItem = m_NavigationView.getMenu().findItem(R.id.custom_visits_item);
        MenuItem basketItem = m_NavigationView.getMenu().findItem(R.id.basket_item);

        if (!french) {
            titleVisitsItem.setTitle(R.string.action_section_en_0);
            allVisitsItem.setTitle(R.string.action_section_en_1);
            customVisitsItem.setTitle(R.string.action_section_en_2);
            basketItem.setTitle(R.string.action_section_en_3);
        }
    }
}
