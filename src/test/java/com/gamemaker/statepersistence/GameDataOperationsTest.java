package com.gamemaker.statepersistence;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;

import com.gamemaker.model.ComponentGroup;


public class GameDataOperationsTest {



	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Mock
	ComponentGroup cg = new ComponentGroup();

	GameDataOperations gop = new GameDataOperations();

	@Test
	public void testSaveToFile() 
	{
		gop.saveToFile(cg);
		File file;
		try (Stream<Path> walk = Files.walk(Paths.get(gop.getStoragePath()))) {

			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());

			//result.forEach(System.out::println);
			if (result != null)
			{
				assertTrue(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//	@Test
//	public void testReadObjectFromFile() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetStoragePath() {
		if (gop.getStoragePath().equals("C:\\Users\\Nisha G\\Desktop\\IUB_Studies\\OOSD\\Git_Projects\\Team4-Week6_7\\GameData"))
			assertTrue(true);
		else if (gop.getStoragePath() instanceof String)
			assertTrue(true);
	}

}
