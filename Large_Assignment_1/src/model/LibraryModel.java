/* Author: Nathan Crossman, Andy Zhang
 * Course: CSC 335
 * Description: An instance of this class represents a music library.
 */
package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class LibraryModel {
	// instance variables
	private MusicStore musicStore;
	private ArrayList<Song> songs;
	private ArrayList<Album> albums;
	private ArrayList<PlayList> playlists;
	private PlayList favorites;
	private HashMap<Song, Integer> ratings;
	
	// constructor
	public LibraryModel() throws FileNotFoundException {
		musicStore = new MusicStore();
		playlists = new ArrayList<PlayList>();
		favorites = new PlayList("favorites");
		ratings = new HashMap<Song, Integer>();
	}
	
	/* Searches through the playlist ArrayList for playlists that match
	 * the argument name.
	 * Arguments:
	 * 		name: the name of the playlist to look for
	 * Returns:
	 * 		result: a String containing the playlist's song's title and artist
	 */
	public String searchPlayListByName(String name) {
		String result = "";
		// add every playlist of the name to the result
		for (PlayList p : playlists) {
			if (p.getName().equals(name)) {
				result += p.toString();
			}
		}
		// if no such playlist of name is found, set result to name + " Playlist not found!"
		if (result.equals("")) result = name + " Playlist not found!";
		return result;
	}
	
	public String addSong(String title, String artist) {
		if (musicStore.getSong(title, artist) != null) {
			songs.add(musicStore.getSong(title, artist));
			return "added " + title + " by " + artist + " to library";
		}
		return "Song not found in Music Store!";
	}
	
	public String addAlbum(String title, String artist) {
		if (musicStore.getAlbum(title, artist) != null){
			albums.add(musicStore.getAlbum(title, artist));
			return "added" + title + " by " + artist + " to library";
		}
		return "Album not found in Music Store!";
	}
	
	public String addRating(String title, String artist, Integer rating) {
		// checks if the rating is within bounds
		if (rating < 1 || rating > 5) return "Set the rating between 1 and 5";
		// checks if the song is in music store and if the song is in the library.
		if (musicStore.getSong(title, artist) != null &&
				songs.contains(musicStore.getSong(title, artist))) {
			// automatically favorite the song if the rating is 5
			if (rating == 5) favorites.addSong(musicStore.getSong(title, artist));
			
			ratings.put(musicStore.getSong(title, artist), 5);
			return title + " by " + artist + " was rated " + rating;
		}
		return "Song was not found in Music Store!";
	}
	
	public String getSongs() {
		String result = "Songs in library: ";
		for (Song s : songs) {
			result += s.getTitle() + ", ";
		}
		if (result.equals("")) return "No songs in library yet!";
		return result.substring(0, result.length()-2);
	}
	
	public String getArtists() {
		String result = "Artists in library: ";
		for (Song s : songs) {
			result += s.getArtist() + ", ";
		}
		if (result.equals("")) return "No songs in library yet!";
		return result.substring(0, result.length()-2);
	}
	
	
	public String getAlbums() {
		String result = "";
		for (Album a :  albums) {
			result += a.toString();
		}
		if (result.equals("")) result += "No Albums yet!";
		return result;
	}
	public String getPlayLists() {
		String result = "";
		for (PlayList p : playlists) {
			result += p.toString();
		}
		if (result.equals("")) result += "No Playlists yet!";
		return result;
	}
	
	public String getFavorites() {
		return favorites.toString();
	}
	
	/* This method validates if the song is in the library before using the method
	 * inside of music store.
	 * 
	 */
	public String searchSongByTitle(String title) {
		for (Song s : songs) {
			if (title.equals(s.getTitle())) return musicStore.searchSongByTitle(title);
		}
		return "Song not found in library";
		
	}
	public String searchSongByArtist(String artist) {
		for (Song s : songs) {
			if (artist.equals(s.getArtist())) return musicStore.searchSongByArtist(artist);
		}
		return "Artist not found in library";
	}
	
	public String searchAlbumByTitle(String title) {
		for (Album a : albums) {
			if (title.equals(a.getTitle())) return musicStore.searchAlbumByTitle(title);
		}
		return "Album not found in library";
	}
	
	public String searchAlbumByArtist(String artist) {
		for (Album a : albums) {
			if (artist.equals(a.getArtist())) return musicStore.searchAlbumByArtist(artist);
		}
		return "Artist not found in library";
	}
	
}
