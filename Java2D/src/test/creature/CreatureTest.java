package test.creature;

import component.GameObject;
import component.GraphicsComponent;
import component.InputComponent;
import component.PhysicsComponent;

/**
 * Created by Alexandre Sanscartier on 11/05/2015.
 */
public class CreatureTest extends GameObject {

    public CreatureTest(GraphicsComponent graphics, InputComponent input, PhysicsComponent physics) {
        super(graphics,input,physics);
        x = 300;
        y = 300;
    }

}
