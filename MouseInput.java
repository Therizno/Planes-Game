/*
stores mouse input data
*/

public class MouseInput
{
    public boolean mousePressed;
    
    //store the coordinates of the last mouse event
    public double mouseX;
    public double mouseY;

    public MouseInput()
    {
        mousePressed = false;
        mouseX = 0;
        mouseY= 0;
    }
}
