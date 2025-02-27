/* Author: Nathan Crossman, Andy Zhang
 * Course: CSC 335
 * Description: An instance of this class represents a PlayList.
 */
package model;

import java.util.ArrayList;

class PlayList {
	// instance variables
	private String name;
	private ArrayList<Song> songs;
	
	// constructor
	public PlayList(String name) {
		this.name = name;
		songs = new ArrayList<Song>();
	}
	
	// adds a song to the songs ArrayList
	public void addSong(Song song) {
		songs.add(song);
	}
	// removes a song from the songs ArrayList
	public void removeSong(Song song) {
		songs.remove(song);
	}
	
	// returns the name of the PlayList
	public String getName() {
		return name;
	}
	
	
	@Override
	public String toString() {
		String result = name + "\n";
		for (Song s : songs) {
			result += s.toString() + ", ";
		}
		result = result.substring(0, result.length()-2);
		return result + "\n";
	}
}
