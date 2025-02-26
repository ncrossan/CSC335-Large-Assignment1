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
	
	@Override
	public String toString() {
		String result = name + "\n";
		for (Song s : songs) {
			result += s + ", ";
		}
		result = result.substring(0, result.length()-2);
		return result;
	}
}
