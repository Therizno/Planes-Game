
public class GunFactory
{

    public GunFactory()
    {
        
    }

    public Gun m249(){
        Gun g = new Gun(15, 75, "m249", 400, 2.5);
        g.setBulletLength(8);
        return g;
    }
    
    public Gun m240(){
        Gun g = new Gun(12, 125, "m240", 200, 5);
        g.setBulletLength(10);
        return g;
    }
}
