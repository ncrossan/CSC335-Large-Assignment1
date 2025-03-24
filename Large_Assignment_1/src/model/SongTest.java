package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SongTest {

	@Test
	void testGetTitle() {
		Song song = new Song("When we were Young", "Adele", "19", "Pop");
		assertEquals(song.getTitle(), "When we were Young");
	}
	
	@Test
	void testGetArtist() {
		Song song = new Song("Hello", "Adele", "19", "Pop");
		assertEquals(song.getArtist(), "Adele");
	}

	@Test
	void testGetAlbum() {
		Song song = new Song("Hello", "Adele", "19", "Pop");
		assertEquals(song.getAlbum(), "19");
	}
	@Test
	void testToString() {
		Song song = new Song("Hello", "Adele", "19", "Pop");
		assertEquals(song.toString(), "Hello by Adele");
	}

}
