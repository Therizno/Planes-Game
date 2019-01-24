import java.util.ArrayList;
import java.util.LinkedHashMap;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class DeathScreen implements GameState
{
    GameState theState;
    Group root;
    
    double startTime;
    boolean displayMenu;
    
    ButtonFactory fac;
    
    Button deathMessage;
    
    public DeathScreen(Group rootNode){
        theState = this;
        root = rootNode;
        
        startTime = System.currentTimeMillis();
        displayMenu = false;
        
        fac = new ButtonFactory();
        
        deathMessage = fac.messageToUser("You Died!", "user-message-red");
        root.getChildren().add(deathMessage);
    }
    
    public void onKeyHold(ArrayList<String> input){}
    public void onKeyPress(String keyCode){}
    public void onKeyRelease(String keyCode){}
    public void onMousePress(double mouseX, double mouseY){}
    public void onMouseRelease(double mouseX, double mouseY){}
    public void onMouseClick(double mouseX, double mouseY){}
    
    public GameState newState(){
        return theState;
    }
    
    public void updateAndDisplay(){
        double time = System.currentTimeMillis() - startTime;
        
        double fadeTime = 3000;
        
        if(time > fadeTime && !displayMenu){
            displayMenu = true;
            
            LinkedHashMap<String, EventHandler<MouseEvent>> actionMap = new LinkedHashMap<String, EventHandler<MouseEvent>>();
            actionMap.put("Restart", new EventHandler<MouseEvent>(){
                public void handle(MouseEvent event){
                    root.getChildren().clear();
                    theState = new Level1(root);
                }
            });
            
            actionMap.put("Quit", new EventHandler<MouseEvent>(){
                public void handle(MouseEvent event){
                    root.getChildren().clear();
                    theState = new StartMenu(root);
                }
            });
            
            root.getChildren().add(fac.buttonMenu("Game Over", actionMap));
        }
    }
}
