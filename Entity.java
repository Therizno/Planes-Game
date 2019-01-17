import javafx.scene.Node;
import javafx.geometry.Point2D;

public abstract class Entity
{
    private double x;
    private double y;
    private double theta;       //radians
    private double magnitude;
    private double distanceTraveled;

    private double turnSpeed;   //degrees

    public Entity(double xStart, double yStart, double angle, double speed, double turn){
        x = xStart;
        y = yStart;
        magnitude = speed;
        theta = angle;
        turnSpeed = turn;
        distanceTraveled = 0;
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
    
    public double getDistanceTraveled(){
        return distanceTraveled;
    }
    
    public void teleport(double newX, double newY){
        x = newX;
        y = newY;
    }

    public abstract boolean containsPoint(Point2D p);
    public abstract boolean collide(Entity e);
    
    public void update(){
        x += dX();
        y += dY();
        distanceTraveled += magnitude;
    }

    public abstract Node display();
    
    //tells the gameState when to destroy this object
    public abstract boolean deAlloc();
    
    //utility methods
    public double relativeX(double xOffset, double yOffset){
        return x + xOffset*Math.cos(theta+Math.PI/2) - yOffset*Math.cos(theta);
    }
    public double relativeY(double xOffset, double yOffset){
        return y + xOffset*Math.sin(theta+Math.PI/2) - yOffset*Math.sin(theta);
    }
}
