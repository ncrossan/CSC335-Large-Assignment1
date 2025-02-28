/* Author: Nathan Crossman, Andy Zhang
 * Course: CSC 335
 * Description: An instance of this class represents an Album.
 */
package model;

import java.util.ArrayList;

class Album {
	// instance variables
	private ArrayList<Song> songList;
	private String title;
	private String artist;
	private String genre;
	private int year;
	
	// constructor
	public Album(String title, String artist, String genre, int year) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
		
		songList = new ArrayList<Song>();
	}
	
	// copy constructor
	public Album(Album album) {
		title = album.getTitle();
		artist = album.getArtist();
		genre = album.getGenre();
		year = album.getYear();
		songList = album.getSongs();
	}
	
	// This method adds a Song to the songList ArrayList
	public void addSong(Song song) {
		songList.add(song);
	}
	
	// Getters
	public String getTitle() {
		return title;
	}
	public String getArtist() {
		return artist;
	}
	public String getGenre() {
		return genre;
	}
	public int getYear() {
		return year;
	}
	// deeper copy is not needed because Song is immutable
	public ArrayList<Song> getSongs() {
		return new ArrayList<Song>(songList);
	}
	
	@Override
	public String toString() {
		String result = title + ", " + artist + ", " + genre + ", " + year + "\n";
		for (Song s: songList) {
			result += s.getTitle() + "\n";
		}
		return result;
	}
	
}
