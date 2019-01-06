import java.util.ArrayList;
import javafx.scene.*;

public abstract class Level implements GameState
{
    private ArrayList<Bullet> bulletList;
    private Plane player;
    private Group root;
    
    private boolean pauseGame;
    
    public Level(Plane player, Group rootNode){
        bulletList = new ArrayList<Bullet>();
        this.player = player;
        root = rootNode;
        
        pauseGame = false;
    }
    
    public void handleKeyboardInput(ArrayList<String> input){
        if(input.contains("A")){
            player.turnLeft();
        }
        if(input.contains("D")){
            player.turnRight();
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
            return null;
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
    }
}
