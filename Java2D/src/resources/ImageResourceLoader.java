package resources;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageResourceLoader {
	
	private HashMap<String, Image> loadedImages;
	
	public ImageResourceLoader() {
		loadedImages = new HashMap<String, Image>();
	}
	
	/**
	 * Loads an image from a file path
	 * @param key The key used to get the image for future reference
	 * @param path The path to load the image from
	 */
	public void loadImage(String key, String path) {
		File sourceImage = new File(path);
		Image image = null;
		try {
			image = ImageIO.read(sourceImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(image != null) {
			loadedImages.put(key, image);
		}
	}
	
	/***
	 * Get a loaded image from a key
	 * @param key The key for the image to load
	 * @return The Image that corresponds to the associated key or null if key is not found
	 */
	public Image getImage(String key) {
		if(loadedImages.containsKey(key))
			return loadedImages.get(key);
		else
			return null;
	}

}
