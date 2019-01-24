import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

//for debug
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Plane extends CombatEntity
{
    private Group displayGroup;
    private ImageView img;
    private Image planeImage;
    
    private ArrayList<Rectangle> hitbox;
    public boolean displayHitbox;
    private Circle debugCircle;

    public Plane(double xStart, double yStart, double speed, double turn, String imageName, int startHealth)
    {
        super(xStart, yStart, Math.PI/-2, speed, turn, startHealth);
        displayGroup = new Group();
        
        planeImage = new Image(imageName);
        img = new ImageView(planeImage);
        displayGroup.getChildren().add(img);
        
        
        /*
         * generating hitbox
         */
        hitbox = new ArrayList<Rectangle>();
        displayHitbox = false;
        
        //mess with these variables to change the size of the hitbox
        int planeRearLength = 40;
        int planeFrontLength = 36;
        int planeWidth = 15;
        int engineSpan = 40;
        int engineLength = 21;
        int wingWidth = 13;
        int wingRearLength = 2;
        int wingForwardLength = 11;
        int wingDistance = engineSpan/2;
        
        /*
         * DON'T mess with the next part:
         * 
         * the way these vairables are used may be confusing or counterintuitive, but that
         * is because the hitbox starts SIDEWAYS in relation to the plane texture
         */
        Rectangle bodyHitbox = new Rectangle(xPos()-planeRearLength, yPos()-planeWidth/2, planeRearLength+planeFrontLength, planeWidth);
        hitbox.add(bodyHitbox);
        displayGroup.getChildren().add(bodyHitbox);
        
        Rectangle engineWing = new Rectangle(xPos(), yPos()-engineSpan/2, engineLength, engineSpan);
        hitbox.add(engineWing);
        displayGroup.getChildren().add(engineWing);
        
        Rectangle wingLeft = new Rectangle(xPos()-wingRearLength, yPos()-wingDistance-wingWidth, wingForwardLength+wingRearLength, wingWidth);
        hitbox.add(wingLeft);
        displayGroup.getChildren().add(wingLeft);
        
        Rectangle wingRight = new Rectangle(xPos()-wingRearLength, yPos()+wingDistance, wingForwardLength+wingRearLength, wingWidth);
        hitbox.add(wingRight);
        displayGroup.getChildren().add(wingRight);
        
        if(displayHitbox){
            debugCircle = new Circle(xPos(), yPos(), 5);
            displayGroup.getChildren().add(debugCircle);
        }
        else{
            for(Rectangle r : hitbox){
                r.setOpacity(0);
            }
        }
    }

    @Override
    public void update(){
        super.update();
        
        if(getHealth() < getMaxHealth()/3){
            if(emitterSize() == 0)
                addParticleEmitter(ParticleEmitter.smokeTrail(xPos(), yPos()), 16, -11);
        }
        else{
            clearParticleEmitters();
        }
        
        for (Rectangle r : hitbox){
            r.setX(r.getX()+dX());
            r.setY(r.getY()+dY());
            r.getTransforms().clear();
            r.getTransforms().add(new Rotate(Math.toDegrees(angle()), xPos(), yPos()));
        }
    }
    
    @Override
    public void teleport(double newX, double newY){
        for (Rectangle r : hitbox){
            r.setX(newX + (r.getX()-xPos()));
            r.setY(newY + (r.getY()-yPos()));
        }
        super.teleport(newX, newY);
    }
    
    public boolean containsPoint(Point2D p){
        boolean cont = false;
        for(Rectangle r : hitbox){
            cont = cont || r.contains(p);
        }
        return cont;
    }
    public boolean collide(Entity e){
        boolean col = false;
        for(Rectangle r : hitbox){
            col = col || e.containsPoint(new Point2D(r.getX(), r.getY()));
            col = col || e.containsPoint(new Point2D(r.getX()+r.getWidth(), r.getY()));
            col = col || e.containsPoint(new Point2D(r.getX(), r.getY()+r.getHeight()));
            col = col || e.containsPoint(new Point2D(r.getX()+r.getWidth(), r.getY()+r.getHeight()));
        }
        return col;
    }
    
    public Node display(){
        img.setRotate(Math.toDegrees(angle()));
        img.setX(xPos() - (planeImage.getWidth()/2));
        img.setY(yPos() - (planeImage.getHeight()/2));
        
        //for debugging
        if(displayHitbox){
            for(Rectangle r : hitbox){
                r.setStroke(Color.BLACK);
                r.setFill(Color.TRANSPARENT);
                r.setStrokeWidth(2.0);
            }
            debugCircle.setCenterX(xPos());
            debugCircle.setCenterY(yPos());
        }

        return displayGroup;
    }
    
    public void addGun(Gun gun){
        if(gunSize() < 2){        //max number of guns is currently 2
            super.addGun(gun, 0, -10);
            if(gunSize() == 2){
                ArrayList<Gun> gl = getGuns();
                gl.get(0).setXOffset(15);
                gl.get(1).setXOffset(-15);
            }
        }
    }
}
