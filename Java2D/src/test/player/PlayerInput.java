package test.player;

import component.GameObject;
import component.InputComponent;
import input.CanvasKeyboard;

import java.awt.event.KeyEvent;

/**
 * Created by Alexandre Sanscartier on 11/05/2015.
 */
public class PlayerInput implements InputComponent {

    CanvasKeyboard keyboard;

    public PlayerInput(CanvasKeyboard keyboard) {
        this.keyboard = keyboard;
    }
    @Override
    public void update(GameObject object) {

        if(keyboard.keyDown(KeyEvent.VK_UP)) {
            object.y -= 2;
        }
        if(keyboard.keyDown(KeyEvent.VK_DOWN)) {
            object.y += 2;
        }
        if(keyboard.keyDown(KeyEvent.VK_LEFT)) {
            object.x -= 2;
        }
        if(keyboard.keyDown(KeyEvent.VK_RIGHT)) {
            object.x += 2;
        }
    }

}
