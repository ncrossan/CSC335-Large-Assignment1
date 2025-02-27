package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class MusicStoreTest {
	@Test
	void testSearchAlbumByTitle() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		assertEquals(musicStore.searchAlbumByTitle("Begin Again"), "Begin Again, Norah Jones, Pop, 2018\n" +
		"My Heart Is Full, Begin Again, It Was You, A Song with No Name, Uh Oh, Wintertime, Just a Little Bit\n");
		assertEquals(musicStore.searchAlbumByTitle("none"), "Album not found!");
		assertNotEquals(musicStore.searchAlbumByTitle("Begin Again"), "Album not found");
	}
	
	@Test
	void testSearchAlbumByArtist() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		assertEquals(musicStore.searchAlbumByArtist("Adele"), "19, Adele, Pop, 2008\n"
				+ "Daydreamer, Best for Last, Chasing Pavements, Cold Shoulder, Crazy for You, Melt My Heart to Stone, "
				+ "First Love, Right as Rain, Make You Feel My Love, My Same, Tired, Hometown Glory\n"
				+ "21, Adele, Pop, 2011\n"
				+ "Rolling in the Deep, Rumour Has It, Turning Tables, Don't You Remember, Set Fire to the Rain, "
				+ "He Won't Go, Take It All, I'll Be Waiting, One and Only, Lovesong, Someone Like You, I Found a Boy\n");
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
}
