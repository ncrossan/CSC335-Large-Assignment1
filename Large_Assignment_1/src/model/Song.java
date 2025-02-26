/* Author: Nathan Crossman, Andy Zhang
 * Course: CSC 335
 * Description: An instance of this class represents a Song.
 */

package model;

final class Song {
	// instance variables
	private String title;
	private String artist;
	// constructor
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}
	
	// copy constructor
	public Song(Song song) {
		title = song.getTitle();
		artist = song.getArtist();
	}
	// Getters
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	@Override
	public String toString() {
		return title + " by " + artist;
	}
}
