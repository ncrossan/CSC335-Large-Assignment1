package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class MusicStoreTest {
	@Test
	void testSearchAlbumByTitle() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		assertEquals(musicStore.searchAlbumByTitle("Begin Again"), "Begin Again, Norah Jones, Pop, 2018\n"
				+ "My Heart Is Full\n"
				+ "Begin Again\n"
				+ "It Was You\n"
				+ "A Song with No Name\n"
				+ "Uh Oh\n"
				+ "Wintertime\n"
				+ "Just a Little Bit\n");
		assertEquals(musicStore.searchAlbumByTitle("none"), "Album not found!");
		assertNotEquals(musicStore.searchAlbumByTitle("Begin Again"), "Album not found");
	}
	
	@Test
	void testSearchAlbumByArtist() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		assertEquals(musicStore.searchAlbumByArtist("Adele"), "19, Adele, Pop, 2008\n"
				+ "Daydreamer\n"
				+ "Best for Last\n"
				+ "Chasing Pavements\n"
				+ "Cold Shoulder\n"
				+ "Crazy for You\n"
				+ "Melt My Heart to Stone\n"
				+ "First Love\n"
				+ "Right as Rain\n"
				+ "Make You Feel My Love\n"
				+ "My Same\n"
				+ "Tired\n"
				+ "Hometown Glory\n"
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
		assertEquals(musicStore.searchAlbumByArtist("none"), "Album not found!");
	}
	
	@Test
	void testSearchSongByTitle() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		assertEquals(musicStore.searchSongByTitle("After Party"), "After Party by Ozomatli in Don't Mess With the Dragon\n");
		assertEquals(musicStore.searchSongByTitle("none"), "Song not found!");
	}
	
	@Test
	void testSearchSongByArtist() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		assertEquals(musicStore.searchSongByArtist("Norah Jones"), "My Heart Is Full by Norah Jones in Begin Again\n"
				+ "Begin Again by Norah Jones in Begin Again\n"
				+ "It Was You by Norah Jones in Begin Again\n"
				+ "A Song with No Name by Norah Jones in Begin Again\n"
				+ "Uh Oh by Norah Jones in Begin Again\n"
				+ "Wintertime by Norah Jones in Begin Again\n"
				+ "Just a Little Bit by Norah Jones in Begin Again\n"
				+ "");
		assertEquals(musicStore.searchSongByArtist("none"), "Song not found!");
	}
	@Test
	void testGetSong() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		Song song = musicStore.getSong("After Party", "Ozomatli");
		assertEquals(song.toString(), "After Party by Ozomatli");
		Song song2 = musicStore.getSong("AfterParty", "r");
		assertTrue(song2 == null);
	}
	@Test
	void testGetAlbum() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		Album album = musicStore.getAlbum("19", "adele");
		assertTrue(album != null);
		Album album2 = musicStore.getAlbum("3", "adele");
		assertTrue(album2 == null);
	}
}
