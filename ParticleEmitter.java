import java.util.Random;

public class ParticleEmitter
{
    private double x;
    private double y;
    private double particleSpeed;
    private double startAngle;
    private double endAngle;
    private double fadeTime;
    private int particleSize;
    
    //for use when attached to an entity
    private int xOffset;
    private int yOffset;
    
    public ParticleEmitter(double xStart, double yStart, double speed, double thetaStart, double thetaEnd, double length, int particleSize)
    {
        x = xStart;
        y = yStart;
        particleSpeed = speed;
        startAngle = thetaStart;
        endAngle = thetaEnd;
        fadeTime = length;
        this.particleSize = particleSize;
    }
    
    //preset particle effect
    public static ParticleEmitter explosion(double xStart, double yStart){
        double speed = 2;
        double time = 2;
        int pSize = 40;
        return new ParticleEmitter(xStart, yStart, speed, 0, 2*Math.PI, time, pSize);
    }
    public static ParticleEmitter smokeTrail(double xStart, double yStart){
        double time = 5;
        int pSize = 20;
        return new ParticleEmitter(xStart, yStart, 1.0, 0.0, 2*Math.PI, time, pSize);
    }
    
    public Particle emitSmoke(){
        double randomAngle = startAngle + endAngle*new Random().nextDouble();
        return new Particle(x-particleSize/2, y-particleSize/2, randomAngle, particleSpeed, particleSize, fadeTime, "grey_particle.png");
    }
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double newX){
        x = newX;
    }
    public void setY(double newY){
        y = newY;
    }
    
    public int getXOffset(){
        return xOffset;
    }
    public int getYOffset(){
        return yOffset;
    }
    public void setXOffset(int offset){
        xOffset = offset;
    }
    public void setYOffset(int offset){
        yOffset = offset;
    }
}
