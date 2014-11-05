package gui;

import java.awt.Graphics;

public interface GameState {

	public int getId(); // Get the Id of the GameState
	
	public void setName();
	public String getName();
	
	public void init(); // Init the GameState
	
	public void render(Graphics g2d); // Render the GameState's Graphics
	public void update();				// Update the GameState
	public void processInput();			// Process Input for GameState
}
