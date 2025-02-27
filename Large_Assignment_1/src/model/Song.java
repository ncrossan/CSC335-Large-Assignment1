/* Author: Nathan Crossman, Andy Zhang
 * Course: CSC 335
 * Description: An instance of this class represents a Song.
 * NOTE: THIS CLASS IS IMMUTABLE
 */

package model;

final class Song {
	// instance variables
	private final String title;
	private final String artist;
	
	// constructor
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
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
