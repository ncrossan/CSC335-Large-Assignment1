package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class PlayListTest {

	@Test
	void testAddSong() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		PlayList playlist = new PlayList("test");
		playlist.addSong(musicStore.getSong("My Heart Is Full", "Norah Jones"));
		assertEquals(playlist.toString(), "test\nMy Heart Is Full by Norah Jones\n");
	}
	
	@Test
	void testRemoveSong() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		PlayList playlist = new PlayList("test");
		playlist.addSong(musicStore.getSong("My Heart Is Full", "Norah Jones"));
		playlist.addSong(musicStore.getSong("Begin Again", "Norah Jones"));
		playlist.removeSong(musicStore.getSong("Begin Again", "Norah Jones"));
		assertEquals(playlist.toString(), "test\nMy Heart Is Full by Norah Jones\n");
	}
	
	@Test
	void testGetName() throws FileNotFoundException {
		PlayList playlist = new PlayList("test");
		assertEquals(playlist.getName(), "test");
	}
	
	@Test
	void testGetSong() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		PlayList playlist = new PlayList("test");
		playlist.addSong(musicStore.getSong("My Heart Is Full", "Norah Jones"));
		assertEquals(String.valueOf(playlist.getPlayList()), "[My Heart Is Full by Norah Jones]");
		PlayList playlist2 = new PlayList("test2");
		assertTrue(playlist2.getPlayList().size() == 0);
	}
	
	@Test
	void testToString() throws FileNotFoundException {
		MusicStore musicStore = new MusicStore();
		PlayList playlist = new PlayList("test");
		assertEquals(playlist.toString(), "test\nThere are no songs in your playlist!");
		playlist.addSong(musicStore.getSong("My Heart Is Full", "Norah Jones"));
		assertEquals(playlist.toString(), "test\nMy Heart Is Full by Norah Jones\n");
	}
}
