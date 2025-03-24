/* Authors: Nathan Crossman, Andy Zhang
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
	private final String genre;
	
	// constructor
	public Song(String title, String artist, String album, String genre) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
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
	
	public String getGenre() {
		return genre;
	}
	
	@Override
	public String toString() {
		return title + " by " + artist;
	}
}
