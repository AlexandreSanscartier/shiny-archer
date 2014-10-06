package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;

/**
* The clock class shows the time elapsed 
* @author Alexandre Sanscartier
*/
public class Clock {

	/*==================================================================
	* 			VARIABLES FOR MANIPULATING THE CLOCK
	*===================================================================*/
    long totalTime = 0;
    long curTime = System.currentTimeMillis();
    long lastTime = curTime;
	int seconds = 0,minutes = 0, hours = 0;
	//==================================================================	
	
	/**
	*  the default constructor just to instantiate Clock
	*/
	public Clock() {
		//For instantiation
	}
	/**
	*  registerTicks updates the clock every second and increases the proper variables accordingly.
	* Call this method in the gameUpdate.
	*/
	public void registerTick() {
		// count the seconds that have passed
	    lastTime = curTime;
	    curTime = System.currentTimeMillis();
	    totalTime += curTime - lastTime;
	    if( totalTime > 1000 ) {
	    	totalTime -= 1000;
	    		seconds++;
	    		if(seconds >= 60) {
	    			seconds -= 60;
	    			minutes++;
					if(minutes >= 60) {
						minutes -= 60;
						hours++;
					}
	    		}
	         } 
	}
	/**
	* The draw method draws the clock at the specified x and y position.
	*@param g2d  the Graphics object to draw on
	*@param x the x position of the clock
	*@param y the y position of the clock
	*/
	public void draw(Graphics g2d, int x, int y) {
		g2d.setColor(Color.GREEN);
		g2d.drawString(toString(), x, y);
	}
	/**
	* The draw method draws the clock at the x position 15 and the y position 40.
	*@param g2d the Graphics object to draw on
	*/
	public void draw(Graphics g2d) {
		g2d.setColor(Color.GREEN);
		g2d.drawString(toString(), 15, 40);	
	}
	/**
	* The toString method returns the clock in a formatted String
	*@return the formatted String
	*/
	public String toString() {
		DecimalFormat h = new DecimalFormat("##");
	    DecimalFormat m = new DecimalFormat("#0");
	    DecimalFormat s = new DecimalFormat("00");
	    if(hours > 0)
	    	return  h.format(hours) + ":" + m.format(minutes) + ":" + s.format(seconds);
	    return m.format(minutes) + ":" + s.format(seconds);
	    
	}
}
