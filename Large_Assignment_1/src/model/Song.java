package model;

public class Song {
	private String title;
	private String artist;
	
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}
	
	// copy constructor
	public Song(Song song) {
		title = song.getTitle();
		artist = song.getArtist();
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String toString() {
		return title + " by " + artist;
	}
}
