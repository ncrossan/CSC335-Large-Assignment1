package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class MusicStore {
	private ArrayList<Album> albumList;
	
	public MusicStore() throws FileNotFoundException {
		// load albums and songs from text files
		File directory = new File("Large_Assignment_1/albums");
		
		for (File file : directory.listFiles()) {
			Scanner fileReader = new Scanner(file);
			
			
			fileReader.close();
		}
	}
	
	private void parseFiles(Scanner fileReader) {
		
	}
}
