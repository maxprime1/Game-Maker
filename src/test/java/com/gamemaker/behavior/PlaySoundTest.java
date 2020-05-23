package com.gamemaker.behavior;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlaySoundTest{

	@Test
	public void test() throws InterruptedException {
		PlaySound playSound = new PlaySound("explode.wav");
		playSound.play(); 
		//assertEquals(1773469, playSound.getClip().getMicrosecondLength());
	}
}
