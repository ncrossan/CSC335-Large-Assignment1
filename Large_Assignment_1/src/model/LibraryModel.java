/* Authors: Nathan Crossman, Andy Zhang
 * Course: CSC 335
 * Description: An instance of this class represents a music library.
 */
package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class LibraryModel {
	// instance variables
	private MusicStore musicStore;
	private ArrayList<Album> albums;
	private ArrayList<Song> songs;
	private ArrayList<PlayList> playlists;
	private PlayList favorites;
	private HashMap<Song, Integer> ratings;
	private ArrayList<Song> recentlyPlayed;
	private HashMap<Song, Integer> plays;
	
	// constructor
	public LibraryModel() throws FileNotFoundException {
		musicStore = new MusicStore();
		songs = new ArrayList<Song>();
		albums = new ArrayList<Album>();
		playlists = new ArrayList<PlayList>();
		favorites = new PlayList("favorites");
		ratings = new HashMap<Song, Integer>();
		recentlyPlayed = new ArrayList<>();
		plays = new HashMap<Song, Integer>();
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
	
	/* adds creates a playlist with the given  playlistName, checks for existing playlists, 
	 * playlist names are case sensitive.
	 * Arguments:
	 * 		playlistName: the name of a playlist to create
	 * returns:
	 * 		a String message affirming a playlist was created or there was a duplicate.
	 * 
	 */
	public String addPlayList(String playlistName) {
		if (playlistName.length() == 0) return "Invalid playlist name!";
		// check if playlist name already exists
		for (PlayList p : playlists) {
			if (p.getName().equals(playlistName)) return "Playlist already exists";
		}
		PlayList playlist = new PlayList(playlistName);
		playlists.add(playlist);
		return "Playlist successfully created!";
	}
	/* adds a song to a given playlist with the playlistName name. Takes in a song
	 * using an artist and the title of the song.
	 * Arguments:
	 * 		playlistName: a String containing the name of a playlist to add to
	 * 		title: the name of the song to be added
	 * 		artist: the artist of the song to be added
	 * Returns:
	 * 		a String message determining if a song was added or not or if the operation
	 * 		failed.
	 */		
	public String addSongToPlayList(String playlistName, String title, String artist) {
		for (PlayList p : playlists) {
			if (p.getName().equals(playlistName)) {
				Song song = musicStore.getSong(title, artist);
				if (p.getPlayList().contains(song)) return "Song already added";
				if (song != null && songs.contains(song)) {
					p.addSong(song);
					return "Song added!";
				}
				if (p.getPlayList().contains(song)) return "Song already in playlist!";
				return "Song not found!";
			}
		}
		return "Couldn't perform operation.";
	}
	/* removes a song from a given playlist with the playlistName name. Takes in a song
	 * using an artist and the title of the song.
	 * Arguments:
	 * 		playlistName: the name of a playlist to remove from
	 * 		title: the name of the song to be removed
	 * 		artist: the artist of the song to be removed
	 * Returns:
	 * 	 	a String message determining if a song was added or not or if the operation
	 * 		failed.
	 */
	public String removeSongFromPlayList(String playlistName, String title, String artist) {
		for (PlayList p : playlists) {
			if (p.getName().equals(playlistName)) {
				Song song = musicStore.getSong(title, artist);
				if (song != null && songs.contains(song) && 
						p.getPlayList().contains(song)) {
					p.removeSong(song);
					return "Song removed!";
				}
				return "Song not found!";
			}
		}
		return "Couldn't perform operation.";
	}

	/* adds a song to the library given the song title and the artist of the song
	 * Arguments:
	 * 		title: the name of the song to be added
	 * 		artist: the artist of the song to be added
	 * Returns:
	 * 		a String message determining if a song was successfully added or
	 * 		not
	 */
	public String addSong(String title, String artist) {
		Song song = musicStore.getSong(title, artist);
		if (songs.contains(song)) return "Song is already in library";
		if (song != null) {
			songs.add(musicStore.getSong(title, artist));
			return "added " + song.toString() + " to library";
		}
		return "Song not found in Music Store!";
	}

	/* adds an album to the library given the album title and the artist
	 * of the album
	 * Arguments:
	 * 		title: the name of the album to be added
	 * 		artist: the artist of the album to be added
	 * Returns:
	 * 		a String message determining if an album was successfully added
	 * 		or not
	 */
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
	/* adds a rating to a song or updates the rating of the song given the title
	 * and artist of the song along with the rating.
	 * Arguments:
	 * 		title: the name of the song to be rated
	 * 		artist: the artist of the song to be rated
	 * 		rating: an Integer of the rating to give to the song
	 * Returns:
	 * 		a String with a message determining whether the song was successfully rated or not
	 */
	public String addRating(String title, String artist, Integer rating) {
		Song song = musicStore.getSong(title, artist);
		// checks if the song is in music store and if the song is in the library.
		if (song != null && songs.contains(song)) {
			// automatically favorite the song if the rating is 5
			if (rating == 5) favorite(title, artist);
			ratings.put(musicStore.getSong(title, artist), rating);
			return song.toString() + " was rated " + rating;
		}
		return "Song was not found in library";
	}
	/* adds a song to the favorites PlayList given a song title and the artist of the song
	 * Arguments:
	 * 		title: the name of a song
	 * 		artist: the name of the song artist
	 * Returns:
	 * 		a String with a message determining whether the song was added to favorites
	 * 
	 */
	public String favorite(String title, String artist) {
		Song song = musicStore.getSong(title, artist);
		if (favorites.getPlayList().contains(song)) return "Song is already in favorites";
		if (song != null && songs.contains(song)) {
			favorites.addSong(song);
			return "added " + song.toString() + " to favorites";
		}
		return "Song was not found in the library!";
	}
	/* gets a list of all the songs in the library
	 * Arguments:
	 * 		none
	 * Returns:
	 * 		result: a String with a list of all the songs in the library or "No songs in library yet!"
	 * 		if there are no songs
	 */
	public String getSongs() {
		String result = "Songs in library:\n";
		for (Song s : songs) {
			result += s.getTitle() + "\n";
		}
		if (result.equals("Songs in library:\n")) return "No songs in library yet!";
		return result;
	}
	/* gets a list of all the artists in the library
	 * Arguments:
	 * 		none
	 * Returns:
	 * 		result: a String with a list of all the artists in the library or "No artists in library 
	 * 		yet!"
	 * 		if there are no songs
	 */
	public String getArtists() {
		String result = "Artists in library:\n";
		for (Album a : albums) {
			// account for duplicate artists in case of multiple albums from same artist
			if (!result.contains(a.getArtist())) {
				result += a.getArtist() + "\n";
			}
		}
		if (result.equals("Artists in library:\n")) return "No artists in library yet!";
		return result;
	}
	/* gets a list of all the albums in the library
	 * Arguments:
	 * 		none
	 * Returns:
	 * 		result: a String with a list of all the albums in the library or "No Albums yet!"
	 */
	public String getAlbums() {
		String result = "Albums in library:\n";
		for (Album a :  albums) {
			result += a.toString();
		}
		if (result.equals("Albums in library:\n")) return "No Albums yet!";
		return result;
	}
	
	/* gets a list of all the playlist names in the library except for favorites
	 * Arguments:
	 * 		none
	 * Returns:
	 * 		result: a String with a list of all playlist names in the library
	 */
	public String getPlayLists() {
		String result = "Playlists:\n";
		for (PlayList p : playlists) {
			result += p.toString();
		}
		if (result.equals("")) result += "No Playlists yet!";
		return result;
	}
	
	/* gets a list of all the songs in the favorites PlayList
	 * Arguments:
	 * 		none
	 * Returns:
	 * 		a String containing all the names of songs in favorites
	 * 
	 */
	public String getFavorites() {
		return favorites.toString();
	}
	
	// remove song from library
	public String removeSong(String title, String artist) {
		Song song = musicStore.getSong(title, artist);
		if (!(songs.contains(song)) || song.equals(null)) {
			return "Song not found in library.";
		}
		songs.remove(song);
		return song.toString() + " removed.";
	}
	
	// remove song from album in library
	public String removeAlbum(String title, String artist) {
		Album album = musicStore.getAlbum(title, artist);
		if (!(albums.contains(album)) || album.equals(null)) {
			return "Album not found in library.";
		}
		albums.remove(album);
		return title + " by " + artist + " removed.";
	}
	
	// shuffle songs ArrayList
	public String shuffleSongs() {
		if (songs.size() == 0) return "There are no songs in your library.";
		String output = "Songs shuffled!\n";
		Collections.shuffle(songs);
		for (Song s : songs) {
			output += s.toString();
		}
		return output;
	}
	
	// shuffle songs in playlists
	public String shufflePlaylist(String playlist) {
		if (playlists.size() == 0) return "You have no playlists.";
		
		for (PlayList p : playlists) {
			String output = playlist + " was shuffled!\n";
			if (p.getName().equals(playlist)) {
				int index = playlists.indexOf(p);
				ArrayList<Song> songList = p.getPlayList();
				Collections.shuffle(songList);
				for (Song s : songList) output += s.toString();
				PlayList shuffledPlayList = new PlayList(playlist, songList);
				playlists.set(index, shuffledPlayList);
				return output;
			}
		}
		return "Playlist not found!";
	}
	
	// get album information from song search
	public String getAlbumInformationBySong(String title) {
		String output = "\n";
		
		for (Song s : songs) {
			if (s.getTitle().toLowerCase().equals(title.toLowerCase())) {
				Song song = musicStore.getSong(s.getTitle(), s.getArtist());
				Album album = musicStore.getAlbum(song.getAlbum(), song.getArtist());
				output += album.toString();
				if (!(albums.contains(album))) output += "Album not in library.\n";
			}
		}
		return output;
	}
	
	// get album information from song search
	public String getAlbumInformationByArtist(String artist) {
		String output = "\n";
			
		for (Song s : songs) {
			if (s.getArtist().toLowerCase().equals(artist.toLowerCase())) {
				Song song = musicStore.getSong(s.getTitle(), s.getArtist());
				Album album = musicStore.getAlbum(song.getAlbum(), song.getArtist());
				output += album.toString();
				if (!(albums.contains(album))) output += "Album not in library.\n";
			}
		}
		return output;
	}
	
	/* searches the library for any songs matching the title argument
	 * Arguments:
	 * 		title: a title of a song to search for
	 * Returns:
	 * 		result: a list of songs with the title along with other information
	 * 		each song
	 * 		OR returns title + " not found in library" if nothing was found
	 * 
	 */
	public String searchSongByTitle(String title) {
		String result = "";
		for (Song s : songs) {
			if (title.toLowerCase().equals(s.getTitle().toLowerCase())) {
				result += s.toString() + " in " + s.getAlbum() + ", ";
				result += s.toString() + " in " + s.getAlbum() + "\n";
			}
		}
		if (result.equals("")) return title + " not found in library";
		return result;
		
	}
	/* searches the library for any songs matching the artist argument
	 * Arguments:
	 * 		artist: a artist of songs search for
	 * Returns:
	 * 		result: a list of songs from the artist along with other information
	 * 		each song
	 * 		OR returns artist + " not found in library" if nothing was found
	 */
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
	
	/* searches the library for any albums matching the title argument
	 * Arguments:
	 * 		title: a title of an album to search for
	 * Returns:
	 * 		result: an album with a list of songs in the album along with other information
	 * 		of the album
	 * 		OR returns title + " not found in library" if nothing was found
	 */
	public String searchAlbumByTitle(String title) {
		String result = "";
		for (Album a : albums) {
			if (title.toLowerCase().equals(a.getTitle().toLowerCase())) result += a.toString();
		}
		if (result.equals("")) return title + " not found in library";
		return result; 
	}
	/* this method searches the library for any albums matching the artist argument
	 * Arguments:
	 * 		artist: an artist of songs to search for
	 * Returns:
	 * 		result: an album with a list of songs in the album along with other information of
	 * 		the album
	 * 		OR returns artist + " not found in library" if nothing was found
	 * 
	 */
	public String searchAlbumByArtist(String artist) {
		String result = "";
		for (Album a : albums) {
			if (artist.toLowerCase().equals(a.getArtist().toLowerCase())) result += a.toString();
		}
		if (result.equals("")) return artist + " not found in library";
		return result;
	}
	
	public String playSong(String title, String artist) {
		String result = "";
		Song song = musicStore.getSong(title, artist);
		if (song != null && songs.contains(song)) {
			// adds the song to the recentlyPlayed list
			recentlyPlayed.add(song);
			// remove the oldest song from the recentlyPlayed ArrayList if it becomes larger than 10
			if (recentlyPlayed.size() > 10) recentlyPlayed.remove(0);
			
			// adds the song into the plays HashMap if it doesn't already exist
			if (!plays.containsKey(song)) plays.put(song, 0);
			// increments the play count of the song
			plays.put(song, plays.get(song) + 1);
			
			result += "Playing " + song.toString();
		}
		if (result.length() < 1) result += title + " by " + artist + " not found in library.";
		return result;
	}
	
	public String getRecentlyPlayed() {
		String result = "Recently played:\n";
		// add the recentlyPlayed ArrayList backwards to result
		for (int i=0; i < recentlyPlayed.size(); i++) {
			result += recentlyPlayed.get(recentlyPlayed.size()-1-i).toString() + "\n";
		}
		return result;
	}
	
	// call the methods of musicStore to search it
	public String searchStoreSongByArtist(String artist) {
		return musicStore.searchSongByArtist(artist);
	}
	// call the methods of musicStore to search it
	public String searchStoreSongByTitle(String title) {
		return musicStore.searchSongByTitle(title);
	}
	// call the methods of musicStore to search it
	public String searchStoreAlbumByArtist(String artist) {
		return musicStore.searchAlbumByArtist(artist);
	}
	// call the methods of musicStore to search it
	public String searchStoreAlbumByTitle(String title) {
		return musicStore.searchAlbumByTitle(title);
	}
	
	// return a shallow copy of songs
	public String getSongListData() {
		String output = "Songs:\n";
		for (Song s : songs) {
			output += s.getTitle() + ", " + s.getArtist() + ", " + s.getAlbum() + "\n";
		}
		return output;
	}
	
	// return copy of albums
	public String getAlbumListData() {
		String output = "Albums:\n";
		for (Album a : albums) {
			output += a.getTitle() + ", " + a.getArtist() + ", " 
					  + a.getGenre()+ ", " + a.getYear() + "\n";
		}
		return output;
	}
	
	// return copy of playlists
	public String getPlayListData() {
		String output = "Playlists:\n";
		for (PlayList p : playlists) {
			output += "Playlist: " + p.getName() + "\n";
			for (Song s : p.getPlayList()) {
				output += s.getTitle() + ", " + s.getArtist() + ", " + s.getAlbum() + "\n";
			}
		}
		return output;
	}
	
	// return copy of favorites
	public String getFavoritesData() {
		String output = favorites.getName() + "\n";
		for (Song s : favorites.getPlayList()) {
			output += s.getTitle() + ", " + s.getArtist() + ", " + s.getAlbum() + "\n";
		}
		return output;
	}
	
	// return copy of recentlyPlayed
	public String getRecentlyPlayedData() {
		String output = "Recents:\n";
		for (Song s : recentlyPlayed) {
			output += s.getTitle() + ", " + s.getArtist() + ", " + s.getAlbum() + "\n";
		}
		return output;
	}
}
