import javafx.scene.Group;

public class Level4 extends Level
{
    Group root;
    
    public Level4(Plane player, Group rootNode, Difficulty d)
    {
        super(player, rootNode, d, "Level 4");
        
        spawnEnemy(new EnemyFactory().mediumPlane(GameEngine.XWIDTH/2, -offScreen));
        
        root = rootNode;
    }
    
    public void onKeyRelease(String keyCode){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}
    
    public GameState nextLevel(){
        return new StartMenu(root);
    }
}
