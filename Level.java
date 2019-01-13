import java.util.ArrayList;

import javafx.scene.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class Level implements GameState
{
    private ArrayList<Bullet> bulletList;
    private Plane player;
    private Group root;
    private Hud playerHud;
    
    public boolean pauseGame;
    private boolean guiDisabled;
    
    
    public Level(Plane player, Group rootNode){
        bulletList = new ArrayList<Bullet>();
        
        this.player = player;
        root = rootNode;
        
        //set up hud
        playerHud = new Hud(this);
        root.getChildren().add(playerHud.hud());
        
        pauseGame = false;
        guiDisabled = false;
    }
    
    public void onKeyHold(ArrayList<String> input){
        if(input.contains("A")){
            player.turnLeft();
        }
        if(input.contains("D")){
            player.turnRight();
        }
    }
    
    public void onKeyPress(String keyCode){
        if(keyCode.equals("ESCAPE")){
            pauseGame = true;
        }
    }

    public void onMousePress(double mouseX, double mouseY){
        for(Bullet b : player.fireGuns()){
            bulletList.add(b);
            root.getChildren().add(b.display());
        }
    }
    
    public GameState newState(){
        if(pauseGame){
            return new PauseState(this, root);
        }
        return this;
    }
    
    public void updateAndDisplay(){
        
        for(Bullet b : bulletList){
            b.display();
            b.update();
        }

        player.display();
        player.update();
        
        playerHud.updateHealth(player.getHealth(), player.getMaxHealth());
        playerHud.updateAmmo(player.getAmmo(), player.getMaxAmmo());
        playerHud.updateMoney(player.getMoney());
        
        pauseGame = false;
    }
    
}
