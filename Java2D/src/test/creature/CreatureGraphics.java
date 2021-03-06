package test.creature;

import component.GameObject;
import component.GraphicsComponent;

import java.awt.*;

/**
 * Created by Alexandre Sanscartier on 11/05/2015.
 */
public class CreatureGraphics implements GraphicsComponent {

    @Override
    public void update(GameObject object, Graphics g) {
        g.setColor(Color.red);
        g.drawRect(object.x - 10, object.y - 10, 20, 20);
        int radius = 100;
        g.setColor(Color.green);
        g.drawOval(object.x - 100, object.y - 100, radius * 2, radius * 2);
    }
}
