import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.geometry.Point2D;

public class Particle extends Entity
{
    Image particleImage;
    ImageView img;
    
    double startTime;
    double fadeTime;
    
    public Particle(double xStart, double yStart, double angle, double speed, int size, double fadeTime, String imageName)
    {
        super(xStart, yStart, angle, speed, 0);
        
        particleImage = new Image(imageName);
        img = new ImageView(particleImage);
        
        img.setFitHeight(size);
        img.setFitWidth(size);
        
        startTime = System.currentTimeMillis();
        this.fadeTime = fadeTime;
    }
    
    public boolean containsPoint(Point2D p){
        return false;
    }
    public boolean collide(Entity e){
        return false;
    }
    
    public Node display(){
        double time = System.currentTimeMillis() - startTime;
        
        img.setX(xPos());
        img.setY(yPos());
        img.setOpacity(1/(time/fadeTime));
        
        return img;
    }
    
    public boolean deAlloc(){
        return img.getOpacity() < 0.001;
    }
}
