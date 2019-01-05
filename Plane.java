import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;

public class Plane extends Entity
{
    Image planeImage;
    ImageView img;

    public Plane(double xStart, double yStart, double speed, double turn, String imageName)
    {
        super(xStart, yStart, speed, turn);
        planeImage = new Image(imageName);
        img = new ImageView(planeImage);
    }

    public Node display(){
        img.setRotate(Math.toDegrees(angle()));
        img.setX(xPos());
        img.setY(yPos());
        return img;
    }
}
