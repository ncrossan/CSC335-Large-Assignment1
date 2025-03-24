package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class LibraryModelTest {
	
	@Test
	void testAddPlayList() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addPlayList("newPlaylist");
		library.addSong("Rolling in the Deep", "Adele");
		assertEquals(library.searchPlayListByName("wrongPlaylist"), "wrongPlaylist Playlist not found!");
		library.addSongToPlayList("newPlaylist", "Rolling in the Deep", "Adele");
		assertEquals(library.searchPlayListByName("newPlaylist"), "newPlaylist\nRolling in the Deep by Adele\n");
	}
	
	@Test
	void testAddSongToPlayList() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addPlayList("newPlaylist");
		library.addSong("Rolling in the Deep", "Adele");
		assertEquals(library.addSongToPlayList("newPlaylist", "Hello", "Adele"), "Song not found!");
		assertEquals(library.addSongToPlayList("newPlaylist", "Rolling in the Deep", "Adele"), "Song added!");
		assertEquals(library.addSongToPlayList("wrongPlaylist", "Rolling in the Deep", "Adele"), "Couldn't perform operation.");
	}
	
	@Test
	void testSearchPlayListByName() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addPlayList("newPlaylist");
		library.addSong("Rolling in the Deep", "Adele");
		assertEquals(library.removeSongFromPlayList("newPlaylist", "Hello", "Adele"), "Song not found!");
		assertEquals(library.removeSongFromPlayList("newPlaylist", "Rolling in the Deep", "Adele"), "Song not found!");
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
		assertEquals(library.getPlayLists(), "p1\nThere are no songs in your playlist!\n");
		library.addSong("He Won't Go", "Adele");
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
	void testSearchSongByTitle() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.searchSongByTitle("Lovesong"), "Lovesong not found in library");
		library.addSong("He Won't Go", "Adele");
		assertEquals(library.searchSongByTitle("He Won't Go"), "He Won't Go by Adele in 21");
		assertEquals(library.searchSongByTitle("He Won't Go"), "He Won't Go by Adele in 21\n");
	}
	
	@Test
	void testSearchSongByArtist() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.searchSongByArtist("Adele"), "Adele not found in library");
		library.addSong("He Won't Go", "Adele");
		library.addSong("Daydreamer", "Adele");
		assertEquals(library.searchSongByArtist("Adele"), "He Won't Go by Adele in 21\nDaydreamer by Adele in 19\n");
	}
	
	@Test
	void testSearchAlbumByTitle() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.searchAlbumByTitle("21"), "21 not found in library");
		library.addAlbum("21", "Adele");
		assertEquals(library.searchAlbumByTitle("21"), "21, Adele, Pop, 2011\n"
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
	void testSearchAlbumByArtist() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		assertEquals(library.searchAlbumByArtist("Adele"), "Adele not found in library");
		library.addAlbum("21", "Adele");
		assertEquals(library.searchAlbumByArtist("Adele"), "21, Adele, Pop, 2011\n"
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
	void testPlaySong() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addAlbum("21", "Adele");
		assertEquals(library.playSong("He won't go", "Adele"), "Playing He Won't Go by Adele");
		assertEquals(library.playSong("asd", ""), "asd by  not found in library.");
	}
	
	@Test
	void testGetRecentlyPlayed() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addAlbum("21", "Adele");
		library.playSong("Rolling in the Deep", "Adele");
		library.playSong("Rolling in the Deep", "Adele");
		library.playSong("Rolling in the Deep", "Adele");
		assertEquals(library.getRecentlyPlayed(), "Rolling in the Deep by Adele\n"
				+ "Rolling in the Deep by Adele\n"
				+ "Rolling in the Deep by Adele\n");
		library.playSong("Turning Tables", "Adele");
		library.playSong("Don't You Remember", "Adele");
		library.playSong("Turning Tables", "Adele");
		library.playSong("Someone Like You", "Adele");
		library.playSong("Rolling in the Deep", "Adele");
		library.playSong("Lovesong", "Adele");
		library.playSong("Don't You Remember", "Adele");
		library.playSong("Set Fire to the Rain", "Adele");
		assertEquals(library.getRecentlyPlayed(), "Set Fire to the Rain by Adele\n"
				+ "Don't You Remember by Adele\n"
				+ "Lovesong by Adele\n"
				+ "Rolling in the Deep by Adele\n"
				+ "Someone Like You by Adele\n"
				+ "Turning Tables by Adele\n"
				+ "Don't You Remember by Adele\n"
				+ "Turning Tables by Adele\n"
				+ "Rolling in the Deep by Adele\n"
				+ "Rolling in the Deep by Adele\n");
	}
	
	@Test
	// write this test case last as the order shifts when changing code
	void testGetMostPlayedSongs() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addAlbum("21", "Adele");
		library.playSong("Rolling in the Deep", "Adele");
		library.playSong("Rolling in the Deep", "Adele");
		library.playSong("Rolling in the Deep", "Adele");
		assertEquals(library.getMostPlayedSongs(), "Rolling in the Deep by Adele: 3 plays\n");
		library.playSong("Turning Tables", "Adele");
		library.playSong("Don't You Remember", "Adele");
		library.playSong("Turning Tables", "Adele");
		library.playSong("Someone Like You", "Adele");
		library.playSong("Rolling in the Deep", "Adele");
		library.playSong("Lovesong", "Adele");
		library.playSong("Don't You Remember", "Adele");
		library.playSong("Set Fire to the Rain", "Adele");
		assertEquals(library.getMostPlayedSongs(), "Rolling in the Deep by Adele: 4 plays\n"
				+ "Turning Tables by Adele: 2 plays\n"
				+ "Don't You Remember by Adele: 2 plays\n"
				+ "Lovesong by Adele: 1 plays\n"
				+ "Set Fire to the Rain by Adele: 1 plays\n"
				+ "Someone Like You by Adele: 1 plays\n");
		library.addAlbum("19", "Adele");
		library.playSong("Daydreamer", "Adele");
		library.playSong("Best for Last", "Adele");
		library.playSong("Chasing Pavements", "Adele");
		library.playSong("Crazy for You", "Adele");
		library.playSong("Melt My Heart to Stone", "Adele");
		library.playSong("Melt My Heart to Stone", "Adele");
		assertEquals(library.getMostPlayedSongs(), "Rolling in the Deep by Adele: 4 plays\n"
				+ "Turning Tables by Adele: 2 plays\n"
				+ "Melt My Heart to Stone by Adele: 2 plays\n"
				+ "Don't You Remember by Adele: 2 plays\n"
				+ "Lovesong by Adele: 1 plays\n"
				+ "Set Fire to the Rain by Adele: 1 plays\n"
				+ "Best for Last by Adele: 1 plays\n"
				+ "Chasing Pavements by Adele: 1 plays\n"
				+ "Crazy for You by Adele: 1 plays\n"
				+ "Someone Like You by Adele: 1 plays\n");
	}
	
	@Test
	void testGetSortedSongsByTitle() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addAlbum("21", "Adele");
		library.addAlbum("19", "Adele");
		assertEquals(library.getSortedSongsByTitle(), "Best for Last by Adele\n"
				+ "Chasing Pavements by Adele\n"
				+ "Cold Shoulder by Adele\n"
				+ "Crazy for You by Adele\n"
				+ "Daydreamer by Adele\n"
				+ "Don't You Remember by Adele\n"
				+ "First Love by Adele\n"
				+ "He Won't Go by Adele\n"
				+ "Hometown Glory by Adele\n"
				+ "I Found a Boy by Adele\n"
				+ "I'll Be Waiting by Adele\n"
				+ "Lovesong by Adele\n"
				+ "Make You Feel My Love by Adele\n"
				+ "Melt My Heart to Stone by Adele\n"
				+ "My Same by Adele\n"
				+ "One and Only by Adele\n"
				+ "Right as Rain by Adele\n"
				+ "Rolling in the Deep by Adele\n"
				+ "Rumour Has It by Adele\n"
				+ "Set Fire to the Rain by Adele\n"
				+ "Someone Like You by Adele\n"
				+ "Take It All by Adele\n"
				+ "Tired by Adele\n"
				+ "Turning Tables by Adele\n");
	}
	
	@Test
	void testGetSortedSongsByArtist() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addAlbum("21", "Adele");
		library.addAlbum("Old Ideas", "Leonard Cohen");
		assertEquals(library.getSortedSongsByArtist(), "Rolling in the Deep by Adele\n"
				+ "Rumour Has It by Adele\n"
				+ "Turning Tables by Adele\n"
				+ "Don't You Remember by Adele\n"
				+ "Set Fire to the Rain by Adele\n"
				+ "He Won't Go by Adele\n"
				+ "Take It All by Adele\n"
				+ "I'll Be Waiting by Adele\n"
				+ "One and Only by Adele\n"
				+ "Lovesong by Adele\n"
				+ "Someone Like You by Adele\n"
				+ "I Found a Boy by Adele\n"
				+ "Going Home by Leonard Cohen\n"
				+ "Amen by Leonard Cohen\n"
				+ "Show Me the Place by Leonard Cohen\n"
				+ "Darkness by Leonard Cohen\n"
				+ "Anyhow by Leonard Cohen\n"
				+ "Crazy to Love You by Leonard Cohen\n"
				+ "Come Healing by Leonard Cohen\n"
				+ "Banjo by Leonard Cohen\n"
				+ "Lullaby by Leonard Cohen\n"
				+ "Different Sides by Leonard Cohen\n");
	}
	
	@Test
	// order changes if two songs have the same rating so write this testcase last
	void testGetSortedRatings() throws FileNotFoundException {
		LibraryModel library = new LibraryModel();
		library.addAlbum("21", "Adele");
		library.addRating("Take It All", "Adele", 5);
		library.addRating("Someone Like You", "Adele", 3);
		library.addRating("Don't You Remember", "Adele", 1);
		library.addRating("Rolling in the Deep", "Adele", 2);
		library.addRating("One and Only", "Adele", 5);
		assertEquals(library.getSortedRatings(), "Don't You Remember by Adele: 1\n"
				+ "Rolling in the Deep by Adele: 2\n"
				+ "Someone Like You by Adele: 3\n"
				+ "Take It All by Adele: 5\n"
				+ "One and Only by Adele: 5\n");
	}

}
