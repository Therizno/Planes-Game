import javafx.scene.Group;

public class Level2 extends Level
{
    Group root;
    Difficulty dif;
    
    public Level2(Plane player, Group rootNode, Difficulty d)
    {
        super(player, rootNode, d, "Level 2");
        
        root = rootNode;
        dif = d;
        
        EnemyFactory fac = new EnemyFactory();
        
        spawnEnemy(fac.easyPlane(GameEngine.XWIDTH/2, -offScreen));
        spawnEnemy(fac.easyPlane(-offScreen, GameEngine.YHEIGHT/2));
        spawnEnemy(fac.easyPlane(GameEngine.XWIDTH+offScreen, GameEngine.YHEIGHT/2));
        
        addUpgrade(new AmmoUpgrade(20, 50));
        
        addUpgrade(new Upgrade("dual M249", 40){
            public void applyUpgrade(Plane player){
                GunFactory fac = new GunFactory();
                player.clearGuns();
                player.addGun(fac.m249());
                player.addGun(fac.m249());
                
                player.subMoney(getCost());
            }
        });
    }
    
    public void onKeyRelease(String keyCode){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}
    
    public GameState nextLevel(){
        return new Level3(player, root, dif);
    }
}
