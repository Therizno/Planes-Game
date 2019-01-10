import java.util.ArrayList;

import javafx.scene.*;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class StartMenu implements GameState
{
    private int buttonW;
    private int buttonH;
    private boolean started;
    private Group root;
    
    private ButtonFactory fac;
    private Button startButton;
    
    public StartMenu(Group rootNode)
    {
        root = rootNode;
        fac = new ButtonFactory();
        
        buttonW = 140;
        buttonH = 70;
        
        startButton = fac.defaultButton("START", fac.centerX(buttonW), fac.centerY(buttonH), buttonW, buttonH);
        
        startButton.setOnMouseClicked(
        new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                started = true;
            }
        });
        
        root.getChildren().add(startButton);
    }
    
    public void onKeyHold(ArrayList<String> input){}
    
    public void onKeyPress(String keyCode){
        if(keyCode.equals("ENTER"))
            started = true;
    }
    
    public void onKeyRelease(String keyCode){}
    public void onMousePress(double mouseX, double mouseY){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}

    public GameState newState(){
        if(started){
            root.getChildren().clear();
            return new TestLevel(new Plane(GameEngine.XWIDTH/2, GameEngine.YHEIGHT - 50, 2, 1, "player_texture.png"), root);
        }
        
        return this;
    }
    
    public void updateAndDisplay(){}
}
