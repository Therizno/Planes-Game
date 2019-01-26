import javafx.scene.Group;

public class Level3 extends Level
{
    Group root;
    
    public Level3(Plane player, Group rootNode, Difficulty d)
    {
        super(player, rootNode, d, "Level 3");
        root = rootNode;
        
        EnemyFactory fac = new EnemyFactory();
        
        for(int i = 0; i <= 3; i++){
            spawnEnemy(fac.easyPlane(i*(GameEngine.XWIDTH/3), -offScreen), 20);
        }
        
        addUpgrade(new Upgrade("+Health", 40){
            public void applyUpgrade(Plane player){
                player.setHealth(player.getMaxHealth()+20);
                
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
