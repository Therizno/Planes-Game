import java.util.ArrayList;
import java.util.LinkedHashMap;

import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PauseState implements GameState
{
    private Group root;
    private Group pauseMenu;
    private GameState theState;
    private GameState previousState;
    ButtonFactory fac;
    
    //fix this constructor! no HashMap!
    public PauseState(GameState previousState, Group rootNode)
    {
        theState = this;
        root = rootNode;
        this.previousState = previousState;
        fac = new ButtonFactory();
        
        //to add new buttons to the menu, add new pairs to the hashmap
        LinkedHashMap<String, EventHandler<MouseEvent>> buttonMap = new LinkedHashMap<String, EventHandler<MouseEvent>>();
        
        buttonMap.put("Resume", new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                root.getChildren().remove(pauseMenu);
                theState = previousState;
            }
        });
        
        
        buttonMap.put("Quit", new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                root.getChildren().clear();
                theState = new StartMenu(root);
            }
        });
        
        pauseMenu = fac.buttonMenu("Paused", buttonMap);
        root.getChildren().add(pauseMenu);
    }
    
    public void onKeyHold(ArrayList<String> input){}
    
    public void onKeyPress(String keyCode){
        if(keyCode.equals("ESCAPE")){
            root.getChildren().remove(pauseMenu);
            theState = previousState;
        }
    }
    
    public void onKeyRelease(String keyCode){}
    public void onMousePress(double mouseX, double mouseY){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}
    
    public GameState newState(){
        return theState;
    }
    
    public void updateAndDisplay(){}
}
