import java.util.ArrayList;

public abstract class CombatEntity extends Entity
{
    private ArrayList<Gun> guns;
    private ArrayList<ParticleEmitter> particleSources;
    
    private double maxHealth;
    private double health;
    private int money;
    
    //the cash reward for the player destroying this entity
    private int cashReward;
    
    public CombatEntity(double xStart, double yStart, double angle, double speed, double turn, double startHealth){
        super(xStart, yStart, angle, speed, turn);
        
        guns = new ArrayList<Gun>();
        particleSources = new ArrayList<ParticleEmitter>();
        
        maxHealth = startHealth;
        health = startHealth;
        money = 0;
        cashReward = 0;
    }
    
    /*
     * the game engine takes care of deAllocating CombatEntities independently in order to 
     * apply death effects, money reward, etc.
     */
    public boolean deAlloc(){
        return false;
    }
    
    //makes a shallow copy
    public ArrayList<Gun> getGuns(){
        ArrayList<Gun> gl = new ArrayList<Gun>();
        for(Gun g : guns){
            gl.add(g);
        }
        return gl;
    }
    public void addGun(Gun gun, int xOffset, int yOffset){
        guns.add(gun);
        gun.setXOffset(xOffset);
        gun.setYOffset(yOffset);
    }
    public abstract void addGun(Gun g);
    public int gunSize(){
        return guns.size();
    }
    public void clearGuns(){
        guns.clear();
    }
    public ArrayList<Bullet> fireGuns(){
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();

        for(Gun g : guns){
            Bullet b = g.fire(relativeX(g.getXOffset(), g.getYOffset()), relativeY(g.getXOffset(), g.getYOffset()), angle());
            if(b != null){
                bullets.add(b);
            }
        }
        return bullets;
    }
    
    //makes a shallow copy
    public ArrayList<ParticleEmitter> getParticleEmitters(){
        ArrayList<ParticleEmitter> emitterList = new ArrayList<ParticleEmitter>();
        for(ParticleEmitter p : particleSources){
            emitterList.add(p);
        }
        return emitterList;
    }
    public void addParticleEmitter(ParticleEmitter p, int xOffset, int yOffset){
        particleSources.add(p);
        p.setXOffset(xOffset);
        p.setYOffset(yOffset);
        p.setX(relativeX(xOffset, yOffset));
        p.setY(relativeY(xOffset, yOffset));
    }
    public boolean removeParticleEmitter(ParticleEmitter p){
        return particleSources.remove(p);
    }
    public int emitterSize(){
        return particleSources.size();
    }
    public void clearParticleEmitters(){
        particleSources.clear();
    }
    
    public int getAmmo(){
        int totalAmmo = 0;
        for(Gun g : guns){
            totalAmmo += g.getAmmo();
        }
        return totalAmmo;
    }
    
    public int getMaxAmmo(){
        int totalAmmo = 0;
        for(Gun g : guns){
            totalAmmo += g.getMaxAmmo();
        }
        return totalAmmo;
    }
    
    public double getMaxHealth(){
        return maxHealth;
    }
    public void setMaxHealth(double amount){
        maxHealth = amount;
        if(maxHealth < 0)
            maxHealth = 0;
    }
    
    public double getHealth(){
        return health;
    }
    public void setHealth(double amount){
        health = amount;
        if(health < 0){
            health = 0;
        }
        else if(health > maxHealth){
            health = maxHealth;
        }
    }
    public void addHealth(double amount){
        health += amount;
        if(health < 0){
            health = 0;
        }
        else if(health > maxHealth){
            health = maxHealth;
        }
    }
    public void subHealth(double amount){
        health -= amount;
        if(health < 0){
            health = 0;
        }
        else if(health > maxHealth){
            health = maxHealth;
        }
    }
    
    public int getMoney(){
        return money;
    }
    public void setMoney(int amount){
        money = amount;
        if(money < 0)
            money = 0;
    }
    public void addMoney(int amount){
        money += amount;
        if(money < 0)
            money = 0;
    }
    public void subMoney(int amount){
        money -= amount;
        if(money < 0)
            money = 0;
    }
    
    public void setReward(int reward){
        cashReward = reward;
    }
    public int getReward(){
        return cashReward;
    }
}
