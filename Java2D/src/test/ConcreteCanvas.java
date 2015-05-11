package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import gui.GameCanvas2D;
import test.player.*;
import test.creature.*;

public class ConcreteCanvas extends GameCanvas2D {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private PlayerTest player;
    private PlayerInput input;
    private PlayerGraphics graphics;
    private PlayerPhysics physics;

    private CreatureTest creature;
    private CreatureInput Cinput;
    private CreatureGraphics Cgraphics;
    private CreaturePhysics Cphysics;

	public ConcreteCanvas() {
        input = new PlayerInput(board);
        graphics = new PlayerGraphics();
        physics = new PlayerPhysics();
        player = new PlayerTest(graphics, input, physics);

        Cinput = new CreatureInput();
        Cgraphics = new CreatureGraphics();
        Cphysics = new CreaturePhysics();
        creature = new CreatureTest(Cgraphics, Cinput, Cphysics);
    }
	
	public void render(Graphics2D g2d) {
		
		g2d.setColor(Color.white);
		g2d.drawRect(50, 50, 100, 100);

        graphics.update(player, g2d);
        Cgraphics.update(creature, g2d);
	}
	
	public void update() {

        input.update(player);
        Cinput.update(creature);
		
		if(mousey.buttonDown(MouseEvent.BUTTON1))
		    System.out.println(String.format("Mouse Down: (X:%f, Y:%f)",mousey.getPosition().getX(),mousey.getPosition().getY()));
	}
}
