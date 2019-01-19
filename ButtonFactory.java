import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class ButtonFactory
{
    //creates a button with default styling, with it's center at x and y
    public Button defaultButton(String text, int x, int y, int xSize, int ySize){
        Button b = new Button(text);
        
        b.setLayoutX(x);
        b.setLayoutY(y);
        
        //set size to a negative number if default size is preffered
        if(xSize > 0 && ySize > 0){
            b.setPrefSize(xSize, ySize);
            b.setMaxSize(xSize, ySize);
            b.setMinSize(xSize, ySize);
        }
        return b;
    }
    
    public Button smallButton(String text, int x, int y, int xSize, int ySize){
        Button b = defaultButton(text, x, y, xSize, ySize);
        b.getStyleClass().add("small-button");
        return b;
    }
    

    public Group buttonMenu(String title, LinkedHashMap<String, EventHandler<MouseEvent>> actionMap){
        Group menuGroup = new Group();
        
        int titleBoxHeight = 40;
        
        int buttonXSize = 80;
        int buttonYSize = 30;
        
        int buttonSpace = 25;
        
        int marginXSize = 40;       //margin between edge of menu and buttons
        int marginYSize = 30;       //margin between titlebox and first button
        
        int xSize = buttonXSize + (2*marginXSize);
        int ySize = buttonYSize * actionMap.size() + buttonSpace * (actionMap.size()-1) + 2*marginYSize + titleBoxHeight;
        
        int x = centerX(xSize);
        int y = centerY(ySize);
        
        
        Rectangle menuBox = guiRect(x, y, xSize, ySize);
        menuGroup.getChildren().add(menuBox);
        
        Button titleText = titleBox(title, x, y,  xSize, titleBoxHeight);
        menuGroup.getChildren().add(titleText);
        

        
        y += titleBoxHeight + marginYSize;
        

        Iterator<Entry<String, EventHandler<MouseEvent>>> i = actionMap.entrySet().iterator();
        
        while(i.hasNext()){
            Entry<String, EventHandler<MouseEvent>> buttonInfo = i.next();
            Button b = this.smallButton(buttonInfo.getKey(), centerX(buttonXSize), y, buttonXSize, buttonYSize);
            b.setOnMouseClicked(buttonInfo.getValue());
            menuGroup.getChildren().add(b);
            
            y += buttonYSize + buttonSpace;
        }
        
        return menuGroup;
    }
    
    
    public Rectangle guiRect(int x, int y, int xSize, int ySize){
        Rectangle box = new Rectangle(x, y, xSize, ySize);
        box.setFill(Color.rgb(47, 52, 57));
        box.setArcWidth(18);
        box.setArcHeight(18);
        
        return box;
    }
    
    public Button titleBox(String text, int x, int y, int xSize, int ySize){
        Button b = defaultButton(text, x, y, xSize, ySize);
        b.getStyleClass().add("title-button");
        return b;
    }
    
    
    //for centering coordinates of objects
    public int centerX(int xSize){
        return (GameEngine.XWIDTH/2) - (xSize/2);
    }
    public int centerY(int ySize){
        return (GameEngine.YHEIGHT/2) - (ySize/2);
    }
}
