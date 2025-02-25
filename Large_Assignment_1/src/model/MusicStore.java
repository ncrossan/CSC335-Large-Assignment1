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
		File directory = new File("Large_Assignment_1/albums");
		albumList = new ArrayList<Album>();
		
		// loop through text files
		for (File file : directory.listFiles()) {
			if (file.getName().equals("albums.txt")) { continue; }
			Scanner fileReader = new Scanner(file);
			parseFile(fileReader);
			
			fileReader.close();
		}
	}
	
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
	
	
}
