import java.util.ArrayList;

import javafx.scene.*;

public class Level1 extends Level
{
    Group root;
    Difficulty dif;
    
    GunFactory gunFac;
    EnemyFactory enemyFac;
    
    public Level1(Group rootNode, Difficulty d){
        super(new Plane(GameEngine.XWIDTH/2, GameEngine.YHEIGHT - 50, 4, 3, "player_texture.png", 100), rootNode, d, "Level 1");
        
        gunFac = new GunFactory();
        enemyFac = new EnemyFactory();
        
        root = rootNode;
        dif = d;
        
        
        player.addGun(gunFac.m249());
        
        spawnEnemy(enemyFac.easyPlane(GameEngine.XWIDTH/2, -offScreen));
        
        addUpgrade(new HealthUpgrade(20, 30));
    }

    public void onKeyRelease(String keyCode){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}
    
    public GameState nextLevel(){
        player.setHealth(player.getMaxHealth());
        return new Level2(player, root, dif);
    }
}
