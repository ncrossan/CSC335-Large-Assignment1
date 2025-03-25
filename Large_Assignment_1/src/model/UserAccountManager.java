/* Authors: Nathan Crossman, Andy Zhang
 * Course: CSC 335
 * Description: An instance of this is a static class that prompts a user for login
 * and handles all data loading/saving for accounts. Files are stored in the Users folder
 * which is created after this code has ran for the first time.
 */
package model;
import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.*;
import java.util.Base64;

public class UserAccountManager {
    private static final String USER_FOLDER = "users";
    static {
    	// make sure the folder exists
        new File(USER_FOLDER).mkdirs();
    }
    /* This method prompts the user for login or account creation
     */
    public static String menu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        String username = "";
        String userFile = "";

        while (!loggedIn) {
            System.out.print("1. Login\n2. Create Account\nChoose option (1 or 2): ");
            int option = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter username: ");
            username = scanner.nextLine().trim();
            userFile = USER_FOLDER + "/" + username + ".txt";

            if (option == 1) {
                if (!new File(userFile).exists()) {
                    System.out.println("Account does not exist. Try again.\n");
                    continue;
                }
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (verifyPassword(username, password)) {
                    System.out.println("Login successful.\n");
                    loggedIn = true;
                }
                else {
                    System.out.println("Incorrect password. Try again.\n");
                }
            }
            else if (option == 2) {
                if (new File(userFile).exists()) {
                    System.out.println("Account already exists. Try again.\n");
                    continue;
                }

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                saveHashedPassword(username, password);
                initializeUserFile(username);
                System.out.println("Account created");
                loggedIn = true;
            } else {
                System.out.println("Invalid option. Try again.\n");
            }
        }
        return userFile;
    }
    /* initalize the user's file if they create an account, the file is named after
     * the username.
     * Arguments:
     * 		username: a String of the username corresponding to an account
     */
    public static void initializeUserFile(String username) throws IOException {
        File file = new File(USER_FOLDER + "/" + username + ".txt");
        // create the file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /* write the password to the .pwd file of the user account after generating the password
     * using the salt and hash functions
     * Arguments:
     *      user: the username of an account
     * 		library: a LibraryModel instance
     */
    private static void saveHashedPassword(String username, String password) throws Exception {
        byte[] saltBytes = generateSalt();
        String salt = Base64.getEncoder().encodeToString(saltBytes);
        String saltedHash = hash(salt + password);

        try (PrintWriter out = new PrintWriter(USER_FOLDER + "/" + username + ".pwd")) {
            out.println(salt);
            out.println(saltedHash);
        }
    }
    
    /* write user data to file in users directory named after the user that can be loaded later
     * Arguments:
     * 		user: the username of an account
     * 		library: a LibraryModel instance
     */
    
    public static void saveUserData(String user, LibraryModel library) throws FileNotFoundException {
    	String output = library.getSongListData() + "\n";
    	output += library.getAlbumListData() + "\n";
    	output += library.getPlayListData() + "\n";
    	output += library.getFavoritesData() + "\n";
    	output += library.getRecentlyPlayedData() + "\n";
    	
    	// write user data to user file
    	try {
    		PrintWriter outputFile = new PrintWriter(user);
    		outputFile.println(output);
    		outputFile.close();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /* load user data to library after login
     * Arguments:
     * 		user: the username of an account
     * 		library: a LibraryModel instance
     */
    public static void loadUserData(String user, LibraryModel library) {
    	try {
    		BufferedReader fileReader = new BufferedReader(new FileReader(user));
    		String line;
    		while ((line = fileReader.readLine()) != null) {
    			if (line.equals("\n") || line.equals("")) continue;
    			if (line.equals("Songs:")) {
    				while ((line = fileReader.readLine()) != "") {
    					if (line.equals("")) break;
    					String[] songArgs = line.split(", ");
    					library.addSong(songArgs[0], songArgs[1]);
    				}
    			}
    			else if (line.equals("Albums:")) {
    				while ((line = fileReader.readLine()) != "") {
    					if (line.equals("")) break;
    					String[] albumArgs = line.split(", ");
    					library.addAlbum(albumArgs[0], albumArgs[1]);
    				}
    			}
    			else if (line.equals("Playlists:")) {
    				String playlist = "";
    				while ((line = fileReader.readLine()) != "") {
    					if (line.equals("")) break;
    					if (line.contains("Playlist:")) {
    						playlist = line.split(" ")[1];
    						library.addPlayList(playlist);
    						continue;
    					}
    					String[] songArgs = line.split(", ");
    					library.addSongToPlayList(playlist, songArgs[0], songArgs[1]);
    				}
    			}
    			else if (line.equals("favorites")) {
    				while ((line = fileReader.readLine()) != "") {
    					if (line.equals("")) break;
    					String[] songArgs = line.split(", ");
    					library.favorite(songArgs[0], songArgs[1]);
    				}
    			}
    			else if (line.equals("Recents:")) {
    				while ((line = fileReader.readLine()) != "") {
    					if (line.equals("")) break;
    					String[] songArgs = line.split(", ");
    					library.playSong(songArgs[0], songArgs[1]);
    				}
    			}
    		}
    		fileReader.close();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /* This method verifys the password for login using BufferedReader to read the password
     * file and the salt and hash functions to determine if the password matches up
     * Arguments:
     * 		username: the username of an account
     * 		password: the password corresponding to the username
     * Returns:
     * 		true or false
     */
    private static boolean verifyPassword(String username, String password) throws Exception {
        File pwdFile = new File(USER_FOLDER + "/" + username + ".pwd");
        if (!pwdFile.exists()) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(pwdFile))) {
            String salt = reader.readLine();
            String storedHash = reader.readLine();
            String computedHash = hash(salt + password);
            return storedHash.equals(computedHash);
        }
    }
    /* This method gets the hash value of a String input using MessageDigest
     * and Base64 libraries and the Secure Hash Algorithm and then returns it
     * Arguments:
     * 		input: A String to get the hash of.
     * Returns:
     * 		a String containing the hash of the String input
     */
    private static String hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashed = md.digest(input.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hashed);
    }
    /* This function generates a random cryptographic salt.
     * Uses the SecureRandom function to simulate a random number and fills a
     * 16 byte array with random bytes, then returns it
     * Returns:
     * 		a random salt.
     */
    private static byte[] generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
