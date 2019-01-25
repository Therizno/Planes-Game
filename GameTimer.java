
public class GameTimer
{
    private double startTime;
    private boolean started;
    
    public GameTimer()
    {
        started = false;
    }
    
    public void start(){
        if(!started)
            startTime = System.currentTimeMillis();
        started = true;
    }
    
    //intended to be called once per frame
    public boolean waitFor(double millis){;
        double time = System.currentTimeMillis() - startTime;
        if(time > millis)
            return true;
        return false;
    }
}
