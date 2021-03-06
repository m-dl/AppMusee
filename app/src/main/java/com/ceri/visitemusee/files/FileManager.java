package com.ceri.visitemusee.files;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.ceri.visitemusee.R;

/**
 * Created by Maxime
 */
public class FileManager {
    // json tags / folders path
    final public static String ASSETS = "assets://";
    final public static String MUSEUM_FOLDER = "museum/";
    final public static String SHOP_FOLDER = "shop/";
    final public static String RESSOURCES = "android.resource://";
    final public static String RAW = "raw/";
    final public static String JSON_FOLDER = "json/";
    final public static String PICTURES_FOLDER = "pictures/";
    final public static String _360_FOLDER = "360/";
    final public static String MUSEUM_JSON = MUSEUM_FOLDER + JSON_FOLDER + "data.json";
    final public static String SHOP_JSON = SHOP_FOLDER + JSON_FOLDER + "data.json";
    final public static String VISIT = "visit";
    final public static String SHOP = "shop";
    final public static String IP = "ip";
    final public static String ITEM = "item";
    final public static String PHOTOS = "pictures";
    final public static String PICTURE = "picture";
    final public static String VIDEOS = "videos";
    final public static String _360 = "p360";
    final public static String NAME_FR = "title_fr";
    final public static String NAME_EN = "title_en";
    final public static String PRESENTATION_FR = "presentation_fr";
    final public static String PRESENTATION_EN = "presentation_en";
    final public static String PRICE = "price";
    final public static String COORD_X = "coordx";
    final public static String COORD_Y = "coordy";
    final public static String AUTHOR = "author";
    final public static String TYPE = "type";
    final public static String TYPE_FR = "type_fr";
    final public static String TYPE_EN = "type_en";
    final public static String ROOM = "room";
    final public static String LINK = "lien";

    private static FileManager INSTANCE;

    // singleton
    public static FileManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FileManager();
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
