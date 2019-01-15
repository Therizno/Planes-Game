import java.util.ArrayList;

import javafx.scene.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class Level implements GameState
{
    //entity management data structures
    private ArrayList<Entity> entityList;
    private ArrayList<Bullet> bulletList;
    private ArrayList<Entity> enemyList;
    
    protected Plane player;
    private Group root;
    private Hud playerHud;
    
    public boolean pauseGame;
    private boolean guiDisabled;
    
    
    public Level(Plane player, Group rootNode){
        entityList = new ArrayList<Entity>();
        bulletList = new ArrayList<Bullet>();
        enemyList = new ArrayList<Entity>();
        
        this.player = player;
        entityList.add(player);
        root = rootNode;
        root.getChildren().add(player.display());
        
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
        
        for(Entity e : entityList){
            e.display();
            e.update();
        }

        detectCollisions();
        
        player.display();
        player.update();
        
        playerHud.updateHealth(player.getHealth(), player.getMaxHealth());
        playerHud.updateAmmo(player.getAmmo(), player.getMaxAmmo());
        playerHud.updateMoney(player.getMoney());
        
        pauseGame = false;
    }
    
    private void detectCollisions(){
        //temporary, revise later
        for(int i = 0; i < enemyList.size(); i++){
            Entity enemy = enemyList.get(i);
            for(int j = 0; j < bulletList.size(); j++){
                Bullet b = bulletList.get(j);
                if(enemy.collide(b) || b.collide(enemy)){
                    bulletList.remove(b);
                    enemyList.remove(enemy);
                    entityList.remove(b);
                    entityList.remove(enemy);
                    root.getChildren().remove(b.display());
                    root.getChildren().remove(enemy.display());
                }
            }
        }
    }
    
    public void spawn(Entity object, boolean enemy){
        entityList.add(object);
        root.getChildren().add(object.display());
        if(enemy)
            enemyList.add(object);
    }
}
