import java.util.ArrayList;

import javafx.scene.*;

public class TestLevel extends Level
{
    public TestLevel(Group rootNode){
        super(new Plane(GameEngine.XWIDTH/2, GameEngine.YHEIGHT - 50, 4, 4, "player_texture.png", 100), rootNode);
        player.addGun(new Gun(10, 100, "m240", 100, 5));
        player.addGun(new Gun(10, 100, "m240", 100, 5));
        
        ArrayList<Gun> guns1 = new ArrayList<Gun>();
        ArrayList<Gun> guns2 = new ArrayList<Gun>();
        guns1.add(new Gun(10, 100, "m240", 1000, 3));
        guns2.add(new Gun(10, 100, "m240", 1000, 3));
        //spawnEnemy(new Plane(100, GameEngine.YHEIGHT - 100, 2, 3, "player_texture.png", 100), guns1, 20);
        spawnEnemy(new Plane(200, GameEngine.YHEIGHT - 100, 2, 3, "player_texture.png", 100), guns2, 20);
        
        addUpgrade(new Upgrade("+Health", 20){
            public void applyUpgrade(Plane player){
                player.setMaxHealth(player.getMaxHealth()+50);
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
}
