package model;
import java.io.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.*;
import java.util.Base64;

public class UserAccountManager {
    private static final String USER_FOLDER = "users";
    private static File user_file;
    static {
    	// make sure the folder exists
        new File(USER_FOLDER).mkdirs();
    }

    public static void menu() throws Exception {
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
    }

    public static void initializeUserFile(String username) throws IOException {
        File file = new File(USER_FOLDER + "/" + username + ".txt");
        // create the file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
        }
        user_file = file;
    }

    // write the password to the .pwd file of the user account
    private static void saveHashedPassword(String username, String password) throws Exception {
        byte[] saltBytes = generateSalt();
        String salt = Base64.getEncoder().encodeToString(saltBytes);
        String saltedHash = hash(salt + password);

        try (PrintWriter out = new PrintWriter(USER_FOLDER + "/" + username + ".pwd")) {
            out.println(salt);
            out.println(saltedHash);
        }
    }
    // verify the password for login
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
    // hash function
    private static String hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashed = md.digest(input.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hashed);
    }
    // salt the password
    private static byte[] generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
