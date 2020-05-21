package main;

import java.util.ArrayList;

public class MusicPlayer implements Runnable {
	/** List of Music Files for us to play */
	private ArrayList<AudioFile> musicFiles;
	/** The index of the current song we are playing */
	private int currentSongIndex;
	/** Whether or not our task is running or not */
	private boolean running;
	/** The song we are currently playing */
	public AudioFile song;
	
	/** Creates a new Music Player with a list of song filenames
	 *  Assumes these files live in a resource folder named res
	 *  And are compatible with the .wav extension 
	 * @param files - list of file names
	 */
	public MusicPlayer(String...files) {
		musicFiles = new ArrayList<>();
		currentSongIndex = 0;
		for (String file : files) {
			musicFiles.add(new AudioFile("./res/" + file+ ".wav"));
		}
	}
	
	@Override
	public void run() {
		running = true;
		song = musicFiles.get(currentSongIndex);
		song.play();
		while (running) {
			if (!song.isPlaying()) {
				currentSongIndex++;
				if (currentSongIndex >= musicFiles.size()) {
					currentSongIndex = 0;
				}
				song = musicFiles.get(currentSongIndex);
				song.play();
			}
		}
	
	}
	/** Queues the next Song in our list */
	public void nextSong() {
		song.stop();
	}
	/** Plays a song based on the filenames provided in MusicPlayer's constructor */
	public void playSong(String fileName) {
		int songindex = -1;
		for (int i = 0; i < musicFiles.size(); i++) {
			if (musicFiles.get(i).fileName.equalsIgnoreCase(fileName)) {
				songindex = i;
				break;
			}
		}
		if (songindex >= 0) {
			currentSongIndex = songindex-1;
			song.stop();
		}
	}
}
