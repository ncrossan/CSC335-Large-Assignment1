package view;
import model.LibraryModel;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class View {
	private LibraryModel library;
	public void search(Scanner keyboardScanner) {
		System.out.println("Where would you like to search?\nMusic Store/Library");
		String command = keyboardScanner.nextLine().toLowerCase();
		System.out.println("What are you searching for?\nAlbum/Song");
		String searchFor = keyboardScanner.nextLine().toLowerCase();
		System.out.println("How would you like to search for it?\nby Title/by Artist");
		String searchMethod = keyboardScanner.nextLine().toLowerCase();


		if (command.equals("music store")) {
			if (searchFor.equals("album")) {
				if (searchMethod.equals("by title")) {
					System.out.println("what is the song title?");
					String title = keyboardScanner.nextLine().toLowerCase();
				}
				else if (searchMethod.equals("by artist")) {
					
				}
				
			if (searchFor.equals("song")) {
				
			}
		}
		else if (command.equals("library")) {
			if (searchFor.equals("album")) {
				
			}
				
		}
		}
		else {
			System.out.println("not a valid search area");
		}
	}
	
	// to be implemented.. prompt user for input
	public void prompUser() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		Scanner keyboardScanner = new Scanner(System.in);
		System.out.println("What would you like to do?\nSearch/Add/Get/Favorite/Rate/Exit");
		String command = keyboardScanner.nextLine().toLowerCase();
		// user can type exit to terminate the program.
		if (command.equals("exit")) System.exit(0);
		else if (command.equals("search")) search(keyboardScanner);
		

		
		
		keyboardScanner.close();
	}
	
	
	public static void main(String[] args) {
		
	}
}
