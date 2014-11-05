package gui;

import java.awt.Color;
import java.awt.Graphics;

/**
* FPSCounter is an arbitrary counter that can count the average of any called method  within a second
*@author Alex Sanscartier
*/
public class FPSCounter {
	
    // Variables for counting frames per seconds
    int fps = 10000;
    int frames = 0;
    long totalTime = 0;
    long curTime = System.currentTimeMillis();
    long lastTime = curTime;

	
	/**
	*  the default constructor used to instantiate the object
	*/
	public FPSCounter() {
		//Leave blank for instantiation
	}
	/**
	 * register_tick registers the calls and registers the average after one second.
	 */
	public void registerTick() {
		// count Frames per second...
	    lastTime = curTime;
	    curTime = System.currentTimeMillis();
	    totalTime += curTime - lastTime;
	    if( totalTime > 1000 ) {
	    	totalTime -= 1000;
	         fps = frames;
	         frames = 0;
	         } 
	    ++frames;
	}
	/**
	 * draws the average on the screen at x, y.
	 * @param g2d the graphics to draw on
	 */
	public void draw(Graphics g2d, String text, int x, int y) {
		g2d.setColor(Color.GREEN);
		g2d.drawString(text + fps, x, y);
	}
}
