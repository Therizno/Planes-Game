
public class AmmoUpgrade extends Upgrade
{
    int addAmount;
    
    public AmmoUpgrade(int cost, int amount)
    {
        super("+Ammo", cost);
        addAmount = amount;
    }
    
    public void applyUpgrade(Plane player){
        for(Gun g : player.getGuns()){
            g.setMaxAmmo(g.getMaxAmmo()+addAmount);
            g.setAmmo(g.getMaxAmmo());
        }

        player.subMoney(getCost());
    }
}
