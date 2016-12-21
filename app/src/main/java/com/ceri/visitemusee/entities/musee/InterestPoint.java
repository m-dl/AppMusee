package com.ceri.visitemusee.entities.musee;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Maxime
 */
public class InterestPoint implements Serializable {
	private String presentation_FR, presentation_EN, name_FR, name_EN, author, type_FR, type_EN;
	private ArrayList<String> photos, _360;
	private ArrayList<File> videos;
	private double coordX, coordY;

	public InterestPoint(String name_FR, String name_EN, String presentation_FR, String presentation_EN, String author, String type_FR, String type_EN,
						 double coordX, double coordY, ArrayList<String> photos, ArrayList<String> _360, ArrayList<File> videos) {
		this.name_FR = name_FR.trim();
		this.name_EN = name_EN.trim();
		this.presentation_FR = presentation_FR.trim();
		this.presentation_EN = presentation_EN.trim();
		this.author = author.trim();
		this.type_FR = type_FR.trim();
		this.type_EN = type_EN.trim();
		this.coordX = coordX;
		this.coordY = coordY;
		this.photos = photos;
		this._360 = _360;
		this.videos = videos;
	}

	public String getName_FR() {
		return name_FR;
	}

	public void setName_FR(String name_FR) {
		this.name_FR = name_FR;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType_FR() {
		return type_FR;
	}

	public void setType_FR(String type_FR) {
		this.type_FR = type_FR;
	}

	public String getType_EN() {
		return type_EN;
	}

	public void setType_EN(String type_EN) {
		this.type_EN = type_EN;
	}

	public String getPresentation_FR() {
		return presentation_FR;
	}

	public void setPresentation_FR(String presentation_FR) {
		this.presentation_FR = presentation_FR;
	}

	public String getPresentation_EN() {
		return presentation_EN;
	}

	public void setPresentation_EN(String presentation_EN) {
		this.presentation_EN = presentation_EN;
	}

	public String getName_EN() {
		return name_EN;
	}

	public void setName_EN(String name_EN) {
		this.name_EN = name_EN;
	}

	public ArrayList<String> getPhotos() {
		return photos;
	}

	public void setPhotos(ArrayList<String> photos) {
		this.photos = photos;
	}

	public ArrayList<String> get_360() {
		return _360;
	}

	public void set_360(ArrayList<String> _360) {
		this._360 = _360;
	}

	public ArrayList<File> getVideos() {
		return videos;
	}

	public void setVideos(ArrayList<File> videos) {
		this.videos = videos;
	}

	public double getCoordX() {
		return coordX;
	}

	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}
}
