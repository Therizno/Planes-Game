import java.util.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.canvas.*;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

public class GameEngine extends Application
{

    private int xWidth;
    private int yHeight;

    public static void main(String[] args){

        launch(args);

    }

    public void start(Stage stage){

        /*
        sets the stage
        */

        stage.setTitle("GAMETITLE");

        xWidth = 1200;
        yHeight = 600;

        Group rootNode = new Group();       //sets the root node, scene
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        Canvas canvas = new Canvas(xWidth, yHeight);    //creates canvas, graphicscontext
        rootNode.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        
        /*
        sets how user input is stored
        */

        ArrayList<String> userInput = new ArrayList<String>();

        scene.setOnKeyPressed(
        new EventHandler<KeyEvent>(){
            public void handle(KeyEvent event){
                String keyCode = event.getCode().toString();
                if(!userInput.contains(keyCode)){
                    userInput.add(keyCode);
                }
            }
        });


        scene.setOnKeyReleased(
        new EventHandler<KeyEvent>(){
            public void handle(KeyEvent event){
                String keyCode = event.getCode().toString();
                userInput.remove(keyCode);
            }
        });


        /*
        sets up the game objects
        */

        Entity player = new Plane(xWidth/2, yHeight - 50, 2, 1, "player_texture.png");
        rootNode.getChildren().add(player.display());

        /*
        sets the gameLoop, begins the gameLoop
        */

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
       
        final long startTime = System.currentTimeMillis();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.017),   //0.017 = 60fps
        new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){

                double time = System.currentTimeMillis() - startTime;
                
                /*
                game logic goes here
                */

                gc.clearRect(0, 0, xWidth, yHeight);

                if(userInput.contains("A")){
                    player.turnLeft();
                }
                if(userInput.contains("D")){
                    player.turnRight();
                }

                player.display();
                player.update();

            }
        });
        
        gameLoop.getKeyFrames().add(keyFrame);
        gameLoop.play();

        stage.show();
    }

}
