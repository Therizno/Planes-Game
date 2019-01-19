import java.util.ArrayList;

import javafx.scene.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

public abstract class Level implements GameState
{
    //entity management data structures
    private ArrayList<Entity> entityList;
    private ArrayList<Pair<CombatEntity, Bullet>> bulletList;
    private ArrayList<CombatEntity> enemyList;
    
    private ArrayList<Upgrade> availableUpgrades;
    
    protected Plane player;
    private Group root;
    private Hud playerHud;
    
    public boolean pauseGame;
    private boolean guiDisabled;
    
    private EnemyAI ai = new EnemyAI();
    
    
    public Level(Plane player, Group rootNode){
        entityList = new ArrayList<Entity>();
        bulletList = new ArrayList<Pair<CombatEntity, Bullet>>();
        enemyList = new ArrayList<CombatEntity>();
        
        availableUpgrades = new ArrayList<Upgrade>();
        
        this.player = player;
        entityList.add(player);
        root = rootNode;
        root.getChildren().add(player.display());
        
        //set up hud
        playerHud = new Hud(this);
        root.getChildren().add(playerHud.hud());
        
        pauseGame = false;
        guiDisabled = false;
    }
    
    public void onKeyHold(ArrayList<String> input){
        if(input.contains("A")){
            player.turnLeft();
        }
        if(input.contains("D")){
            player.turnRight();
        }
    }
    
    public void onKeyPress(String keyCode){
        if(keyCode.equals("ESCAPE")){
            pauseGame = true;
        }
    }

    public void onMousePress(double mouseX, double mouseY){
        for(Bullet b : player.fireGuns()){
            bulletList.add(new Pair(player, b));
            entityList.add(b);
            root.getChildren().add(0, b.display());
        }
    }
    
    public GameState newState(){
        if(pauseGame){
            return new PauseState(this, root);
        }
        return this;
    }
    
    public void updateAndDisplay(){
        /*
         * move camera with player
         */
        int tpX = 0;
        int tpY = 0;
        if(player.xPos() < 0){
            tpX = GameEngine.XWIDTH;
        }
        else if(player.yPos() < 0){
            tpY = GameEngine.YHEIGHT;
        }
        else if(player.xPos() > GameEngine.XWIDTH){
            tpX = -GameEngine.XWIDTH;
        }
        else if(player.yPos() > GameEngine.YHEIGHT){
            tpY = -GameEngine.YHEIGHT;
        }
        if(tpX != 0 || tpY != 0){
            for(Entity e : entityList){
                e.teleport(e.xPos()+tpX, e.yPos()+tpY);
            }
        }
        
        
        /*
         * enemy logic
         */
        for(int i = 0; i < enemyList.size(); i++){
            CombatEntity enemy = enemyList.get(i);
            
            //ai
            for(Bullet b : ai.planeAI(player, enemy)){
                entityList.add(b);
                bulletList.add(new Pair(enemy, b));
                root.getChildren().add(0, b.display());
            }
            
            //death
            if(enemy.getHealth() == 0){
                player.addMoney(enemy.getReward());
                entityList.remove(enemy);
                enemyList.remove(i);
                root.getChildren().remove(enemy.display());
                
                //create an explosion effect
                ParticleEmitter p = ParticleEmitter.explosion(enemy.xPos(), enemy.yPos());
                for(int j = 0; i < 50; i++){
                    Particle par = p.emitSmoke();
                    entityList.add(par);
                    root.getChildren().add(par.display());
                }
            }
        }
        
        
        /*
         * general entity logic
         */
        for(int i = 0; i < entityList.size(); i++){
            Entity e = entityList.get(i);
            e.display();
            e.update();
            
            if(e.deAlloc()){
                entityList.remove(i);
                bulletList.remove(e);
                root.getChildren().remove(e.display());
            }
        }
        
        detectCollisions();
        
        
        /*
         * player logic
         */
        playerHud.updateHealth((int)player.getHealth(), (int)player.getMaxHealth());
        playerHud.updateAmmo(player.getAmmo(), player.getMaxAmmo());
        playerHud.updateMoney(player.getMoney());
        
        if(player.getHealth() < player.getMaxHealth()/3){
            if(player.emitterSize() == 0){
                player.addParticleEmitter(ParticleEmitter.smokeTrail(player.xPos(), player.yPos()), 24, -15);
            }
            
            //temporary
            Particle p = player.getParticleEmitters().get(0).emitSmoke();
            entityList.add(p);
            root.getChildren().add(0, p.display());
        }
        
        for(ParticleEmitter p : player.getParticleEmitters()){
            p.setX(player.relativeX(p.getXOffset(), p.getYOffset()));
            p.setY(player.relativeY(p.getXOffset(), p.getYOffset()));
        }
        
        //temporary debug
        //player.setHealth((player.getMaxHealth()/3)-1);
        
        
        pauseGame = false;
    }
    
    private void detectCollisions(){
        for(int i = 0; i < enemyList.size(); i++){
            CombatEntity enemy = enemyList.get(i);
            for(int j = 0; j < bulletList.size(); j++){
                Pair<CombatEntity, Bullet> bulletPair = bulletList.get(j);
                Bullet b = bulletPair.getValue();
                if(bulletPair.getKey().equals(player)){
                    if(enemy.collide(b) || b.collide(enemy)){
                        enemy.subHealth(b.getDamage());
                        bulletList.remove(j);
                        entityList.remove(b);
                        root.getChildren().remove(b.display());
                    }
                }
                else if(player.collide(b) || b.collide(player)){
                    player.subHealth(b.getDamage());
                    bulletList.remove(j);
                    entityList.remove(b);
                    root.getChildren().remove(b.display());
                }
            }
        }
        
        for(int i = 0; i < bulletList.size(); i++){
            Pair<CombatEntity, Bullet> bulletPair = bulletList.get(i);
            Bullet b = bulletPair.getValue();
            if(!bulletPair.getKey().equals(player)){
                if(player.collide(b) || b.collide(player)){
                    
                }
            }
        }
    }
    
    public ArrayList<Upgrade> getUpgrades(){
        return availableUpgrades;
    }
    public void addUpgrade(Upgrade g){
        availableUpgrades.add(g);
        playerHud.updateUpgrades();
    }
    
    public void spawn(Entity object){
        entityList.add(object);
        root.getChildren().add(object.display());
    }
    public void spawnEnemy(CombatEntity enemy, ArrayList<Gun> gunList, int reward){
        entityList.add(enemy);
        enemyList.add(enemy);
        enemy.setReward(reward);
        root.getChildren().add(enemy.display());
        
        for(Gun g : gunList){
            enemy.addGun(g);
        }
    }
}
