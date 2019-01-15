
public class Gun
{
    private double gunVelocity;
    private double timeLastFired;
    private int fireFrequency;
    String name;
    
    private int xOffset;
    private int yOffset; 
    
    private int maxAmmo;
    private int ammo;

    public Gun(double velocity, int frequency, String gunName, int ammoCapacity)
    {
        gunVelocity = velocity;
        fireFrequency = frequency;
        name = gunName;

        timeLastFired = System.currentTimeMillis();
        xOffset = 0;
        yOffset = 0;
        
        maxAmmo = ammoCapacity;
        ammo = ammoCapacity;
    }

    public Bullet fire(double x, double y, double direction){
        if(ammo > 0) {
            double currentTime = System.currentTimeMillis();
            double timeSinceLastFired = currentTime - timeLastFired;
            if(timeSinceLastFired >= fireFrequency){
                timeLastFired = currentTime;
                ammo--;
                return new Bullet(x + xOffset, y + yOffset, direction, gunVelocity);
            }
        }
        return null;
    }

    public void setXOffset(int x){
        xOffset = x;
    }
    public void setYOffset(int y){
        yOffset = y;
    }
    
    public int getAmmo(){
        return ammo;
    }
    public void setAmmo(int amount){
        ammo = amount;
        if(ammo < 0){
            ammo = 0;
        }
        if(ammo > maxAmmo){
            ammo = maxAmmo;
        }
    }
    public void addAmmo(int amount){
        ammo += amount;
        if(ammo < 0){
            ammo = 0;
        }
        if(ammo > maxAmmo){
            ammo = maxAmmo;
        }
    }
    
    public int getMaxAmmo(){
        return maxAmmo;
    }
    
    public double getVelocity(){
        return gunVelocity;
    }
}
