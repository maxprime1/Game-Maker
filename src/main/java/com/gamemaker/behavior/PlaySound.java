package com.gamemaker.behavior;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.log4j.Logger;

public class PlaySound {
	
	private static final Logger logger = Logger.getLogger(PlaySound.class);
	private Clip clip;
	
	public PlaySound(String fileName) {
		//String filePath = System.getProperty("user.dir") + File.separator + "GameData" + File.separator + "sounds" + File.separator + fileName;
		String filePath = "GameData" + File.separator + "sounds" + File.separator + fileName;
		/**
		 * The following code for playing audio clip is referenced from: https://stackoverflow.com/questions/25171205/playing-sound-in-java
		 */
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(ClassLoader.getSystemResource(filePath));        
	         // Get a sound clip resource.
	        this.clip = AudioSystem.getClip();
	        this.clip.open(audioIn);
	        audioIn.close();
		} catch (IOException | LineUnavailableException| UnsupportedAudioFileException e) {
			logger.debug(e.getMessage());
		}
		
	}

	public void play() throws InterruptedException {
		// Following future code source: https://www.callicoder.com/java-8-completablefuture-tutorial/
		// Run a task specified by a Runnable Object asynchronously.
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
		    @Override
		    public void run() {
		        // Simulate a long-running Job
		        clip.start();
		    }
		});	
		try {
			future.get();
			if(future.isDone()) {
				future.cancel(true);
			}
		} catch (ExecutionException e) {
			logger.debug(e.getMessage());
		}
	}
	
	public Clip getClip() {
		return this.clip;
	}
}
