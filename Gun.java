
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
    
    private double damage;

    public Gun(double velocity, int frequency, String gunName, int ammoCapacity, double gunDamage)
    {
        gunVelocity = velocity;
        fireFrequency = frequency;
        name = gunName;

        timeLastFired = System.currentTimeMillis();
        xOffset = 0;
        yOffset = 0;
        
        maxAmmo = ammoCapacity;
        ammo = ammoCapacity;
        
        damage = gunDamage;
    }

    public Bullet fire(double x, double y, double direction){
        if(ammo > 0) {
            double currentTime = System.currentTimeMillis();
            double timeSinceLastFired = currentTime - timeLastFired;
            if(timeSinceLastFired >= fireFrequency){
                timeLastFired = currentTime;
                ammo--;
                return new Bullet(x, y, direction, gunVelocity, damage);
            }
        }
        return null;
    }

    public int getXOffset(){
        return xOffset;
    }
    public int getYOffset(){
        return yOffset;
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
    public void setMaxAmmo(int amount){
        maxAmmo = amount;
        if(maxAmmo < 0){
            maxAmmo = 0;
        }
    }
    
    public double getVelocity(){
        return gunVelocity;
    }
}
