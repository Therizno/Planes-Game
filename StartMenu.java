import java.util.ArrayList;
import java.util.LinkedHashMap;

import javafx.scene.*;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class StartMenu implements GameState
{
    private GameState theState;
    private Group root;
    
    private ButtonFactory fac;
    private Button startButton;
    
    public StartMenu(Group rootNode)
    {
        root = rootNode;
        theState = this;
        fac = new ButtonFactory();
        
        int buttonW = 140;
        int buttonH = 70;
        
        startButton = fac.defaultButton("START", fac.centerX(buttonW), fac.centerY(buttonH), buttonW, buttonH);
        
        startButton.setOnMouseClicked(
        new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                difMenu();
            }
        });
        
        root.getChildren().add(startButton);
    }
    
    public void onKeyHold(ArrayList<String> input){}
    
    public void onKeyPress(String keyCode){
        if(keyCode.equals("ENTER"))
            difMenu();
    }
    
    public void onKeyRelease(String keyCode){}
    public void onMousePress(double mouseX, double mouseY){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}

    public GameState newState(){
        return theState;
    }
    
    public void updateAndDisplay(){}
    
    private void difMenu(){
        LinkedHashMap<String, EventHandler<MouseEvent>> actionMap = new LinkedHashMap<String, EventHandler<MouseEvent>>();
        
        actionMap.put("Easy", new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                root.getChildren().clear();
                theState = new Level1(root, new Difficulty(0.60));
            }
        });
        
        actionMap.put("Meduim", new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                root.getChildren().clear();
                theState = new Level1(root, new Difficulty(0.80));
            }
        });
        
        actionMap.put("Hard", new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                root.getChildren().clear();
                theState = new Level1(root, new Difficulty(1));
            }
        });
        
        root.getChildren().add(new ButtonFactory().buttonMenu("Difficulty", actionMap));
    }
}
