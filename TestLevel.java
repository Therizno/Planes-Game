import javafx.scene.*;

public class TestLevel extends Level
{
    public TestLevel(Group rootNode){
        super(new Plane(GameEngine.XWIDTH/2, GameEngine.YHEIGHT - 50, 3, 3, "player_texture.png", 100), rootNode);
        player.addGun(new Gun(10, 100, "m240", 100, 5));
        player.addGun(new Gun(10, 100, "m240", 100, 5));
        
        spawnEnemy(new Plane(100, GameEngine.YHEIGHT - 100, 1, 0, "player_texture.png", 100), 20);
        spawnEnemy(new Plane(200, GameEngine.YHEIGHT - 100, 1, 0, "player_texture.png", 100), 20);
    }

    public void onKeyRelease(String keyCode){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}
}
