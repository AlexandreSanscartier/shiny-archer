package resources;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//TODO: Determine how ImageResourceLoader and SpriteSheet should communicate
public class ImageManipulator {

	//The image corresponding to the SpriteSheet
	private BufferedImage image;

	public ImageManipulator() {
		String filePath = "C:\\Users\\sea-alex\\Pictures\\megaman7.png";

		ImageResourceLoader irl = new ImageResourceLoader(); 

		irl.loadImage("MEGAMAN_1", filePath);
		image = (BufferedImage)irl.getImage("MEGAMAN_1");
	}

	public BufferedImage getImage() {
		return image;
	}
	
	//TODO: Optimize
	public void trimFillerColor(final int FILLER_COLOR) {
		int[][] pixelData = getPixelData();
		
		boolean[] rowsToKeep = new boolean[pixelData.length];
		boolean[] colsToKeep = new boolean[pixelData[0].length];
		
		for(int i = 0; i < pixelData.length; i++) {
			for(int j = 0; j < pixelData[i].length; j++) {
				if(pixelData[i][j] != FILLER_COLOR) {
					rowsToKeep[i] = true;
					colsToKeep[j] = true;
				}
			}
		}
		
		int rows = 0;
		int cols = 0;
		
		for(int i = 0; i < rowsToKeep.length; i++) {
			if(rowsToKeep[i])
				rows++;
		}
		for(int i = 0; i < colsToKeep.length; i++) {
			if(colsToKeep[i])
				cols++;
		}

		BufferedImage copy = new BufferedImage(cols,rows,image.getType());
		
		int skipW = 0, skipH = 0;
		
		for(int i = 0; i < pixelData.length; i++) {
			skipW = 0;
			if(!rowsToKeep[i]) {
				skipH++;
			}
			for(int j = 0; j < pixelData[i].length; j++) {
				if(!colsToKeep[j]) {
					skipW++;
				}
				if(rowsToKeep[i] && colsToKeep[j]) {
					int value = image.getRGB(j, i);
					copy.setRGB(j-skipW, i-skipH, value);
				}
			}
		}		
		
		File outputFile = new File("C:\\Users\\sea-alex\\Pictures\\megaman7_copy.png");
		try {
			ImageIO.write(copy, "png", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private int[][] getPixelData() {
		final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		final int width = image.getWidth();
		final int height = image.getHeight();
		final boolean hasAlphaChannel = image.getAlphaRaster() != null;

		int[][] result = new int[height][width];
		if (hasAlphaChannel) {
			final int pixelLength = 4;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
				argb += ((int) pixels[pixel + 1] & 0xff); // blue
				argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
				result[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		} else {
			final int pixelLength = 3;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += -16777216; // 255 alpha
				argb += ((int) pixels[pixel] & 0xff); // blue
				argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
				argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
				result[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		}
		
		return result;
	}


	public static void main(String[] args) {
		ImageManipulator s = new ImageManipulator();
		BufferedImage i = s.getImage();
		int[][] pixelData = s.getPixelData();
		s.trimFillerColor(-16716765);
	}

}
