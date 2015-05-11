package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import gui.GameCanvas2D;
import test.player.PlayerGraphics;
import test.player.PlayerInput;
import test.player.PlayerPhysics;
import test.player.PlayerTest;

public class ConcreteCanvas extends GameCanvas2D {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private PlayerTest player;
    private PlayerInput input = new PlayerInput(board);
    private PlayerGraphics graphics = new PlayerGraphics();
    private PlayerPhysics physics = new PlayerPhysics();

	public ConcreteCanvas() {
        input = new PlayerInput(board);
        graphics = new PlayerGraphics();
        physics = new PlayerPhysics();
        player = new PlayerTest(graphics, input, physics);
	}
	
	public void render(Graphics2D g2d) {
		
		g2d.setColor(Color.white);
		g2d.drawRect(50, 50, 100, 100);

        graphics.update(player, g2d);
	}
	
	public void update() {

        input.update(player);
		
		if(mousey.buttonDown(MouseEvent.BUTTON1))
		    System.out.println(String.format("Mouse Down: (X:%f, Y:%f)",mousey.getPosition().getX(),mousey.getPosition().getY()));
	}
}
