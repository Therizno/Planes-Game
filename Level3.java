import javafx.scene.Group;

public class Level3 extends Level
{
    Group root;
    Difficulty dif;
    
    public Level3(Plane player, Group rootNode, Difficulty d)
    {
        super(player, rootNode, d, "Level 3");
        root = rootNode;
        dif = d;
        
        EnemyFactory fac = new EnemyFactory();
        
        for(int i = 0; i <= 3; i++){
            spawnEnemy(fac.easyPlane(i*(GameEngine.XWIDTH/3), -offScreen));
        }
        
        addUpgrade(new HealthUpgrade(40, 20));
        
        addUpgrade(new M240Upgrade(80));
    }
    
    public void onKeyRelease(String keyCode){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}
    
    public GameState nextLevel(){
        return new Level4(player, root, dif);
    }
}
