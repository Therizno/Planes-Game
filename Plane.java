import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Group;

public class Plane extends Entity
{
    private ArrayList<Gun> guns;
    private Image planeImage;
    private ImageView img;
    
    //important stats
    private int maxHealth;
    private int health;
    private int money;

    public Plane(double xStart, double yStart, double speed, double turn, String imageName, int startHealth)
    {
        super(xStart, yStart, Math.PI/-2, speed, turn);
        planeImage = new Image(imageName);
        img = new ImageView(planeImage);
        guns = new ArrayList<Gun>();
        
        maxHealth = startHealth;
        health = startHealth;
        money = 0;
    }

    public Node display(){
        img.setRotate(Math.toDegrees(angle()));
        img.setX(xPos() - (planeImage.getWidth()/2));
        img.setY(yPos() - (planeImage.getHeight()/2));

        return img;
    }

    public void addGun(Gun gun){
        if(guns.size() < 2){        //max number of guns is currently 2
            guns.add(gun);
            if(guns.size() == 2){
                guns.get(0).setXOffset(10);
                guns.get(1).setXOffset(-10);
            }
        }
    }

    public ArrayList<Bullet> fireGuns(){
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();

        for(Gun g : guns){
            Bullet b = g.fire(xPos(), yPos(), angle());
            if(b != null){
                bullets.add(b);
            }
        }
        return bullets;
    }
    
    public int getHealth(){
        return health;
    }
    public void setHealth(int amount){
        health = amount;
        if(health < 0){
            health = 0;
        }
        else if(health > maxHealth){
            health = maxHealth;
        }
    }
    public void addHealth(int amount){
        health += amount;
        if(health < 0){
            health = 0;
        }
        else if(health > maxHealth){
            health = maxHealth;
        }
    }
    public void subHealth(int amount){
        health -= amount;
        if(health < 0){
            health = 0;
        }
        else if(health > maxHealth){
            health = maxHealth;
        }
    }
    
    public int getMaxHealth(){
        return maxHealth;
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
}
