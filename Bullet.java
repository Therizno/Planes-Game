import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.geometry.Point2D;

public class Bullet extends Entity
{
    Line displayLine;

    public Bullet(double xStart, double yStart, double angle, double speed)
    {
        super(xStart, yStart, angle, speed, 0);
        displayLine = new Line();
    }

    public boolean containsPoint(Point2D p){
        return false;
    }
    public boolean collide(Entity e){
        return e.containsPoint(new Point2D(xPos(), yPos())) || e.containsPoint(new Point2D(xPos()+dX(), yPos()+dY()));
    }
    
    public Node display(){
        displayLine.setStartX(xPos());
        displayLine.setStartY(yPos());
        displayLine.setEndX(xPos()+dX());
        displayLine.setEndY(yPos()+dY());

        return displayLine;
    }
}
