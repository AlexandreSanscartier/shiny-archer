package gui;

import input.CanvasKeyboard;
import input.CanvasMouse;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.*;

/**
 * The GameCanvas is where all the game rendering and updating is done. It's the main container for the game
 *@author Alexandre Sanscartier
 */
public abstract class GameCanvas extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	/*==================================================================
	 * 			Variables Affecting the UPS/FPS
	 *===================================================================*/
	final int TICKS_PER_SECOND = 25;
	final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
	final int MAX_FRAMESKIP = 5;
	int DELAY = 0;

	long next_game_tick = System.currentTimeMillis();
	int loops;
	//==================================================================

	/*==================================================================
	 * 			FPS/UPS/CLOCK Counter
	 *===================================================================*/
	FPSCounter fps = new FPSCounter();
	FPSCounter ups = new FPSCounter();
	Clock clock = new Clock();
	//==================================================================

	/*==================================================================
	 * 			VARIABLES FOR RETRIEVING INPUT
	 *===================================================================*/
	//Camera c = Camera.getInstance();
	CanvasKeyboard board = CanvasKeyboard.getInstance();
	CanvasMouse mousey = CanvasMouse.getInstance(this);
	//=================================================================

	/*===================================================================
	 * 			VARIABLES FOR DRAWING
	 *===================================================================*/
	static GameCanvas instance = null;
	GameWindow container;
	final int WIDTH;
	final int HEIGHT;
	int widthInsets;
	int heightInsets;
	Graphics graphics = null;
	Graphics2D g2d = null;
	Color background = Color.BLACK;
	Thread animator = null;
	//==================================================================

	/*==================================================================
	 * 			VARIABLES FOR GAME FONTS
	 *==================================================================*/
	Font font  = new Font("Arial", Font.PLAIN, 12);
	//==================================================================

	/*==================================================================
	 * 			VARIABLES FOR THE GRAPHICS
	 *===================================================================*/	
	// Variables for double buffering
	BufferedImage bi;
	BufferStrategy buffer;

	// Get graphics configuration...
	static GraphicsEnvironment ge = 
			GraphicsEnvironment.getLocalGraphicsEnvironment();
	static GraphicsDevice gd = ge.getDefaultScreenDevice();
	static GraphicsConfiguration gc = gd.getDefaultConfiguration();
	//================================================================= 

	/*==================================================================
	 * 			VARIABLES FOR MANAGING GAME STATES
	 *===================================================================*/
	//GameStateManager gsm = GameStateManager.getInstance();
	//================================================================= 

	/*==================================================================
	 * 			VARIABLES AFFECTING RUNTIME
	 *===================================================================*/	
	volatile boolean isRunning = false;
	private volatile boolean isPaused = false;
	//================================================================= 

	/**
	 *  The default constructor for the GameCanvas. This sets the WIDTH and HEIGHT to 800 by 600 respectively
	 * by default and is used as the default screen resolution. Should only be used if the game will always use the same resolution.
	 */
	public GameCanvas() {
		super(gc);
		WIDTH = 800;
		HEIGHT = 600;
		requestFocus();
		setIgnoreRepaint( true );
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.addKeyListener( board );
		this.addMouseListener( mousey );
		this.addMouseMotionListener(mousey);
	}
	/**
	 * The main constructor for the GameCanvas. This constructor is used to specify the size of the canvas
	 *@param width the width of the Canvas
	 *@param height the height of the Canvas
	 *
	 */
	public GameCanvas(int width, int height, GameWindow gw) {
		super(gc);
		WIDTH = width;
		HEIGHT = height;
		requestFocus();
		setIgnoreRepaint( true );
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.addKeyListener(board);
		this.addMouseListener(mousey);
		this.addMouseMotionListener(mousey);
	}
	/**
	 * addNotify is called when the GameCanvas is added to the GameWindow. This starts the game
	 */
	public void addNotify() {
		super.addNotify();
		// Create BackBuffer...
		createBufferStrategy( 2 );
		buffer = getBufferStrategy();

		// Create off-screen drawing surface
		bi = gc.createCompatibleImage( WIDTH, HEIGHT );
		if (animator == null) {
			try {
				animator = new Thread(this, "GameLoop");
				animator.start();	    		
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/**
	 * initGame Initializes the whole game
	 */
	private void initGame() {
		this.setFocusTraversalKeysEnabled(false);
		g2d = bi.createGraphics();
		/*==================================================================
		 * 			INITIALIZE THE GAME HERE
		 *===================================================================*/

		//==================================================================
	}
	/**
	 * run is the game's gameloop and is called as long as the game is running
	 */
	public void run() {
		/*==================================================================
		 * 				THE GAMELOOP THREAD
		 *===================================================================*/

		//==================================================================

		initGame();
		isRunning = true;

		//ProcessThread key = new ProcessThread(board, mousey, gsm, this);
		//key.start();

		while( isRunning ) {
			gameUpdate();
			gameRender();
		}
	}
	/**
	 * processInput processes the users input from the KeyBoard and Mouse
	 */
	protected void processInput() {
		/*==================================================================
		 * 				PROCESS INPUT HERE
		 *===================================================================*/
		if(board.keyDownOnce(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
		//==================================================================
	}
	/**
	 * gameUpdate updates the state of the game every second  for TICKS_PER_SECOND times
	 */
	private void gameUpdate() {
		loops = 0;
		while( System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
			board.poll();
			mousey.poll();
			processInput();
			/*==================================================================
			 * 			GAME UPDATE METHODS
			 *===================================================================*/
			update();
			//==================================================================

			/*==================================================================
			 * 			GAME UPDATES PER SECOND / CLOCK
			 *===================================================================*/
			ups.registerTick();
			clock.registerTick();
			//==================================================================

			DELAY++;
			next_game_tick += SKIP_TICKS;
			loops++;
		}
	}
	/**
	 * gameRender renders the game on the offscreen buffer.
	 */
	int low = 500;
	private void gameRender() {
		// clear back buffer...
		g2d = bi.createGraphics();
		g2d.setColor( background );
		g2d.fillRect( 0, 0, WIDTH - 1, HEIGHT - 1 );
		/*==================================================================
		 * 			GAME RENDERING METHODS
		 *===================================================================*/
		render(g2d);
		//==================================================================

		/*==================================================================
		 * 			FRAMES PER SECOND / UPDATES PER SECOND
		 *===================================================================*/
		g2d.setFont(font);
		fps.registerTick();
		fps.draw(g2d, "FPS: ", 90, 20);
		if (fps.fps < low){
			low = fps.fps;
		}
		g2d.drawString("Lowest FPS: " + low, 50, 500);
		ups.draw(g2d, "UPS: ", 160, 20);
		clock.draw(g2d, 240, 20);
		g2d.drawString("mX:" + (int)mousey.getPosition().getX() + " mY:" + (int)mousey.getPosition().getY(), 270, 20);
		//=================================================================
		paintOnScreen(); // Render the back buffer on screen
	}
	/**
	 * paintOnScreen takes the Canvas' offscreen buffer and displays it on the screen. It also disposes the buffer when it is done.
	 * This method must be called after the rendering to the buffer has been made or nothing will be displayed.
	 */
	private void paintOnScreen() {
		try {
			graphics = buffer.getDrawGraphics();
			graphics.drawImage( bi, 0, 0, null );

			if( !buffer.contentsLost() )
				buffer.show();

			Thread.yield(); // Let the OS have a little time...
		} finally {
			// release resources
			if( graphics != null ) 
				graphics.dispose();
			if( g2d != null ) 
				g2d.dispose();
		}
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font f) {
		font = f;
	}
	public Graphics getGraphics() {
		return g2d;
	}
	/**
	 * @param isPaused the isPaused to set
	 */
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	/**
	 * @return the isPaused
	 */
	public boolean isPaused() {
		return isPaused;
	}
	public int getWidthInsets() {
		return widthInsets;
	}
	public int getHeightInsets() {
		return heightInsets;
	}
	public void setWidthInsets(int w) {
		widthInsets = w;
	}
	public void setHeightInsets(int h) {
		heightInsets = h;
	}

	public void setContainer(GameWindow gw) {
		this.container = gw;
	}

	public void render(Graphics2D g2d) {};
	public void update() {};
}
