
public class EnemyFactory
{
    GunFactory fac;
    public EnemyFactory()
    {
        fac = new GunFactory();
    }
    
    public Plane easyPlane(int x, int y){
        Plane p = new Plane(x, y, 1, 1, "player_texture.png", 50);
        p.addGun(fac.m249());
        return p;
    }
}
