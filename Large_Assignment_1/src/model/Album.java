package model;

import java.util.ArrayList;

class Album {
	private ArrayList<Song> songList;
	private String title;
	private String artist;
	private String genre;
	private int year;
	
	public Album(String title, String artist, String genre, int year) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
		
		songList = new ArrayList<Song>();
	}
	
	// copy constructor
	public Album(Album album) {
		
	}
	
	public void addSong(Song song) {
		songList.add(song);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String toString() {
		return " ";
	}
	
}
