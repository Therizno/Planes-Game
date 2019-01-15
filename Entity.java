import javafx.scene.Node;
import javafx.geometry.Point2D;

public abstract class Entity
{
    private double x;
    private double y;
    private double theta;       //radians
    private double magnitude;

    private double turnSpeed;   //degrees

    public Entity(double xStart, double yStart, double angle, double speed, double turn){
        x = xStart;
        y = yStart;
        magnitude = speed;
        theta = angle;
        turnSpeed = turn;
    }

    public double xPos(){
        return x;
    }
    public double yPos(){
        return y;
    }

    public double angle(){
        return theta;
    }
    public void turnLeft(){
        theta -= Math.toRadians(turnSpeed);
    }
    public void turnRight(){
        theta += Math.toRadians(turnSpeed);
    }

    public double speed(){
        return magnitude;
    }

    public double turnSpeed(){
        return turnSpeed;
    }
    public void setTurnSpeed(double newSpeed){
        turnSpeed = newSpeed;
        if(turnSpeed < 0){
            turnSpeed = 0;
        }
    }

    public double dX(){
        return magnitude * Math.cos(theta);
    }
    public double dY(){
        return magnitude * Math.sin(theta);
    }

    public abstract boolean containsPoint(Point2D p);
    public abstract boolean collide(Entity e);
    
    public void update(){
        x += dX();
        y += dY();
    }

    public abstract Node display();
    
    //utility methods
    public double relativeX(double magnitude){
        return xPos() + magnitude * Math.cos(angle());
    }
    public double relativeY(double magnitude){
        return yPos() + magnitude * Math.sin(angle());
    }
}
