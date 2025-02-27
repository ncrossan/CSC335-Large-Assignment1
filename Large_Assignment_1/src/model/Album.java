package model;

import java.util.ArrayList;

class Album {
	private ArrayList<Song> songList;
	private String title;
	private String artist;
	
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
	
	public void addSong(Song song) {
		songList.add(song);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public ArrayList<Song> getSongList() {
		return new ArrayList<Song>(songList);
	}
	
	public String toString() {
		return " ";
	}
	
}
