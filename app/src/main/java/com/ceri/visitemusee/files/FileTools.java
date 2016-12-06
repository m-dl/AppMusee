package com.ceri.visitemusee.files;

import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.main.MainActivity;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author Maxime
 */
public class FileTools {
    public static final String[] IMAGES_EXTENSIONS = new String[]{
        "gif", "png", "bmp", "jpeg", "jpg", "GIF", "PNG", "BMP", "JPEG", "JPG"
    };

    public static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : IMAGES_EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    
    public static final String[] VIDEOS_EXTENSIONS = new String[]{
        "mp4", "avi", "mkv", "webm", "mov", "MP4", "AVI", "MKV", "WEBM", "MOV"
    };

    public static final FilenameFilter VIDEO_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : VIDEOS_EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

	// Create a file
	public static void CreateFile(String path) {
		try {
		      File file = new File(path);
		      if(!file.createNewFile()){ }
	    	} catch(IOException e) {
		      e.printStackTrace();
		}
	}
	
	// Create a directory
	public static void CreateDirectory(String path) {
		File dir = new File(path);
		// if the directory does not exist, create it
		if(Exist(dir)) { }
		else {
			try {
		        dir.mkdir();
		    } 
		    catch(SecurityException se){
		    	 se.printStackTrace();
		    }    
		}
	}
	
	// Read from a file
	public static String Read(File file) {
		String output = null;
	    try {
	        FileReader reader = new FileReader(file);
	        char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        output = new String(chars);
	        reader.close();
	    } catch(IOException e) {
	        e.printStackTrace();
	    }
		return output;
	}

	// Check if folder or file exists
	public static boolean Exist(File f) {
		if(f.exists())
			return true;
		return false;
	}

	// Delete a file / directory
	public static void Delete(String path) {
		File file = new File(path);
		if (Exist(file)) {
			try {
				FileUtils.forceDelete(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

    // Clear string all spaces / line jump
    public static String StringClearAllSpaces(String input) {
    	return input.replaceAll("\\s+",""); // removes spaces / line jump
    }
    
	// List all pictures from a folder
	public static ArrayList<File> ListFolderPictures(String p) {
		ArrayList<File> picturesList = new ArrayList<File>();
		File pathFrom = new File(p);
		if(!Exist(pathFrom)) 
			return picturesList;
		File[] list = pathFrom.listFiles(IMAGE_FILTER);
		for(File f : list) {
        	// add picture to arraylist
			picturesList.add(f);
        }
		return picturesList;
	}

	// List all videos from a folder
	public static ArrayList<File> ListFolderVideos(String p) {
		ArrayList<File> videosList = new ArrayList<File>();
		File pathFrom = new File(p);
		if(!Exist(pathFrom)) 
			return videosList;
		File[] list = pathFrom.listFiles(VIDEO_FILTER);
		for(File f : list) {
        	// add video to arraylist
			videosList.add(f);
        }
		return videosList;
	}
//
//	// List all Museum visits from a location
//	public static void ListVisitMusee(String pathFrom, Location l) {
//		File fileFrom = new File(pathFrom);
//        File[] list = fileFrom.listFiles();
//        if(!Exist(fileFrom))
//        	return;
//        for(File file : list){
//            if(file.isDirectory()){ // Visits' folder
//            	ListVisitContentMusee(pathFrom + "/" + file.getName(), file.getName(), l);
//            }
//        }
//	}
//
//	// List a Museum visit folder content
//    public static void ListVisitContentMusee(String pathFrom, String visitName, Location l) {
//        File fileFrom = new File(pathFrom);
//        File[] list = fileFrom.listFiles();
//
//        if(!Exist(fileFrom))
//        	return;
//
//        Visit tmpVisit = new Visit(pathFrom, visitName);
//        for(File file : list) {
//            if(file.isDirectory()){ // Overview or Info or IP folder
//                if(!file.getName().equals(FileManager.OVERVIEW_FOLDER) && !file.getName().equals(FileManager.INFO_FOLDER)) {
//                	InterestPoint tmpIP = new InterestPoint(pathFrom + "/" + file.getName(), file.getName());
//					tmpVisit.addInterestPoint(tmpIP, tmpVisit.getIP());
//                }
//            }
//        }
//        l.addVisit(tmpVisit);
//    }
//

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
				ArrayList<File> pictures = new ArrayList<>(), p360 = new ArrayList<>(), videos = new ArrayList<>();

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
					pictures.add(new File(FileManager.MUSEUM_FOLDER + FileManager.PICTURES_FOLDER + tmpjArray.getString(j)));
				}

				tmpobject = reader.getJSONObject(FileManager._360);
				tmpjArray = tmpobject.getJSONArray(FileManager.LINK);
				for (int j = 0; j < tmpjArray.length(); j++) {
					p360.add(new File(FileManager.MUSEUM_FOLDER + FileManager._360_FOLDER + tmpjArray.getString(j)));
				}

				tmpobject = reader.getJSONObject(FileManager.VIDEOS);
				tmpjArray = tmpobject.getJSONArray(FileManager.LINK);
				for (int j = 0; j < tmpjArray.length(); j++) {
					videos.add(new File(FileManager.RESSOURCES + MainActivity.getContext().getPackageName() +
							File.pathSeparator + FileManager.RAW + FileManager.MUSEUM_FOLDER + FileManager.VIDEOS_FOLDER + tmpjArray.getString(j)));
				}

				IPList.add(new InterestPoint(title_fr, title_en, presentation_fr, presentation_en, author, type_fr, type_en, coordx, coordy, pictures, p360, videos));
			}
			return IPList;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}