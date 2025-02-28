package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class LibraryModelTest {
	
	@Test
	void testAddPlayList() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addPlayList("newPlaylist");
		assertEquals(library.searchPlayListByName("wrongPlaylist"), "wrongPlaylist Playlist not found!");
		library.addSongToPlayList("newPlaylist", "Rolling in the Deep", "Adele");
		assertEquals(library.searchPlayListByName("newPlaylist"), "newPlaylist\nRolling in the Deep by Adele\n");
	}
	
	@Test
	void testAddSongToPlayList() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addPlayList("newPlaylist");
		assertEquals(library.addSongToPlayList("newPlaylist", "Hello", "Adele"), "Song not found!");
		assertEquals(library.addSongToPlayList("newPlaylist", "Rolling in the Deep", "Adele"), "Song added!");
		assertEquals(library.addSongToPlayList("wrongPlaylist", "Rolling in the Deep", "Adele"), "Couldn't perform operation.");
	}
	
	@Test
	void testSearchPlayListByName() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addPlayList("newPlaylist");
		assertEquals(library.removeSongFromPlayList("newPlaylist", "Hello", "Adele"), "Song not found!");
		assertEquals(library.removeSongFromPlayList("newPlaylist", "Rolling in the Deep", "Adele"), "Song removed!");
		assertEquals(library.removeSongFromPlayList("wrongPlaylist", "Rolling in the Deep", "Adele"), "Couldn't perform operation.");
	}
	
	@Test
	void testAddSong() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.addSong("Rolling in the Deep", "Adele"), "added Rolling in the Deep by Adele to library");
		assertEquals(library.addSong("Hello", "Adele"), "Song not found in Music Store!");
	}
	
	@Test
	void testAddAlbum() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.addAlbum("21", "Adele"), "added 21 by Adele to library");
		assertEquals(library.addAlbum("fake", "none"), "Album not found in Music Store!");
	}
	
	@Test
	void testAddRating() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addSong("Lovesong", "Adele");
		assertEquals(library.addRating("Lovesong", "Adele", 4), "Lovesong by Adele was rated 4");
		library.addSong("He Won't Go", "Adele");
		library.addRating("He Won't Go", "Adele", 5);
		assertEquals(library.getFavorites(), "favorites\nHe Won't Go by Adele\n");
	}
	
	@Test
	void testFavorite() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.favorite("Hello", "Adele"), "Song was not found in the library!");
		library.addSong("Lovesong", "Adele");
		assertEquals(library.favorite("Lovesong", "Adele"), "added Lovesong by Adele to favorites");
	}
	
	@Test
	void testGetSongs() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.getSongs(), "No songs in library yet!");
		library.addSong("Lovesong", "Adele");
		assertEquals(library.getSongs(), "Songs in library:\nLovesong\n");
		
	}
	
	@Test
	void testGetAlbums() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.getAlbums(), "No Albums yet!");
		library.addAlbum("21", "Adele");
		assertEquals(library.getAlbums(), "Albums in library:\n"
										+ "21, Adele, Pop, 2011\n"
										+ "Rolling in the Deep\n"
										+ "Rumour Has It\n"
										+ "Turning Tables\n"
										+ "Don't You Remember\n"
										+ "Set Fire to the Rain\n"
										+ "He Won't Go\n"
										+ "Take It All\n"
										+ "I'll Be Waiting\n"
										+ "One and Only\n"
										+ "Lovesong\n"
										+ "Someone Like You\n"
										+ "I Found a Boy\n");
	}
	
	@Test
	void testArtists() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.getArtists(), "No artists in library yet!");
		library.addAlbum("21", "Adele");
		library.addAlbum("Old Ideas", "Leonard Cohen");
		assertEquals(library.getArtists(), "Artists in library:\nAdele\nLeonard Cohen\n");
	}
	
	@Test
	void testGetPlayLists() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.getPlayLists(), "No Playlists yet!");
		library.addPlayList("p1");
		assertEquals(library.getPlayLists(), "There are no songs in your playlist!");
		library.addSongToPlayList("p1", "He Won't Go", "Adele");
		assertEquals(library.getPlayLists(), "p1\nHe Won't Go by Adele\n");
	}
	
	@Test
	void testGetFavorites() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addSong("Lovesong", "Adele");
		library.favorite("Lovesong", "Adele");
		assertEquals(library.getFavorites(), "favorites\nLovesong by Adele\n");
	}
	
	@Test
	void testSearchSongByTitle() {
		
	}
	
	@Test
	void testSearchSongByArtist() {
		
	}
	
	@Test
	void testSearchAlbumByTitle() {
		
	}
	
	@Test
	void testSearchAlbumByArtist() {
		
	}

}
