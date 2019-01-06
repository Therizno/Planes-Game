
public class Gun
{
    private double gunVelocity;
    private double timeLastFired;
    private int fireFrequency;
    String name;
    
    private int xOffset;
    private int yOffset; 

    public Gun(double velocity, int frequency, String gunName)
    {
        gunVelocity = velocity;
        fireFrequency = frequency;
        name = gunName;

        timeLastFired = System.currentTimeMillis();
        xOffset = 0;
        yOffset = 0;
    }

    public Bullet fire(double x, double y, double direction){
        double currentTime = System.currentTimeMillis();
        double timeSinceLastFired = currentTime - timeLastFired;
        if(timeSinceLastFired >= fireFrequency){
            timeLastFired = currentTime;
            return new Bullet(x + xOffset, y + yOffset, direction, gunVelocity);
        }
        return null;
    }

    public void setXOffset(int x){
        xOffset = x;
    }
    public void setYOffset(int y){
        yOffset = y;
    }
}
