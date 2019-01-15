import javafx.scene.*;

public class TestLevel extends Level
{
    public TestLevel(Group rootNode){
        super(new Plane(GameEngine.XWIDTH/2, GameEngine.YHEIGHT - 50, 1, 2, "player_texture.png", 100), rootNode);
        player.addGun(new Gun(10, 100, "m240", 100));
        
        spawn(new Plane(100, 100, 0, 0, "player_texture.png", 100), true);
    }

    public void onKeyRelease(String keyCode){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}
}
