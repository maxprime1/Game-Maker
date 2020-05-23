package com.gamemaker.statepersistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.gamemaker.model.ComponentGroup;

// To read and write to file.
public class GameDataOperations {
	
	private static final Logger logger = Logger.getLogger(GameDataOperations.class);
	
	public void saveToFile(ComponentGroup component) {

		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		
		GameData gameData = new GameData(component.getComponentMap(), component.getWinMap(), component.getLoseMap(), component.getColor(), component.getFile());
		Date date = new Date();
		long time = date.getTime();
		//String file = getStoragePath()+"game.sav";
		//System.out.println(file);
		try {
			fout = new FileOutputStream("save.sav");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(gameData);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug(ex.getMessage());
		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException ex) {
					logger.debug(ex.getMessage());
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException ex) {
					logger.debug(ex.getMessage());
				}
			}
		}
		
	}

	public ComponentGroup readObjectFromFile(String file) {
		InputStream fileIn = null;
		ObjectInputStream objectIn = null;
		
		try {

			fileIn = new FileInputStream("save.sav");
			objectIn = new ObjectInputStream(fileIn);
			GameData gameData = (GameData)objectIn.readObject();
			ComponentGroup componentGroup = new ComponentGroup();
			componentGroup.setComponentMap(gameData.getComponentMap());
			componentGroup.setWinMap(gameData.getWinMap());
			componentGroup.setLoseMap(gameData.getLoseMap());
			componentGroup.setColor(gameData.getBackgroundColor());
			componentGroup.setFile(gameData.getFile());
			//componentGroup.setBackGroundImage(ImageIO.read(gameData.getFile()));
			
			objectIn.close();
			fileIn.close();
			
			return componentGroup;

		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			return null;
		}finally {
			if(fileIn != null) {
				try {
					fileIn.close();
				}catch (IOException ex) {
					logger.debug(ex.getMessage());
				}
			}
			if(objectIn != null) {
				try {
					objectIn.close();
				} catch (IOException ex) {
					logger.debug(ex.getMessage());
				}
			}
		}
	}
	
	public String getStoragePath() {
		return System.getProperty("user.dir") + File.separator + "GameData" + File.separator;
	}

}
