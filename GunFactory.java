
public class GunFactory
{

    public GunFactory()
    {
        
    }

    public Gun m249(){
        Gun g = new Gun(15, 75, "m249", 800, 2.5);
        g.setBulletLength(8);
        return g;
    }
}
