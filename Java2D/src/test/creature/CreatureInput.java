package test.creature;

import component.GameObject;
import component.InputComponent;
import input.CanvasKeyboard;

import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by Alexandre Sanscartier on 11/05/2015.
 */
public class CreatureInput implements InputComponent {

    public CreatureInput() {}
    @Override
    public void update(GameObject object) {

        int dir = new Random().nextInt(4);

        if(dir == 0) {
            object.y -= 2;
        }
        if(dir == 1) {
            object.y += 2;
        }
        if(dir == 2) {
            object.x -= 2;
        }
        if(dir == 3) {
            object.x += 2;
        }
    }

}
