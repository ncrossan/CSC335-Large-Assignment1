/* Authors: Nathan Crossman, Andy Zhang
 * Course: CSC 335
 * Description: An instance of this class represents a music library.
 */
package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryModel {
	// instance variables
	private MusicStore musicStore;
	private ArrayList<Album> albums;
	private ArrayList<Song> songs;
	private ArrayList<PlayList> playlists;
	private PlayList favorites;
	private PlayList topRated;
	private HashMap<Song, Integer> ratings;
	private ArrayList<Song> recentlyPlayed;
	private HashMap<Song, Integer> plays;
	private HashMap<String, Integer> genreCount;
	
	// constructor
	public LibraryModel() throws FileNotFoundException {
		musicStore = new MusicStore();
		songs = new ArrayList<Song>();
		albums = new ArrayList<Album>();
		playlists = new ArrayList<PlayList>();
		favorites = new PlayList("favorites");
		topRated = new PlayList("Top Rated");
		playlists.add(favorites);
		playlists.add(topRated);
		ratings = new HashMap<Song, Integer>();
		recentlyPlayed = new ArrayList<>();
		plays = new HashMap<Song, Integer>();
		genreCount = new HashMap<String, Integer>();
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
		Album album = musicStore.getAlbum(song.getAlbum(), song.getArtist());
		// add album to library if song is added
		if (!(albums.contains(album))) albums.add(album);
		
		if (songs.contains(song)) return "Song is already in library";
		if (song != null) {
			songs.add(song);
			maintainGenrePlayLists(song, 1);
			
			return "added " + song.toString() + " to library";
		}
		return "Song not found in Music Store!";
	}
	/* This class maintains the genre playlists, if a genre has more than 10 songs
	 * in the library, it automatically creates a playlist for that genre, if it drops
	 * below 10 songs, then the playlist will be deleted
	 * Arguments:
	 * 		song: a Song object
	 * 		intOrDec: a variable that enables the genreCount HashMap to count the number
	 * 		of songs in a genre. 1 to increment the count, 0 to decrement
	 */
	private void maintainGenrePlayLists(Song song, int incOrDec) {
		if (!genreCount.containsKey(song.getGenre())) {
			genreCount.put(song.getGenre(), 1);
		}
		// increment the count
		else if (genreCount.containsKey(song.getGenre()) && incOrDec == 1) {
			genreCount.put(song.getGenre(), genreCount.get(song.getGenre())+1);
		}
		// decrement the count
		else if (genreCount.containsKey(song.getGenre()) && incOrDec == 0) {
			genreCount.put(song.getGenre(), genreCount.get(song.getGenre())-1);
		}
		// create a playlist for that genre as soon as there are 10 songs of the genre
		for (String genre : genreCount.keySet()) {
			if (genreCount.get(song.getGenre()) == 10) {
				PlayList newGenrePlayList = new PlayList(genre);
				playlists.add(newGenrePlayList);
				for (Song s : songs) {
					if (s.getGenre().equals(genre)) newGenrePlayList.addSong(s);;
				}
			}
			// if the genre's playlists already exists then update it
			else if (genreCount.get(song.getGenre()) > 10) {
				for (PlayList p : playlists) {
					if (p.getName().equals(song.getGenre()) && !p.getPlayList().contains(song)) {
						p.addSong(song);
					}
				}
			}
			// remove the genre's playlists if there are no longer 10 songs
			else if (genreCount.get(song.getGenre()) < 10) {
				PlayList playlistToRemove = null;
				for (PlayList p : playlists) {
					if (p.getName().equals(song.getGenre())) {
						playlistToRemove = p;
						for (Song s: p.getPlayList()) {
							p.removeSong(s);
						}
					}
				}
				if (playlists.contains(playlistToRemove)) playlists.remove(playlistToRemove);

			}
		}
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
				if (!songs.contains(s)) addSong(s.getTitle(), s.getArtist());	
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
			// automatically favorite the song and add it to the Top Rated PlayList if the rating is 5
			if (rating == 5) {
				favorite(title, artist);
				topRated.addSong(song);
			}
			// automatically add the song to Top Rated if the rating is 4+
			else if (rating >= 4) {
				topRated.addSong(song);
				// automatically remove the song from favorites if it was in favorites and the ranting
				// was changed to under 5
				if (favorites.getPlayList().contains(song)) favorites.removeSong(song);
			}
			else {
				// if the rating was changed to under 4, remove the song from both the topRated and favorites playlists
				if (topRated.getPlayList().contains(song)) topRated.removeSong(song);
				if (favorites.getPlayList().contains(song)) favorites.removeSong(song);
			}
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
		maintainGenrePlayLists(song, 0);
		return song.toString() + " removed.";
	}
	
	/* removes an album from albums ArrayList
	 * Arguments:
	 * 		title:  name of album
	 *  	artist: name of artist
	 * Returns:
	 * 		a String about operation success
	 * 
	 */
	public String removeAlbum(String title, String artist) {
		Album album = musicStore.getAlbum(title, artist);
		if (!(albums.contains(album)) || album.equals(null)) {
			return "Album not found in library.";
		}
		for (Song s: album.getSongs()) {
			removeSong(s.getTitle(), s.getArtist());
		}
		albums.remove(album);
		return title + " by " + artist + " removed.";
	}
	
	/* This method shuffles the songs ArrayList using Collections.shuffle
	 * Arguments:
	 * 		none
	 * Returns:
	 * 		a String about operation success and output of the shuffled songs list
	 * 
	 */
	public String shuffleSongs() {
		if (songs.size() == 0) return "There are no songs in your library.";
		String output = "Songs shuffled!\n";
		Collections.shuffle(songs);
		for (Song s : songs) {
			output += s.toString() + "\n";
		}
		return output;
	}
	
	/* This method shuffles a playlist using Collections.shuffle
	 * Arguments:
	 * 		playlist:  name of playlist
	 * Returns:
	 * 		a String about operation success and the output of the shuffled playlist
	 * 
	 */
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
	
	/* This method returns additional info about the album based on the song title
	 * Arguments:
	 * 		title:  name of song
	 * Returns:
	 * 		album instance variables in String
	 * 
	 */
	public String getAlbumInformationBySong(String title) {
		String output = "\n";
		
		for (Song s : songs) {
			if (s.getTitle().toLowerCase().equals(title.toLowerCase())) {
				Song song = musicStore.getSong(s.getTitle(), s.getArtist());
				Album album = musicStore.getAlbum(song.getAlbum(), song.getArtist());
				output += album.toString();
				for (Album a : albums) {
					if (a.getTitle().equals(album.getTitle()))  return output += "Album in library.\n";
				}
				output += "Album not in library.\n";
			}
		}
		return output;
	}
	
	/* This method returns additional info about the album based on the artist
	 * Arguments:
	 * 		artist:  name of artist
	 * Returns:
	 * 		instance variables of Album and the toString data
	 */
	public String getAlbumInformationByArtist(String artist) {
		String output = "\n";
			
		for (Song s : songs) {
			if (s.getArtist().toLowerCase().equals(artist.toLowerCase())) {
				Song song = musicStore.getSong(s.getTitle(), s.getArtist());
				Album album = musicStore.getAlbum(song.getAlbum(), song.getArtist());
				output += album.toString();
				for (Album a : albums) {
					if (a.getTitle().equals(album.getTitle()))  return output += "Album in library.\n";
				}
				output += "Album not in library.\n";
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
	
	/* This method returns a list of songs based on the genre
	 * Arguments:
	 * 		genre:  genre type
	 * Returns:
	 * 		String of songs
	 */
	public String searchSongByGenre(String genre) {
		String output = genre + " songs:\n";
		
		for (Song s : songs) {
			if (s.getGenre().toLowerCase().equals(genre.toLowerCase())) output += s.toString() + "\n";
		}
		return output;
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
	
	/* play a song using a title and artist of a song and return a message displaying
	 * which song is being played
	 * Arguments:
	 * 		title: the title of a song
	 * 		artist: the artist of a song
	 * Returns:
	 * 		a message of whether a song has successfully been played or not
	 */
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
	
	/* get the 10 most recently played songs in the library
	 * Returns:
	 * 		result: a list of the 10 most recently played songs, could also be nothing
	 * 
	 */
	public String getRecentlyPlayed() {
		String result = "Recently played:\n";
		// add the recentlyPlayed ArrayList backwards to result
		for (int i=0; i < recentlyPlayed.size(); i++) {
			result += recentlyPlayed.get(recentlyPlayed.size()-1-i).toString() + "\n";
		}
		return result;
	}
	
	/* get the 10 most played songs in the library
	 * Returns:
	 * 		result: a list of the 10 most played songs along with the number of times
	 * 		they have been played for each song.
	 * 
	 */
	public String getMostPlayedSongs() {
		String result = "";
	    List<Map.Entry<Song, Integer>> playCount = new ArrayList<>(plays.entrySet());
	    
	    // sort in descending order
	    Collections.sort(playCount, Collections.reverseOrder(Map.Entry.comparingByValue()));

	    List<Song> mostPlayed = new ArrayList<>();
	    for (int i = 0; i < Math.min(10, playCount.size()); i++) {
	        mostPlayed.add(playCount.get(i).getKey());
	    }
	    for (Song s : mostPlayed) {
	    	result += s.toString() + ": " + plays.get(s) + " plays\n";
	    }
	    return result;
	}
	
	/* Sorts the songs alphabetically by Song titles. Uses the Collections and Comparator methods to do so
	 * Returns:
	 * 		result: a sorted list of songs by title
	 */
	public String getSortedSongsByTitle() {
		String result = "";
	    ArrayList<Song> sorted = new ArrayList<>(songs);
	    Collections.sort(sorted, Comparator.comparing(Song::getTitle, String.CASE_INSENSITIVE_ORDER));

	    for (Song s : sorted) {
	    	result += s.toString() + "\n";
	    }
	    
	    return result;
	}
	/* Sorts the songs alphabetically by Song artists. Uses the Collections and Comparator methods to do so
	 * Returns:
	 * 		result: a sorted list of songs by artist
	 */
	public String getSortedSongsByArtist() {
		String result = "";
	    ArrayList<Song> sorted = new ArrayList<>(songs);
	    Collections.sort(sorted, Comparator.comparing(Song::getArtist, String.CASE_INSENSITIVE_ORDER));
	    
	    for (Song s : sorted) {
	    	result += s.toString() + "\n";
	    }
	    
	    return result;
	}
	
	/* Sorts the songs by the number of plays it has. Uses the Collections library methods to do so
	 * Returns:
	 * 		result: a sorted list of songs by playcount
	 */	
	public String getSortedRatings() {
		String result = "";
	    List<Map.Entry<Song, Integer>> list = new ArrayList<>(ratings.entrySet());

	    // sort to lowest to highest
	    Collections.sort(list, Map.Entry.comparingByValue());

	    List<Song> sortedSongs = new ArrayList<>();
	    for (Map.Entry<Song, Integer> entry : list) {
	        sortedSongs.add(entry.getKey());
	    }
	    
	    for (Song s : sortedSongs) {
	    	result += s.toString() + ": " + ratings.get(s) + "\n";;
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
