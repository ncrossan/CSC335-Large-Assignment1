package view;

import java.util.Scanner;

public class View {
	
	
	// to be implemented.. prompt user for input
	public void prompUser() {
		Scanner keyboardScanner = new Scanner(System.in);
		System.out.println("command?");
		String command = keyboardScanner.nextLine();
		
		// user can type exit to terminate the program.
		if (command.toLowerCase().equals("exit")) System.exit(0);
		
		
		keyboardScanner.close();
	}
	
	
	public static void main(String[] args) {
		
	}
}
