package model;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class UserAccountManagerTest {

	@Test
	void testInitializeUserFile() throws IOException {
		UserAccountManager.initializeUserFile("andy");
		File file = new File("users/andy.txt");
		assertTrue(file.exists());
		File file2 = new File("users/ben.txt");
		assertFalse(file2.exists());
	}

}
