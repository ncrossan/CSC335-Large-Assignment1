package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlbumTest {

	@Test
	void testAddSong() {
		Album album = new Album("19", "Adele", "Pop", 2015);
		Song song = new Song("Song1", "Adele", "19", "Pop");
		album.addSong(song);
		assertTrue(album.getSongs().contains(song));
	}
	
	@Test
	void testToString() {
		Album album = new Album("19", "Adele", "Pop", 2015);
		Song song = new Song("Song1", "Adele", "19", "Pop");
		album.addSong(song);
		assertEquals("19, Adele, Pop, 2015\nSong1\n", album.toString());
	}
	
	@Test
	void testGetTitle() {
		Album album = new Album("21", "Adele", "Pop", 2019);
		assertEquals(album.getTitle(), "21");
	}
	
	@Test
	void testGetArtist() {
		Album album = new Album("19", "Alicia Keys", "Pop", 2015);
		assertEquals(album.getArtist(), "Alicia Keys");
	}
	
	@Test
	void testGetGenre() {
		Album album = new Album("19", "Adele", "Pop", 2015);
		assertEquals(album.getGenre(), "Pop");
	}
	
	@Test
	void testGetYear() {
		Album album = new Album("19", "Adele", "Pop", 2015);
		assertEquals(album.getYear(), 2015);
	}
	
	@Test
	void testCopyConstructors() {
		Album album = new Album("19", "Adele", "Pop", 2015);
		Album albumCopy = new Album(album);
		assertEquals(album.getArtist(), albumCopy.getArtist());
		assertEquals(album.getTitle(), albumCopy.getTitle());
		assertEquals(album.getGenre(), albumCopy.getGenre());
		assertTrue(album.getYear() == albumCopy.getYear());


	}

}
