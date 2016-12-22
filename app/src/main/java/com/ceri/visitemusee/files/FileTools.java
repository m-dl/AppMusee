package com.ceri.visitemusee.files;

import com.ceri.visitemusee.basket.BasketItem;
import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.main.MainActivity;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author Maxime
 */
public class FileTools {

	public static String removeExtension(String s) {
		int pos = s.lastIndexOf(".");
		if (pos > 0) {
			s = s.substring(0, pos);
		}
		return s;
	}

	static String readFile(InputStream inputStream) {
		try {
			StringWriter writer = new StringWriter();
			IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
			return writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// decode json object
	public static ArrayList<InterestPoint> JSONToIP() {
		try {
			MainActivity.getContext().getResources().getAssets();
			String s = null;
			try {
				s = readFile(MainActivity.getContext().getResources().getAssets().open(FileManager.MUSEUM_JSON));
			} catch (IOException e) {
				e.printStackTrace();
			}
			ArrayList<InterestPoint> IPList = new ArrayList<>();
			JSONObject reader;
			JSONObject object = new JSONObject(s);
			object = object.getJSONObject(FileManager.VISIT);
			JSONArray jArray = object.getJSONArray(FileManager.IP);
			for (int i = 0; i < jArray.length(); i++) {
				reader = jArray.getJSONObject(i);
				String title_en, title_fr, presentation_fr, presentation_en, author, type_en, type_fr;
				double coordx, coordy;
				ArrayList<String> pictures = new ArrayList<>(), p360 = new ArrayList<>();
				ArrayList<File> videos = new ArrayList<>();

				title_fr = reader.getString(FileManager.NAME_FR);
				title_en = reader.getString(FileManager.NAME_EN);
				presentation_fr = reader.getString(FileManager.PRESENTATION_FR);
				presentation_en = reader.getString(FileManager.PRESENTATION_EN);
				coordx = Double.parseDouble(reader.getString(FileManager.COORD_X));
				coordy = Double.parseDouble(reader.getString(FileManager.COORD_Y));
				author = reader.getString(FileManager.AUTHOR);
				type_fr = reader.getString(FileManager.TYPE_FR);
				type_en = reader.getString(FileManager.TYPE_EN);

				JSONObject tmpobject = reader.getJSONObject(FileManager.PHOTOS);
				JSONArray tmpjArray = tmpobject.getJSONArray(FileManager.LINK);
				for (int j = 0; j < tmpjArray.length(); j++) {
					pictures.add(FileManager.ASSETS + FileManager.MUSEUM_FOLDER + FileManager.PICTURES_FOLDER + tmpjArray.getString(j).replaceAll("\\s+",""));
				}

				tmpobject = reader.getJSONObject(FileManager._360);
				tmpjArray = tmpobject.getJSONArray(FileManager.LINK);
				for (int j = 0; j < tmpjArray.length(); j++) {
					p360.add(FileManager.ASSETS + FileManager.MUSEUM_FOLDER + FileManager._360_FOLDER + tmpjArray.getString(j).replaceAll("\\s+",""));
				}

				tmpobject = reader.getJSONObject(FileManager.VIDEOS);
				tmpjArray = tmpobject.getJSONArray(FileManager.LINK);
				for (int j = 0; j < tmpjArray.length(); j++) {
					videos.add(new File(removeExtension(tmpjArray.getString(j).replaceAll("\\s+",""))));
				}

				IPList.add(new InterestPoint(title_fr, title_en, presentation_fr, presentation_en, author, type_fr, type_en, coordx, coordy, pictures, p360, videos));
			}
			return IPList;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	// decode json object
	public static ArrayList<BasketItem> JSONToItem() {
		try {
			MainActivity.getContext().getResources().getAssets();
			String s = null;
			try {
				s = readFile(MainActivity.getContext().getResources().getAssets().open(FileManager.SHOP_JSON));
			} catch (IOException e) {
				e.printStackTrace();
			}
			ArrayList<BasketItem> BIList = new ArrayList<>();
			JSONObject reader;
			JSONObject object = new JSONObject(s);
			object = object.getJSONObject(FileManager.SHOP);
			JSONArray jArray = object.getJSONArray(FileManager.ITEM);
			for (int i = 0; i < jArray.length(); i++) {
				reader = jArray.getJSONObject(i);
				String name_en, name_fr, presentation_fr, presentation_en, type, picture;
				double price;

				name_fr = reader.getString(FileManager.NAME_FR);
				name_en = reader.getString(FileManager.NAME_EN);
				presentation_fr = reader.getString(FileManager.PRESENTATION_FR);
				presentation_en = reader.getString(FileManager.PRESENTATION_EN);
				price = Double.parseDouble(reader.getString(FileManager.PRICE));
				type = reader.getString(FileManager.TYPE);
				picture = FileManager.ASSETS + FileManager.SHOP_FOLDER + FileManager.PICTURES_FOLDER + reader.getString(FileManager.PICTURE).replaceAll("\\s+","");

				BIList.add(new BasketItem(price, name_fr, name_en, presentation_fr, presentation_en, type, picture));
			}
			return BIList;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}