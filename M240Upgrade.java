
public class M240Upgrade extends Upgrade
{
    public M240Upgrade(int cost)
    {
        super("+M240", cost);
    }
    
    public void applyUpgrade(Plane player){
        GunFactory fac = new GunFactory();
        player.clearGuns();
                
        player.addGun(fac.m240());
        
        player.subMoney(getCost());
    }
}
