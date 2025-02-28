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
}
