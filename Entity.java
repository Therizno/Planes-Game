import javafx.scene.Node;

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
        assert newSpeed >= 0;
        turnSpeed = newSpeed;
    }

    public double dX(){
        return magnitude * Math.cos(theta);
    }
    public double dY(){
        return magnitude * Math.sin(theta);
    }

    public void update(){
        x += dX();
        y += dY();
    }

    public abstract Node display();
}
