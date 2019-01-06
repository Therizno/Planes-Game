import javafx.scene.control.Button;

public class ButtonFactory
{
    //creates a button with default styling, with it's center at x and y
    public Button defaultButton(String text, int x, int y, int xSize, int ySize){
        Button b = new Button(text);
        
        b.setLayoutX(x - (xSize/2));
        b.setLayoutY(y - (ySize/2));
        
        b.setPrefSize(xSize, ySize);
        b.setMaxSize(xSize, ySize);
        b.setMinSize(xSize, ySize);
        
        return b;
    }
}
