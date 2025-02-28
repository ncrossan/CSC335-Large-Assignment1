package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SongTest {

	@Test
	void testGetTitle() {
		Song song = new Song("When we were Young", "Adele", "19");
		assertEquals(song.getTitle(), "When we were Young");
	}
	
	@Test
	void testGetArtist() {
		Song song = new Song("Hello", "Adele", "19");
		assertEquals(song.getArtist(), "Adele");
	}
	
	@Test
	void testToString() {
		Song song = new Song("Hello", "Adele", "19");
		assertEquals(song.toString(), "Hello by Adele");
	}

}
