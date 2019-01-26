import javafx.scene.Group;

public class Level3 extends Level
{
    Group root;
    
    public Level3(Plane player, Group rootNode, Difficulty d)
    {
        super(player, rootNode, d, "Level 3");
        root = rootNode;
        
        EnemyFactory enemyFac = new EnemyFactory();
        GunFactory gunFac = new GunFactory();
        
        for(int i = 0; i <= 3; i++){
            spawnEnemy(enemyFac.easyPlane(i*(GameEngine.XWIDTH/3), -offScreen));
        }
        
        addUpgrade(new HealthUpgrade(40, 20));
        
        addUpgrade(new Upgrade("M240", 80){
            public void applyUpgrade(Plane player){
                player.clearGuns();
                
                player.addGun(gunFac.m240());
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
