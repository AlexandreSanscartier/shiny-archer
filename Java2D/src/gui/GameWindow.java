package gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This is the GameCanvas' main container 
 *@author Alex Sanscartier
 */
public class GameWindow extends Frame implements WindowListener {

	private static final long serialVersionUID = 1L;
	/*==================================================================
	 * 					SCREEN SIZE
	 *===================================================================*/
	final int WIDTH;
	final int HEIGHT;
	boolean fullScreen = false;
	//==================================================================


	/*==================================================================
	 * 				GRAPHICS ENVIRONMENT
	 *===================================================================*/
	static GraphicsEnvironment ge = 
			GraphicsEnvironment.getLocalGraphicsEnvironment();
	static GraphicsDevice gd = ge.getDefaultScreenDevice();
	static GraphicsConfiguration gc = gd.getDefaultConfiguration();
	//==================================================================	

	GameCanvas c; // GameCanvas

	/**
	 * GameWindow's default constructor. Sets the width and height to 800 by 600 respectively.
	 */
	public GameWindow() {
		GameWindowConfiguration cfg = new GameWindowConfiguration();
		setTitle(cfg.title);
		WIDTH = cfg.width;
		HEIGHT = cfg.height;
		initWindow(cfg.fullScreen);
	}
	/**
	 *  This constructor accepts width and height as arguments to set the Window's dimensions
	 *
	 *@param width the width in pixels of the window
	 *@param height the height in pixels of the window
	 */
	public GameWindow(GameCanvas c, GameWindowConfiguration cfg) {
		this.c = c;
		setTitle(cfg.title);
		WIDTH = cfg.width;
		HEIGHT = cfg.height;
		initWindow(cfg.fullScreen);
	}
	/**
	 * initWindow initializes the windows variables so that everything displays properly
	 */
	public void initWindow(boolean full) {		
		// Create game window...
		setIgnoreRepaint( true );
		setResizable(false);
		this.addWindowListener(this); // Add the WindowListening events to the GameWindow
		if(full) { // Disable fullScreen
			this.setSize(WIDTH, HEIGHT);
			this.setUndecorated(true);
			gd.setFullScreenWindow(this);
			if( gd.isDisplayChangeSupported() ) { // Checks if Window can be put in fullscreen 
				gd.setDisplayMode( 
						new DisplayMode( WIDTH, HEIGHT, DisplayMode.BIT_DEPTH_MULTI, DisplayMode.REFRESH_RATE_UNKNOWN ) // Sets window to full screen
						);
			}
		} else {
			this.pack(); // Need to pack once to get the insets
			this.setSize(WIDTH + this.getInsets().left + this.getInsets().right,
					HEIGHT + this.getInsets().top + this.getInsets().bottom);
		}
		// Create canvas for painting...
		this.setLocationRelativeTo(null);
		setVisible(true);
		c.setContainer(this);
		c.setWidthInsets(this.getInsets().left);
		c.setHeightInsets(this.getInsets().top);
		add(c);
		c.requestFocusInWindow(); 
		pack(); // Refreshes the canvas on the Window
	}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) { 
		System.exit(0);
	}
	public void windowDeactivated(WindowEvent e) { }
	public void windowDeiconified(WindowEvent e) { }
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
}