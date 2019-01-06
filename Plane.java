import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Group;

public class Plane extends Entity
{
    private ArrayList<Gun> guns;
    private Image planeImage;
    private ImageView img;

    public Plane(double xStart, double yStart, double speed, double turn, String imageName)
    {
        super(xStart, yStart, Math.PI/-2, speed, turn);
        planeImage = new Image(imageName);
        img = new ImageView(planeImage);
        guns = new ArrayList<Gun>();
    }

    public Node display(){
        img.setRotate(Math.toDegrees(angle()));
        img.setX(xPos() - (planeImage.getWidth()/2));
        img.setY(yPos() - (planeImage.getHeight()/2));

        return img;
    }

    public void addGun(Gun gun){
        guns.add(gun);
        if(guns.size() == 2){
            guns.get(0).setXOffset(10);
            guns.get(1).setXOffset(-10);
        }
        assert (guns.size() < 3);       //currently only two gun capacity
    }

    public ArrayList<Bullet> fireGuns(double currentTime){
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();

        for(Gun g : guns){
            Bullet b = g.fire(xPos(), yPos(), angle(), currentTime);
            if(b != null){
                bullets.add(b);
            }
        }
        return bullets;
    }
}
