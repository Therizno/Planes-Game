import java.util.ArrayList;

import javafx.scene.*;

public class Level1 extends Level
{
    Group root;
    
    GunFactory gunFac;
    EnemyFactory enemyFac;
    
    public Level1(Group rootNode){
        super(new Plane(GameEngine.XWIDTH/2, GameEngine.YHEIGHT - 50, 4, 3, "player_texture.png", 100), rootNode);
        gunFac = new GunFactory();
        enemyFac = new EnemyFactory();
        
        root = rootNode;
        
        
        player.addGun(gunFac.m249());
        
        int offScreen = 100;
        
        spawnEnemy(enemyFac.easyPlane(GameEngine.XWIDTH/2, -offScreen), 20);
        
        addUpgrade(new Upgrade("+Health", 20){
            public void applyUpgrade(Plane player){
                player.setMaxHealth(player.getMaxHealth()+30);
                player.setHealth(player.getMaxHealth());
                
                player.subMoney(getCost());
            }
        });
        
        addUpgrade(new Upgrade("+Ammo", 20){
            public void applyUpgrade(Plane player){
                for(Gun g : player.getGuns()){
                    g.setMaxAmmo(g.getMaxAmmo()+30);
                    g.setAmmo(g.getMaxAmmo());
                }

                player.subMoney(getCost());
            }
        });
    }

    public void onKeyRelease(String keyCode){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}
    
    @Override
    public GameState newState(){
        if(player.getHealth() > 0)
            return super.newState();
        return new DeathScreen(root);
    }
}
