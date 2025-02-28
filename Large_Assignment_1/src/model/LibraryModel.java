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
	private ArrayList<Album> albums;
	private ArrayList<Song> songs;
	private ArrayList<PlayList> playlists;
	private PlayList favorites;
	private HashMap<Song, Integer> ratings;
	
	// constructor
	public LibraryModel() throws FileNotFoundException {
		musicStore = new MusicStore();
		songs = new ArrayList<Song>();
		albums = new ArrayList<Album>();
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
			if (p.getName().toLowerCase().equals(name.toLowerCase())) {
				result += p.toString();
			}
		}
		// if no such playlist of name is found, set result to name + " Playlist not found!"
		if (result.equals("")) result = name + " Playlist not found!";
		return result;
	}
	
	public String removeSongFromPlayList(String playlistName, String title, String artist) {
		boolean inPlayLists = false;
		for (PlayList p : playlists) {
			if (p.getName().toLowerCase().equals(playlistName.toLowerCase())) inPlayLists = true;
		}
		// if the playlist is not in playlists, return a message.
		if (!inPlayLists) return "playlist does not exist";
		
		if (musicStore.getSong(title, artist) == null) return "Song does not exist";
		if (songs.contains(musicStore.getSong(title, artist))) {
			for (PlayList p : playlists) {
				if (p.getName().toLowerCase().equals(playlistName.toLowerCase())) {
					p.removeSong(musicStore.getSong(title, artist));;
				}
			}
		}
		return title + " by "  + artist + " removed from  " + playlistName;
	}
	
	public String addSongToPlayList(String playlistName, String title, String artist) {
		boolean inPlayLists = false;
		for (PlayList p : playlists) {
			if (p.getName().toLowerCase().equals(playlistName.toLowerCase())) inPlayLists = true;
		}
		// if the playlist is not in playlists, return a message.
		if (!inPlayLists) return "playlist does not exist";
		
		if (musicStore.getSong(title, artist) == null) return "Song does not exist";
		if (songs.contains(musicStore.getSong(title, artist))) {
			for (PlayList p : playlists) {
				if (p.getName().toLowerCase().equals(playlistName.toLowerCase())) {
					p.addSong(musicStore.getSong(title, artist));;
				}
			}
		}
		return title + " by "  + artist + " added to  " + playlistName;
	}
	
	public String addSong(String title, String artist, String albumTitle) {
		if (musicStore.getSong(title, artist) != null) {
			songs.add(musicStore.getSong(title, artist));			
			return "added " + title + " by " + artist + " to library";
		}
		return "Song not found in Music Store!";
	}
	
	public String addAlbum(String title, String artist) {
		if (musicStore.getAlbum(title, artist) != null){
			albums.add(musicStore.getAlbum(title, artist));
			// add the songs in the albums to the songs arraylist
			for (Song a : musicStore.getAlbum(title, artist).getSongs()) {
				songs.add(a);
			}
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
	
	public String favorite(String title, String artist) {
		// checks if the song is in the music store AND library
		if (musicStore.getSong(title, artist) != null &&
				songs.contains(musicStore.getSong(title, artist))) {
			favorites.addSong(musicStore.getSong(title, artist));
			return "added " + musicStore.getSong(title, artist).toString() + " to favorites";
		}
		return "Song was not found in the library!";
	}
	
	public String getSongs() {
		String result = "Songs in library: ";
		for (Song s : songs) {
			result += s.getTitle() + ", ";
		}
		if (result.equals("Songs in library: ")) return "No songs in library yet!";
		return result.substring(0, result.length()-2);
	}
	
	public String getArtists() {
		String result = "Artists in library: ";
		for (Song s : songs) {
			result += s.getArtist() + ", ";
		}
		if (result.equals("Artists in library: ")) return "No songs in library yet!";
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
	
	/* 
	 *
	 * 
	 */
	public String searchSongByTitle(String title) {
		String result = "";
		for (Song s : songs) {
			if (title.toLowerCase().equals(s.getTitle().toLowerCase())) {
				result = s.toString() + " in " + s.getAlbum() + ", ";
			}
		}
		if (result.equals("")) return title + " not found in library";
		return result.substring(0, result.length()-2);
		
	}
	public String searchSongByArtist(String artist) {
		String result = "";
		for (Song s : songs) {
			if (artist.toLowerCase().equals(s.getArtist().toLowerCase())) {
				result = s.toString() + " in " + s.getAlbum() + ", ";
			}
		}
		if (result.equals("")) return artist + " not found in library";
		return result;
	}
	
	public String searchAlbumByTitle(String title) {
		String result = "";
		for (Album a : albums) {
			if (title.toLowerCase().equals(a.getTitle().toLowerCase())) result += a.toString();
		}
		if (result.equals("")) return title + " not found in library";
		return result; 
	}
	
	public String searchAlbumByArtist(String artist) {
		String result = "";
		for (Album a : albums) {
			if (artist.toLowerCase().equals(a.getArtist().toLowerCase())) result += a.toString();
		}
		if (result.equals("")) return artist + " not found in library";
		return result;
	}
	
	// call the methods of musicStore to search it
	public String searchStoreSongByArtist(String artist) {
		return musicStore.searchSongByArtist(artist);
	}
	
	public String searchStoreSongByTitle(String title) {
		return musicStore.searchSongByTitle(title);
	}
	
	public String searchStoreAlbumByArtist(String artist) {
		return musicStore.searchAlbumByArtist(artist);
	}
	
	public String searchStoreAlbumByTitle(String title) {
		return musicStore.searchSongByArtist(title);
	}
}
