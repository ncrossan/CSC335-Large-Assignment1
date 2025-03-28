/* Authors: Nathan Crossman, Andy Zhang
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
	
	// copy constructor
	public PlayList(PlayList playlist) {
		this.name = playlist.getName();
		this.songs = playlist.getPlayList();
	}
	
	// copy constructor
	public PlayList(String name, ArrayList<Song> songList) {
		this.name = name;
		this.songs = songList;
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
	// returns a copy of the songs ArrayList
	public ArrayList<Song> getPlayList() {
		return new ArrayList<Song>(songs);
	}
	
	
	@Override
	public String toString() {
		String result = name + "\n";
		if (songs.size() == 0) {
			return result + "There are no songs in your playlist!\n";
		}
		for (Song s : songs) {
			result += s.toString() + "\n";
		}
		return result;
	}
}
