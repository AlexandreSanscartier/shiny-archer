package test.player;

import component.GameObject;
import component.GraphicsComponent;
import component.InputComponent;
import component.PhysicsComponent;

/**
 * Created by Alexandre Sanscartier on 11/05/2015.
 */
public class PlayerTest extends GameObject {

    public PlayerTest(GraphicsComponent graphics, InputComponent input, PhysicsComponent physics) {
        super(graphics,input,physics);
        x = 0;
        y = 0;
    }

}
