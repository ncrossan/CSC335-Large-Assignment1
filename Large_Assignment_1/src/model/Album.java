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
	
	// constructor
	public Album(String title, String artist) {
		this.title = title;
		this.artist = artist;
		
		songList = new ArrayList<Song>();
	}
	
	// copy constructor
	public Album(Album album) {
		this.title = album.getTitle();
		this.artist = album.getArtist();
		
		this.songList = album.getSongList();
	}
	
	//This method adds a Song to the songList ArrayList
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
	
	public ArrayList<Song> getSongList() {
		return new ArrayList<Song>(songList);
	}

	@Override
	public String toString() {
		String result = title + ", " + artist + "\n";
		for (Song s: songList) {
			result += s + ", ";
		}
		result = result.substring(0, result.length()-2);
		return result;
	}
	
}
