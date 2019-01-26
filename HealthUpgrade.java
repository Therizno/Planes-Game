
public class HealthUpgrade extends Upgrade
{
    int addAmount;
    
    public HealthUpgrade(int cost, int amount)
    {
        super("+Health", cost);
        addAmount = amount;
    }
    
    public void applyUpgrade(Plane player){
        player.setMaxHealth(player.getMaxHealth()+addAmount);
        player.setHealth(player.getMaxHealth());
                
        player.subMoney(getCost());
    }
}
