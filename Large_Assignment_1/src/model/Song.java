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
	private final String album;
	
	// constructor
	public Song(String title, String artist, String album) {
		this.title = title;
		this.artist = artist;
		this.album = album;
	}
	
	// Getters
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	@Override
	public String toString() {
		return title + " by " + artist;
	}
}
