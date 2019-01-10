import javafx.scene.*;

public class TestLevel extends Level
{
    public TestLevel(Plane player, Group rootNode){
        super(player, rootNode);
        player.addGun(new Gun(10, 100, "m240"));
        rootNode.getChildren().add(player.display());
    }

    public void onKeyRelease(String keyCode){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}
}
