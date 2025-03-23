/* Authors: Nathan Crossan, Andy Zhang
 * Course: CSC 335
 * Description: the view of the music library
 */
package view;
import model.LibraryModel;


import java.io.FileNotFoundException;
import java.util.Scanner;

public class View {	
	// Prompt the user for input using a makeshift menu.
	public static void promptUser() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		Scanner scanner = new Scanner(System.in);
		// program main loop
        while (true) {
        	// print main menu options
            System.out.println("\n===== Music Library System =====");
            System.out.println("1. Search in Music Store");
            System.out.println("2. Search in User Library");
            System.out.println("3. Add to Library");
            System.out.println("4. Get Lists from Library");
            System.out.println("5. Manage Playlists");
            System.out.println("6. Mark a Song as Favorite");
            System.out.println("7. Rate a Song");
            System.out.println("8. Exit Program");
            System.out.print("Enter your choice with a number: ");
            
            String choice = scanner.nextLine();
            
            // enter specific menus based on user input
            switch (choice) {
            case "1":
                searchMusicStore(library, scanner);
                break;

            case "2":
                searchUserLibrary(library, scanner);
                break;

            case "3":
                addToLibrary(library, scanner);
                break;

            case "4":
                getListsFromLibrary(library, scanner);
                break;

            case "5":
                managePlaylists(library, scanner);
                break;

            case "6":
                markFavorite(library, scanner);
                break;

            case "7":
                rateSong(library, scanner);
                break;

            case "8":
                System.out.println("Exiting...");
                scanner.close();
                return;
            
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
		
	}
    // Search in Music Store
    private static void searchMusicStore(LibraryModel library, Scanner scanner) {
        System.out.println("\n===== Search Music Store =====");
        System.out.println("1. Search for a song by title");
        System.out.println("2. Search for a song by artist");
        System.out.println("3. Search for an album by title");
        System.out.println("4. Search for an album by artist");
        System.out.println("5. Back");

        System.out.print("Enter your choice with a number: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter song title: ");
                String songTitle = scanner.nextLine();
                System.out.println(library.searchStoreSongByTitle(songTitle));
                break;

            case "2":
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();
                System.out.println(library.searchStoreSongByArtist(artist));
                break;

            case "3":
                System.out.print("Enter album title: ");
                String albumTitle = scanner.nextLine();
                System.out.println(library.searchStoreAlbumByTitle(albumTitle));
                break;

            case "4":
                System.out.print("Enter artist: ");
                String albumArtist = scanner.nextLine();
                System.out.println(library.searchStoreAlbumByArtist(albumArtist));
                break;
            case "5":
            	break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    // Search in User Library
    private static void searchUserLibrary(LibraryModel library, Scanner scanner) {
        System.out.println("\n===== Search User Library =====");
        System.out.println("1. Search for a song by title");
        System.out.println("2. Search for a song by artist");
        System.out.println("3. Search for an album by title");
        System.out.println("4. Search for an album by artist");
        System.out.println("5. Search for a playlist by name");
        System.out.println("6. Back");

        System.out.print("Enter your choice with a number: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter song title: ");
                String songTitle = scanner.nextLine();
                System.out.println(library.searchSongByTitle(songTitle));
                break;

            case "2":
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();
                System.out.println(library.searchSongByArtist(artist));
                break;

            case "3":
                System.out.print("Enter album title: ");
                String albumTitle = scanner.nextLine();
                System.out.println(library.searchAlbumByTitle(albumTitle));
                break;

            case "4":
                System.out.print("Enter artist: ");
                String albumArtist = scanner.nextLine();
                System.out.println(library.searchAlbumByArtist(albumArtist));
                break;

            case "5":
                System.out.print("Enter playlist name: ");
                String playlistName = scanner.nextLine();
                System.out.println(library.searchPlayListByName(playlistName));
                break;
            case "6":
            	break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    // Add to Library
    private static void addToLibrary(LibraryModel library, Scanner scanner) {
    	// print library menu options
        System.out.println("\n===== Add to Library =====");
        System.out.println("1. Add a song to the library");
        System.out.println("2. Add an album to the library");
        System.out.println("3. Back");

        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();
        
        // perform operations based on user input
        switch (choice) {
        	// add song to library
            case "1":
                System.out.print("Enter song title: ");
                String songTitle = scanner.nextLine();
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();
                System.out.println(library.addSong(songTitle, artist));
                break;
            
            // add album to library
            case "2":
                System.out.print("Enter album title: ");
                String albumTitleInput = scanner.nextLine();
                System.out.print("Enter artist: ");
                String artistInput = scanner.nextLine();
                System.out.println(library.addAlbum(albumTitleInput, artistInput));
                break;
                
            // return to main menu
            case "3":
            	break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    // Get Lists from Library
    private static void getListsFromLibrary(LibraryModel library, Scanner scanner) {
    	// print listing menu options
        System.out.println("\n===== Library Lists =====");
        System.out.println("1. List of song titles");
        System.out.println("2. List of artists");
        System.out.println("3. List of albums");
        System.out.println("4. List of playlists");
        System.out.println("5. List of favorite songs");
        System.out.println("6. Back");

        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
        	// list songs in library
            case "1":
                System.out.println(library.getSongs());
                break;
            // list artists in library
            case "2":
                System.out.println(library.getArtists());
                break;
            // list albums in library
            case "3":
                System.out.println(library.getAlbums());
                break;
            // list playlists in library
            case "4":
                System.out.println(library.getPlayLists());
                break;
            // list favorite songs in library
            case "5":
                System.out.println(library.getFavorites());
                break;
            // return to main menu
            case "6":
            	break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Manage Playlists 
    private static void managePlaylists(LibraryModel library, Scanner scanner) {
    	// print manage playlists options
        System.out.println("\n===== Manage Playlists =====");
        System.out.println("1. Create a new playlist");
        System.out.println("2. Add a song to a playlist");
        System.out.println("3. Remove a song from a playlist");
        System.out.println("4. Back");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
        	// create new playlist
            case "1":
                System.out.print("Enter playlist name: ");
                String playlistName = scanner.nextLine();
                library.addPlayList(playlistName);
                System.out.println("Playlist created: " + playlistName);
                break;
            // add song to playlist
            case "2":
                System.out.print("Enter playlist name: ");
                String addPlaylistName = scanner.nextLine();
                System.out.print("Enter song title: ");
                String songTitle = scanner.nextLine();
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();
                library.addSongToPlayList(addPlaylistName, songTitle, artist);
                System.out.println("Song added to playlist: " + addPlaylistName);
                break;
            // remove song from playlist
            case "3":
                System.out.print("Enter playlist name: ");
                String removePlaylistName = scanner.nextLine();
                System.out.print("Enter song title: ");
                String removeSongTitle = scanner.nextLine();
                System.out.print("Enter artist: ");
                String removeArtist = scanner.nextLine();
                library.removeSongFromPlayList(removePlaylistName, removeSongTitle, removeArtist);
                System.out.println("Song removed from playlist: " + removePlaylistName);
                break;
            // return to main menu
            case "4":
            	break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Mark a Song as Favorite
    private static void markFavorite(LibraryModel library, Scanner scanner) {
    	// print prompt to favorite song
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();
        System.out.println(library.favorite(title, artist));
    }

    // Rate a Song
    private static void rateSong(LibraryModel library, Scanner scanner) {
    	// print prompt to rate a song
        System.out.println("Enter song title: ");
        String title = scanner.nextLine();
        System.out.println("Enter artist: ");
        String artist = scanner.nextLine();
        Integer rating = null;
        // validate user input
        while (rating == null) {
            System.out.println("Enter rating (1-5): ");
            try {
                rating = Integer.valueOf(scanner.nextLine());
                if (rating < 1 || rating > 5) {
                    System.out.println("Rating must be between 1 and 5. Try again.");
                    rating = null;  // reset rating to force the loop to repeat
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
        System.out.println(library.addRating(title, artist, rating));
    }
	
	public static void main(String[] args) throws FileNotFoundException {
		promptUser();
		// after the end of the user input, end the program
		System.exit(0);
	}
}