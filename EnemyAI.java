import java.util.ArrayList;

public class EnemyAI
{   
    public ArrayList<Bullet> planeAI(Plane player, CombatEntity enemy){
        double angleDifference = enemy.angle()-Math.atan2(player.yPos()-enemy.yPos(), player.xPos()-enemy.xPos()); //y is intentionally before x
        if(Math.toDegrees(angleDifference) < -180)      //corrects for negative angle
            angleDifference = Math.toRadians(Math.toDegrees(angleDifference)+360);
        
        //if the enemy plane is within +-turnThreshold degrees of facing the player, it will
        //not turn
        double turnThreshold = 5;
        if(Math.toDegrees(angleDifference) > turnThreshold){
            enemy.turnLeft();
        }
        else if(Math.toDegrees(angleDifference) < -turnThreshold){
            enemy.turnRight();
        }
        
        boolean canTarget = false;
        for(Gun g : enemy.getGuns()){
            canTarget = canTarget || canTarget(player, enemy, g);
        }
        
        if(canTarget)
            return enemy.fireGuns();
        return new ArrayList<Bullet>();
    }
    
    private boolean canTarget(Plane player, CombatEntity enemy, Gun g){
        //the maximum number of frames in the future this gun can see
        int maxRange = 1000;
        
        //how close a bullet has to be to the exact x and y coordinates of the player in order
        //for the gun to fire
        int fireThreshold = 100;
        
        double gunX = enemy.relativeX(g.getXOffset(), g.getYOffset());
        double gunY = enemy.relativeY(g.getXOffset(), g.getYOffset());
        
        for(int t = 0; t < maxRange; t++){
            double bulletX = gunX + t*g.getVelocity()*Math.cos(enemy.angle());
            double bulletY = gunY + t*g.getVelocity()*Math.sin(enemy.angle());
            
            double playerX = player.xPos() + t*player.dX();
            double playerY = player.yPos() + t*player.dY();
            
            double bulletDistance = Math.sqrt(Math.pow(playerX-bulletX, 2) + Math.pow(playerY-bulletY, 2));
            if(bulletDistance < fireThreshold)
                return true;
        }
        return false;
    }
}
