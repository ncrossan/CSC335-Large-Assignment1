package model;

import java.util.ArrayList;
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
	
	// method that reads the text files and loads the Album and Song objects in the albumList
	private void parseFile(Scanner fileReader) {
		String[] textHeader = fileReader.nextLine().split(",");
		String artist = textHeader[1]; // get artist name
		Album album = new Album(textHeader[0], textHeader[1]); // create album object
		albumList.add(album);
		
		// add list of songs to album
		while (fileReader.hasNextLine()) {
			String songName = fileReader.nextLine(); // read song names from file
			Song song = new Song(songName, artist); // create song object
			album.addSong(song);
		}
	}
	
	public Album getAlbumByTitle(String title) {
		for (Album album : albumList) {
			if (album.getTitle().toLowerCase().equals(title.toLowerCase())) {
				return new Album(album);
			}
		}
		return null;
	}
	
	public Song getSongByTitle(String title){
		for (Album album : albumList) {
			for (Song song : album.getSongList()) {
				if (song.getTitle().toLowerCase().equals(title.toLowerCase())) {
					return song;
				}
			} // inner for loop ends
		} // outer for loop ends
		return null;
	}
	
	public Album getAlbumByArtist(String artist) {
		for (Album album : albumList) {
			if (album.getArtist().toLowerCase().equals(artist.toLowerCase())) {
				return new Album(album);
			}
		}
		return null;
	}
	
	public Song getSongByArtist(String artist) {
		for (Album album : albumList) {
			for (Song song : album.getSongList()) {
				if (song.getArtist().toLowerCase().equals(artist.toLowerCase())) {
					return song;
				}
			} // inner for loop ends
		} // outer for loop ends
		return null;
	}
	
}
