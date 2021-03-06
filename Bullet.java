import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.geometry.Point2D;

public class Bullet extends Entity
{
    Line displayLine;
    private double displayLength;
    private double damage;

    public Bullet(double xStart, double yStart, double angle, double speed, double damage)
    {
        super(xStart, yStart, angle, speed, 0);
        displayLine = new Line();
        displayLength = speed;
        this.damage = damage;
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
        displayLine.setEndX(xPos()+displayLength*Math.cos(angle()));
        displayLine.setEndY(yPos()+displayLength*Math.sin(angle()));

        return displayLine;
    }
    
    public boolean deAlloc(){
        return getDistanceTraveled() > 10000;
    }
    
    public void setDrawLength(double length){
        displayLength = length;
        if(displayLength < 0)
            displayLength = 0;
    }
    
    public double getDamage(){
        return damage;
    }
}
