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
		if (playlists.size() != 0) {
			for (PlayList p : playlists) {
				if (p.getName().equals(name)) {
					result += p.toString();
				}
			}
		}
		// if no such playlist of name is found, set result to name + " Playlist not found!"
		if (result.equals("")) result = name + " Playlist not found!";
		return result;
	}
	
	/*
	 * 
	 * */
	public String addPlayList(String playlistName) {
		// check if playlist name already exists
		for (PlayList p : playlists) {
			if (p.getName().equals(playlistName)) return "Playlist already exists";
		}
		PlayList playlist = new PlayList(playlistName);
		playlists.add(playlist);
		return "Playlist successfully created!";
	}
	
	public String addSongToPlayList(String playlistName, String title, String artist) {
		for (PlayList p : playlists) {
			if (p.getName().equals(playlistName)) {
				Song song = musicStore.getSong(title, artist);
				if (p.getPlayList().contains(song)) return "Song already added";
				if (song != null && songs.contains(song)) {
					p.addSong(song);
					return "Song added!";
				}
				return "Song not found!";
			}
		}
		return "Couldn't perform operation.";
	}
	
	public String removeSongFromPlayList(String playlistName, String title, String artist) {
		for (PlayList p : playlists) {
			if (p.getName().equals(playlistName)) {
				Song song = musicStore.getSong(title, artist);
				if (song != null && songs.contains(song)) {
					p.removeSong(song);
					return "Song removed!";
				}
				return "Song not found!";
			}
		}
		return "Couldn't perform operation.";
	}
	
	public String addSong(String title, String artist) {
		Song song = musicStore.getSong(title, artist);
		if (songs.contains(song)) return "Song is already in library";
		if (song != null) {
			songs.add(musicStore.getSong(title, artist));			
			return "added " + song.toString() + " to library";
		}
		return "Song not found in Music Store!";
	}
	
	public String addAlbum(String title, String artist) {
		Album album = musicStore.getAlbum(title, artist);
		if (albums.contains(album)) return "Album is already in library";
		if (album != null) {
			albums.add(album);
			// add the songs in the albums to the songs arraylist
			for (Song s : album.getSongs()) {
				if (!songs.contains(s)) songs.add(s);	
			}
			return "added " + album.getTitle() + " by " + album.getArtist() + " to library";
		}
		return "Album not found in Music Store!";
	}
	
	public String addRating(String title, String artist, Integer rating) {
		// checks if the song is in music store and if the song is in the library.
		Song song = musicStore.getSong(title, artist);
		if (song != null && songs.contains(song)) {
			// automatically favorite the song if the rating is 5
			if (rating == 5) favorites.addSong(song);
			ratings.put(musicStore.getSong(title, artist), rating);
			return song.toString() + " was rated " + rating;
		}
		return "Song was not found in Music Store!";
	}
	
	public String favorite(String title, String artist) {
		Song song = musicStore.getSong(title, artist);
		if (favorites.getPlayList().contains(song)) return "Song is already in favorites";
		if (song != null && songs.contains(song)) {
			favorites.addSong(song);
			return "added " + song.toString() + " to favorites";
		}
		return "Song was not found in the library!";
	}
	
	public String getSongs() {
		String result = "Songs in library:\n";
		for (Song s : songs) {
			result += s.getTitle() + "\n";
		}
		if (result.equals("Songs in library:\n")) return "No songs in library yet!";
		return result;
	}
	
	public String getArtists() {
		String result = "Artists in library:\n";
		for (Album a : albums) {
			result += a.getArtist() + "\n";
		}
		if (result.equals("Artists in library:\n")) return "No artists in library yet!";
		return result;
	}
	
	public String getAlbums() {
		String result = "Albums in library:\n";
		for (Album a :  albums) {
			result += a.toString();
		}
		if (result.equals("Albums in library:\n")) return "No Albums yet!";
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
				result += s.toString() + " in " + s.getAlbum() + ", ";
			}
		}
		if (result.equals("")) return title + " not found in library";
		return result.substring(0, result.length()-2);
		
	}
	public String searchSongByArtist(String artist) {
		String result = "";
		for (Song s : songs) {
			if (artist.toLowerCase().equals(s.getArtist().toLowerCase())) {
				result += s.toString() + " in " + s.getAlbum() + "\n";
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
		return musicStore.searchAlbumByTitle(title);
	}
	
}
