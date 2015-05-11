package test.player;

import component.GameObject;
import component.GraphicsComponent;

import java.awt.*;

/**
 * Created by Alexandre Sanscartier on 11/05/2015.
 */
public class PlayerGraphics implements GraphicsComponent {

    @Override
    public void update(GameObject object, Graphics g) {
        g.setColor(Color.red);
        g.drawRect(object.x - 10, object.y - 10, 20, 20);
    }
}
