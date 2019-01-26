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
import javafx.scene.input.MouseEvent;

public class GameEngine extends Application
{
    private GameState currentState;

    static final int XWIDTH = 1200;
    static final int YHEIGHT = 600;

    public static void main(String[] args){

        launch(args);

    }

    public void start(Stage stage){

        /*
        sets the stage
        */

        stage.setTitle("FALLING SKIES");

        Group rootNode = new Group();       //sets the root node, scene
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);

        Canvas canvas = new Canvas(XWIDTH, YHEIGHT);    //creates canvas, graphicscontext
        rootNode.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        
        /*
        creates state and input objects
        */

        ArrayList<String> userInput = new ArrayList<String>();
        currentState = new StartMenu(rootNode);
        MouseInput mouse = new MouseInput();
        
        
        /*
        sets how global user input is stored
        */

        scene.setOnKeyPressed(
        new EventHandler<KeyEvent>(){
            public void handle(KeyEvent event){
                String keyCode = event.getCode().toString();
                if(!userInput.contains(keyCode)){
                    userInput.add(keyCode);
                }
                
                currentState.onKeyPress(keyCode);
            }
        });


        scene.setOnKeyReleased(
        new EventHandler<KeyEvent>(){
            public void handle(KeyEvent event){
                String keyCode = event.getCode().toString();
                userInput.remove(keyCode);
                
                currentState.onKeyRelease(keyCode);
            }
        });

        scene.setOnMousePressed(
        new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                mouse.mouseX = event.getScreenX();
                mouse.mouseY = event.getScreenY();
                mouse.mousePressed = true;
            }
        });

        scene.setOnMouseReleased(
        new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                mouse.mouseX = event.getScreenX();
                mouse.mouseY = event.getScreenY();
                currentState.onMouseRelease(mouse.mouseX, mouse.mouseY);
                mouse.mousePressed = false;
            }
        });
        
        scene.setOnMouseClicked(
        new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                currentState.onMouseClick(event.getScreenX(), event.getScreenY());
            }
        });


        /*
        creates the gameLoop, begins the gameLoop
        */

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
       
        final long startTime = System.currentTimeMillis();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.017),   //0.017 = 60fps
        new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){

                double time = System.currentTimeMillis() - startTime;
                
                gc.clearRect(0, 0, XWIDTH, YHEIGHT);
                
                if(mouse.mousePressed){
                    currentState.onMousePress(mouse.mouseX, mouse.mouseY);
                }

                currentState = currentState.newState();
                
                currentState.onKeyHold(userInput);
                currentState.updateAndDisplay();

            }
        });
        
        gameLoop.getKeyFrames().add(keyFrame);
        gameLoop.play();

        stage.show();
    }

}
