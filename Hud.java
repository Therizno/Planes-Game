import java.util.ArrayList;

import javafx.scene.*;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Hud
{
    Level level;
    
    Button pause;
    Group hud;
    
    Group upgradeGroup;
    
    int playerHealth, playerMoney, playerAmmo;
    Label healthLabel, moneyLabel, ammoLabel;
    int labelXMargin;    //size of margin between the above labels and the edge of the window
    int labelYMargin;
    int labelYGap;
    
    ArrayList<Label> labelList;
    
    public Hud(Level theLevel)
    {
        level = theLevel;
        playerHealth = -1;
        playerMoney = -1;
        playerAmmo = -1;
        
        //change the order that these items are added in to change the y axis order
        //to add a new label, simply add it to the list
        labelList = new ArrayList<Label>();
        labelList.add(moneyLabel);
        labelList.add(ammoLabel);
        labelList.add(healthLabel);
        labelXMargin = 15;
        labelYMargin = 40;
        labelYGap = 35;
    }

    public Group hud(){
        hud = new Group();
        ButtonFactory fac = new ButtonFactory();
        
        
        /*
         * pause button
         */
        
        Image pauseImage = new Image("pause_graphic.png");
        ImageView pauseImgV = new ImageView(pauseImage);
        
        int buttonW = (int)pauseImage.getWidth() + 13;
        int buttonH = (int)pauseImage.getHeight() + 10;
        pause = fac.defaultButton("", GameEngine.XWIDTH - buttonW, 0, buttonW, buttonH);
        
        pause.setGraphic(pauseImgV);
        
        pause.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                level.pauseGame = true;
            }
        });
                
        hud.getChildren().add(pause);
        
        
        /*
         * character status
         */
        
        int xSize = 200;
        int ySize = 140;
        
        Rectangle statsBox = fac.guiRect(-20, GameEngine.YHEIGHT - ySize + 20, xSize, ySize);
        hud.getChildren().add(statsBox);
        
        updateUpgrades();
        
        return hud;
    }
    
    public void updateUpgrades(){
        if(level.getUpgrades().size() > 0){
            hud.getChildren().remove(upgradeGroup);
            upgradeGroup = new Group();
            
            ButtonFactory fac = new ButtonFactory();
            

            int xMargin = 10;
            int yMargin = 10;
            
            int buttonXSize = 100;
            int buttonYSize = 50;
            int numUpgrades = level.getUpgrades().size();
        
            int buttonX = GameEngine.XWIDTH-(numUpgrades*buttonXSize);
        
            Rectangle displayBox = fac.guiRect(buttonX-xMargin, GameEngine.YHEIGHT-buttonYSize-yMargin, (numUpgrades*buttonXSize)+xMargin+100, buttonYSize+yMargin+100);
            upgradeGroup.getChildren().add(displayBox);
        
            
            int titleRectXMargin = 8;
            int titleRectYMargin = 8;
            
            int titleBoxXSize = buttonXSize-titleRectXMargin;
            int titleBoxYSize = 40;
            
            Rectangle titleRect = fac.guiRect(GameEngine.XWIDTH-titleBoxXSize-titleRectXMargin, GameEngine.YHEIGHT-titleBoxYSize-buttonYSize-titleRectYMargin, titleBoxXSize+titleRectXMargin+100, buttonYSize+titleBoxYSize+titleRectYMargin+100);
            upgradeGroup.getChildren().add(titleRect);
            
            Button title = fac.titleBox("Upgrades", GameEngine.XWIDTH-titleBoxXSize, GameEngine.YHEIGHT-titleBoxYSize-buttonYSize, titleBoxXSize, titleBoxYSize);
            upgradeGroup.getChildren().add(title);
        
            for (Upgrade g : level.getUpgrades()){
                Button b = fac.defaultButton(g.getName(), buttonX, GameEngine.YHEIGHT-buttonYSize, buttonXSize, buttonYSize);
                b.getStyleClass().add("upgrade-button");
                
                b.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    public void handle(MouseEvent event){
                        if(level.player.getMoney() >= g.getCost()){
                            g.applyUpgrade(level.player);
                        }
                    }
                });
                
                upgradeGroup.getChildren().add(b);
                buttonX += buttonXSize;
            }   
            
            hud.getChildren().add(upgradeGroup);
        }
    }
    
    public void updateHealth(int health, int maxHealth){
        if(playerHealth != health){
            int labelPosition = labelList.indexOf(healthLabel);
            hud.getChildren().remove(healthLabel);
            
            healthLabel = new Label("Health: " + health);
            healthLabel.setLayoutX(labelXMargin);
            healthLabel.setLayoutY(GameEngine.YHEIGHT - (labelYGap*labelPosition+labelYMargin));
            
            if(maxHealth == 0)      //prevent div by 0
                maxHealth = 1;
            
            float percHealth = health/maxHealth;
            if(percHealth > 0.66){
                healthLabel.getStyleClass().add("label-green");    //green
            }
            else if(percHealth <= 0.66 && percHealth > 0.33){
                healthLabel.getStyleClass().add("label-yellow");   //yellow
            }
            else{
                healthLabel.getStyleClass().add("label-red");   //red
            }
                
            playerHealth = health;
            labelList.set(labelPosition, healthLabel);
            hud.getChildren().add(healthLabel);
        }
    }
    public void updateMoney(int money){
        if(playerMoney != money){
            int labelPosition = labelList.indexOf(moneyLabel);
            hud.getChildren().remove(moneyLabel);
            
            moneyLabel = new Label("Money: $" + money);
            moneyLabel.setLayoutX(labelXMargin);
            moneyLabel.setLayoutY(GameEngine.YHEIGHT - (labelYGap*labelPosition+labelYMargin));
            moneyLabel.getStyleClass().add("label-green");
            
            playerMoney = money;
            labelList.set(labelPosition, moneyLabel);
            hud.getChildren().add(moneyLabel);
        }
    }
    public void updateAmmo(int ammo, int maxAmmo){
        if(playerAmmo != ammo){
            int labelPosition = labelList.indexOf(ammoLabel);
            hud.getChildren().remove(ammoLabel);
            
            ammoLabel = new Label("Ammo: " + ammo);
            ammoLabel.setLayoutX(labelXMargin);
            ammoLabel.setLayoutY(GameEngine.YHEIGHT - (labelYGap*labelPosition+labelYMargin));
            
            if(maxAmmo == 0)      //prevent div by 0
                maxAmmo = 1;
            
            double percAmmo = (double)ammo/(double)maxAmmo;
            if(percAmmo > 0.66){
                ammoLabel.getStyleClass().add("label-green");
            }
            else if(percAmmo <= 0.66 && percAmmo > 0.33){
                ammoLabel.getStyleClass().add("label-yellow");
            }
            else{
                ammoLabel.getStyleClass().add("label-red");   
            }
            
            playerAmmo = ammo;
            labelList.set(labelPosition, ammoLabel);
            hud.getChildren().add(ammoLabel);
        }
    }
    
    //fill these in later
    public void disableHud(){}
    public void enableHud(){}
}
