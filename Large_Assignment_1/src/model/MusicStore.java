/* Author: Nathan Crossman, Andy Zhang
 * Course: CSC 335
 * Description: An instance of this class represents a Music Store.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class MusicStore {
	private ArrayList<Album> albumList;
	
	// constructor method
	public MusicStore() throws FileNotFoundException {
		// load albums and songs from text files
		File directory = new File("src/albums");
		albumList = new ArrayList<Album>();
		
		// loop through text files
		for (File file : directory.listFiles()) {
			if (file.getName().equals("albums.txt")) { continue; } // skip albums file
			Scanner fileReader = new Scanner(file);
			parseFile(fileReader);
			
			fileReader.close();
		}
	}
	/* parses each album text file and creates song and album objects to put them in the music
	 * store.
	 */
	private void parseFile(Scanner fileReader) {
		String[] textHeader = fileReader.nextLine().split(",");
		String artist = textHeader[1]; // get artist name
		Album album = new Album(textHeader[0], textHeader[1],
								textHeader[2], Integer.parseInt(textHeader[3])); // create album object
		albumList.add(album);
		
		// add list of songs to album
		while (fileReader.hasNextLine()) {
			String songName = fileReader.nextLine(); // read song names from file
			Song song = new Song(songName, artist); // create song object
			album.addSong(song);
		}
	}
	
	/* This method searches the MusicStore for an Album given a String of
	 * the title of an album. Returns the Album's information and songs if
	 * it is found, otherwise return a message indicating nothing was found.
	 * Arguments:
	 * 		title: a string of the title of the album to search for
	 * Returns:
	 * 		message: an Album's information and the songs in it or a message indicating
	 * 		nothing was found
	 */
	public String searchAlbumByTitle(String title) {
		String message = "";
		// loop through every album in albumList to find the album(s) matching the title
		for (Album a : albumList) {
			if (a.getTitle().equals(title)) {
				return a.toString();
			}
		}
		// if no albums are found, set message to "Album not found!"
		if (message.equals("")) message += "Album not found!";
		return message;
	}
	/* This method searches the MusicStore for an Album given a String of
	 * the name of an artist. Returns the Album's information and songs if
	 * it is found, otherwise return a message indicating nothing was found.
	 * Arguments:
	 * 		name: a string of the name of the artist to search for
	 * Returns:
	 * 		message: an Album's information and the songs in it or a message indicating
	 * 		nothing was found
	 */
	public String searchAlbumByArtist(String artist) {
		String message = "";
		// loop through every album in albumList to find the album(s) from the artist
		for (Album a : albumList) {
			if (a.getArtist().equals(artist)) {
				message += a.toString();
			}
		}
		// if no albums are found, set message to "Album not found!"
		if (message.equals("")) message += "Album not found!";
		return message;
	}
	
	/* This method searches through every Song in the MusicStore for a song
	 * matching the name argument, any songs will be returned with their
	 * title, artist, and the album they are in.
	 * Arguments:
	 * 		name: a string of a name of a song
	 * Returns:
	 * 		message: a string of every found song's information or a string
	 * 		detailing that nothing was found
	 */
	public String searchSongByTitle(String title) {
		String message = "";
		// loop through every album in albumList and every song in each album
		// to find songs matching the title of the argument
		for (Album a : albumList) {
			for (Song s : a.getSongs()) {
				if (title.equals(s.getTitle())) {
					message += s.toString() +  " in " + a.getTitle() + "\n";
				}
			}
		}
		// if no songs are found, set message to "Song not found!"
		if (message.equals("")) message += "Song not found!";
		return message;
	}
	/* This method searches through every Song in the MusicStore for a song
	 * matching the title argument, any songs will be returned with their
	 * title, artist, and the album they are in.
	 * Arguments:
	 * 		name: a string of an artist's name
	 * Returns:
	 * 		message: a string of every found song's information or a string
	 * 		detailing that nothing was found
	 */
	public String searchSongByArtist(String artist) {
		String message = "";
		// loop through every album in albumList and every song in each album
		// to find songs matching the artist of the argument
		for (Album a : albumList) {
			for (Song s : a.getSongs()) {
				if (artist.equals(s.getArtist())) {
					message += s.toString() + " in " + a.getTitle() + "\n";
				}
			}
		}
		// if no songs are found, set message to "Song not found!"
		if (message.equals("")) message += "Song not found!";
		return message;
	}
	
	public Song getSong(String title, String artist) {
		for (Album a : albumList) {
			for (Song s : a.getSongs()) {
				if (title.equals(s.getTitle()) && artist.equals(s.getArtist())) {
					// escaping reference is not a problem because Song is immutable
					return s;
				}
			}
		}
		return null;
	}
	
	public Album getAlbum(String title, String artist) {
		for (Album a : albumList) {
			if (title.equals(a.getTitle()) && artist.equals(a.getArtist())) {
				// deeper copy is not needed because Song is immutable
				return new Album(a);
			}
		}
		return null;
	}
}
