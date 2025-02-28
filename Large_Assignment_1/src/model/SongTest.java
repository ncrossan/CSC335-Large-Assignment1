package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SongTest {

	@Test
	void testGetTitle() {
		Song song = new Song("When we were Young", "Adele");
		assertEquals(song.getTitle(), "When we were Young");
	}
	
	@Test
	void testGetArtist() {
		Song song = new Song("Hello", "Adele");
		assertEquals(song.getArtist(), "Adele");
	}
	
	@Test
	void testToString() {
		Song song = new Song("Hello", "Adele");
		assertEquals(song.toString(), "Hello by Adele");
	}

}
