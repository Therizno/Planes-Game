import javafx.scene.Node;
import javafx.scene.shape.Line;

public class Bullet extends Entity
{
    Line displayLine;

    public Bullet(double xStart, double yStart, double angle, double speed)
    {
        super(xStart, yStart, angle, speed, 0);
        displayLine = new Line();
    }

    public Node display(){
        displayLine.setStartX(xPos());
        displayLine.setStartY(yPos());
        displayLine.setEndX(xPos()+dX());
        displayLine.setEndY(yPos()+dY());

        return displayLine;
    }
}
