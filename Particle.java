import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.geometry.Point2D;

public class Particle extends Entity
{
    Image particleImage;
    ImageView img;
    
    double fadeDistance;
    
    public Particle(double xStart, double yStart, double angle, double speed, int size, double fadeDistance, String imageName)
    {
        super(xStart, yStart, angle, speed, 0);
        
        particleImage = new Image(imageName);
        img = new ImageView(particleImage);
        
        img.setFitHeight(size);
        img.setFitWidth(size);
        
        this.fadeDistance = fadeDistance;
    }
    
    public boolean containsPoint(Point2D p){
        return false;
    }
    public boolean collide(Entity e){
        return false;
    }
    
    public Node display(){
        img.setX(xPos());
        img.setY(yPos());
        img.setOpacity(fadeDistance/getDistanceTraveled());
        return img;
    }
}
