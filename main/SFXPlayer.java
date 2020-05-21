package main;

import java.util.ArrayList;
import java.util.TreeMap;

public class SFXPlayer implements Runnable {
	
	/** A list of all the sound effects given by the constructor */
	private ArrayList<AudioFile> sfxFiles;
	/** The current sound effect we are playing */
	public AudioFile song;
	
	/** Creates a new SoundEffectsPlayer given an input of filenames
	 *  SFXPlayer assumes these files are stored in a resource folder named res
	 *  SFXPlayer also assumes these files are .wav compatable
	 * @param files
	 */
	public SFXPlayer(String...files) {
		sfxFiles = new ArrayList<>();
		for (String file : files) {
			sfxFiles.add(new AudioFile("./res/" + file+ ".wav"));
		}
		
	}
	
	/** Plays a sound effect at the specified index in the list of effects */
	public void playSFX(int index) {
		song = sfxFiles.get(index);
		song.play();
	}
	
	/** Plays a sound effect at the specified index at a volume that is
	 * volumeDiff away from the standard
	 * @param index - index of Sound Effect
	 * @param volumeDiff - difference in volume of sound effect
	 */
	public void playSFXatVolume(int index, int volumeDiff) {
		song = sfxFiles.get(index);
		song.play(volumeDiff);
	}
	
	/** Halts the current SFX song from continuing */
	public void stopSFX() {
		song.stop();
	}
	
	/** Plays a sound effect if its filename matches one provided in SFXPlayer constructor */
	public void playSongByFilename(String filename) {
		for (AudioFile song : sfxFiles) {
			if (song.fileName.equalsIgnoreCase(filename)) {
				song.play();
				return;
			}
		}
	}
	
	@Override
	public void run() {
		// this stays empty because unlike the music player we only play
		// sound effects when it is called in game
	}
}
