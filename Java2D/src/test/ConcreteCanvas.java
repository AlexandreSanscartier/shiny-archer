package test;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.GameCanvas2D;

public class ConcreteCanvas extends GameCanvas2D {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConcreteCanvas() {
		
	}
	
	public void render(Graphics2D g2d) {
		
		g2d.setColor(Color.white);
		g2d.drawRect(50, 50, 100, 100);
		
	}
	
	public void update() {
		
		
		
	}
}
