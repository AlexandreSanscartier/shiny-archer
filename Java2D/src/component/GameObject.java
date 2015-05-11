package component;


/**
 * Created by Alexandre Sanscartier on 11/05/2015.
 */
public class GameObject {

    public int x, y;

    private GraphicsComponent graphics;
    private InputComponent input;
    private PhysicsComponent physics;

    public GameObject(GraphicsComponent graphics, InputComponent input, PhysicsComponent physics) {
        this.graphics = graphics;
        this.input = input;
        this.physics = physics;
    }

}
