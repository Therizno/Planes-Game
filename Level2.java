import javafx.scene.Group;

public class Level2 extends Level
{
    Group root;
    
    public Level2(Plane player, Group rootNode, Difficulty d)
    {
        super(player, rootNode, d, "Level 2");
        
        root = rootNode;
        
        EnemyFactory fac = new EnemyFactory();
        
        spawnEnemy(fac.easyPlane(GameEngine.XWIDTH/2, -offScreen), 20);
        spawnEnemy(fac.easyPlane(-offScreen, GameEngine.YHEIGHT/2), 20);
        spawnEnemy(fac.easyPlane(GameEngine.XWIDTH+offScreen, GameEngine.YHEIGHT/2), 20);
        
        addUpgrade(new Upgrade("+Ammo", 20){
            public void applyUpgrade(Plane player){
                for(Gun g : player.getGuns()){
                    g.setMaxAmmo(g.getMaxAmmo()+30);
                    g.setAmmo(g.getMaxAmmo());
                }

                player.subMoney(getCost());
            }
        });
        
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
        return new StartMenu(root);
    }
}
